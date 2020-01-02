/**
 * 
 */
package com.owt.contact.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.owt.contact.constant.SecurityConstant;
import com.owt.contact.model.Person;
import com.owt.contact.repository.PersonRepository;
import com.owt.contact.service.PersonService;

/**
 * @author moussa.kanoute
 *
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private PersonRepository personRepository;
	private PersonService personService;

	@Autowired
	public UserDetailsServiceImpl(PersonRepository personRepository, PersonService personService) {
		this.personRepository = personRepository;
		this.personService = personService;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.userdetails.UserDetailsService#
	 * loadUserByUsername(java.lang.String)
	 */
	@Override
	public UserDetails loadUserByUsername(String email) {
		Collection<String> roles = new ArrayList<>(Arrays.asList(SecurityConstant.DEFAULT_ROLE));
		Person person = personRepository.findByEmail(email);

		if (person == null) {
			person = new Person();
			person.setEmail(email);
			person.setFirstName("");
			person.setLastName("");
			person = personRepository.save(person);
		}

		if (person.getEmail().contains(SecurityConstant.ADMIN_USER_PATTERN)) {
			roles.add(SecurityConstant.ROLE_ADMIN);
		} else {
			roles.add(SecurityConstant.ROLE_USER);
		}

		Collection<GrantedAuthority> authorities = AuthorityUtils
				.createAuthorityList(roles.toArray(new String[roles.size()]));

		return new User(person.getId().toString(), personService.getDefaultEncodedPassword(), authorities);

	}

}
