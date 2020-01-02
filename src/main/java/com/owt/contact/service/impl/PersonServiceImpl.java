/**
 * 
 */
package com.owt.contact.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.owt.contact.constant.SecurityConstant;
import com.owt.contact.service.PersonService;

/**
 * @author moussa.kanoute
 *
 */
@Service
public class PersonServiceImpl implements PersonService {

	private final String defaultEncPassword;

	@Autowired
	private PersonServiceImpl(BCryptPasswordEncoder bCryptPasswordEncoder) {
		defaultEncPassword = bCryptPasswordEncoder.encode(SecurityConstant.DEFAULT_PASSWORD);
	}

	public String getDefaultEncodedPassword() {
		return defaultEncPassword;
	}
}
