/**
 * 
 */
package com.owt.contact.security;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.owt.contact.constant.SecurityConstant;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

/**
 * @author moussa.kanoute
 *
 */
public class AuthorizationFilter extends BasicAuthenticationFilter {

	public AuthorizationFilter(AuthenticationManager authManager) {
		super(authManager);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		String header = req.getHeader(SecurityConstant.HEADER_JWT);

		if (header == null || !header.startsWith(SecurityConstant.TOKEN_PREFIX)) {
			chain.doFilter(req, res);
			return;
		}

		addAuthentication(req);

		chain.doFilter(req, res);
	}

	private void addAuthentication(HttpServletRequest request) {
		String token = request.getHeader(SecurityConstant.HEADER_JWT);
		if (token != null) {
			// parse the token.
			token = token.replace(SecurityConstant.TOKEN_PREFIX, "");
			Claims tokenClaims = Jwts.parser().setSigningKey(SecurityConstant.SECRET).parseClaimsJws(token).getBody();
			String userId = tokenClaims.getSubject();

			String[] roles = tokenClaims.get(SecurityConstant.CLAIMS_ROLES, String.class)
					.split(SecurityConstant.CLAIMS_ROLES_SEPARATOR);
			Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(roles);

			if (userId != null) {
				SecurityContextHolder.getContext().setAuthentication(
						new UsernamePasswordAuthenticationToken(new User(userId, "", authorities), null, authorities));
			}
		}
	}
}
