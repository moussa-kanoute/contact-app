/**
 * 
 */
package com.owt.contact.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.owt.contact.constant.SecurityConstant;
import com.owt.contact.model.Person;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @author moussa.kanoute
 *
 */
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;

	public AuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) {
		String email = null;
		try {
			Person pCred = new ObjectMapper().readValue(req.getInputStream(), Person.class);
			email = pCred.getEmail();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (email == null) {
				email = "";
			}
		}

		return authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(email, SecurityConstant.DEFAULT_PASSWORD, new ArrayList<>()));
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
			Authentication auth) throws IOException, ServletException {

		String authorities = auth.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining(","));

		addAuthentication(res, ((User) auth.getPrincipal()).getUsername(), authorities);

	}

	private void addAuthentication(HttpServletResponse res, String subject, String roles) {

		Calendar expDateCalendar = Calendar.getInstance();
		expDateCalendar.add(Calendar.DATE, SecurityConstant.EXPIRATION_TIME_DAY);

		Claims claims = Jwts.claims().setSubject(subject).setExpiration(expDateCalendar.getTime());
		claims.put(SecurityConstant.CLAIMS_ROLES, roles);

		String token = Jwts.builder().setClaims(claims).setExpiration(expDateCalendar.getTime())
				.signWith(SignatureAlgorithm.HS512, SecurityConstant.SECRET).compact();

		res.addHeader(SecurityConstant.HEADER_JWT, SecurityConstant.TOKEN_PREFIX + token);
	}

}
