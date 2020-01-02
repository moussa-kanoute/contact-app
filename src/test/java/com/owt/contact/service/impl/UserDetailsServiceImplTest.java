/**
 * 
 */
package com.owt.contact.service.impl;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.owt.contact.constant.SecurityConstant;
import com.owt.contact.mock.DataUtils;
import com.owt.contact.mock.MockDataConstant;
import com.owt.contact.model.Person;
import com.owt.contact.repository.PersonRepository;
import com.owt.contact.service.PersonService;

/**
 * @author moussa.kanoute
 *
 */
@RunWith(SpringRunner.class)
@ContextConfiguration
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserDetailsServiceImplTest {

	@InjectMocks
	UserDetailsServiceImpl userDetailsService;

	@Mock
	PersonRepository personRepositoryMock;

	@Mock
	PersonService personServiceMock;

	@Test
	public void whenUser_callloadUserByUsername_thenOK() {

		// Mock Data : user found in database
		Person mockJohnPerson = DataUtils.load(MockDataConstant.JOHN_JSON_FILE, Person.class);
		when(personRepositoryMock.findByEmail(MockDataConstant.JOHN_EMAIL)).thenReturn(mockJohnPerson);
		when(personServiceMock.getDefaultEncodedPassword()).thenReturn(SecurityConstant.DEFAULT_PASSWORD);

		// Invoke method with normal user
		UserDetails user = userDetailsService.loadUserByUsername(MockDataConstant.JOHN_EMAIL);

		verify(personRepositoryMock).findByEmail(MockDataConstant.JOHN_EMAIL);
		verify(personServiceMock).getDefaultEncodedPassword();
		assertTrue(user.getAuthorities().contains(new SimpleGrantedAuthority(SecurityConstant.ROLE_USER)));
	}

	@Test
	public void whenAdmin_callloadAUserByUsername_thenOK() {

		// Mock Data : Admin found in database
		Person mockAdminPerson = DataUtils.load(MockDataConstant.ADMIN_JSON_FILE, Person.class);
		when(personRepositoryMock.findByEmail(MockDataConstant.ADMIN_EMAIL)).thenReturn(mockAdminPerson);
		when(personServiceMock.getDefaultEncodedPassword()).thenReturn(SecurityConstant.DEFAULT_PASSWORD);

		// Invoke method with admin user
		UserDetails user = userDetailsService.loadUserByUsername(MockDataConstant.ADMIN_EMAIL);

		verify(personRepositoryMock).findByEmail(MockDataConstant.ADMIN_EMAIL);
		verify(personServiceMock).getDefaultEncodedPassword();
		assertTrue(user.getAuthorities().contains(new SimpleGrantedAuthority(SecurityConstant.ROLE_ADMIN)));
	}

	@Test
	public void whenUserNotFound_callloadUserByUsername_thenOK() {

		// Mock Data : return null - Person not found in database
		Person mockAdminPerson = DataUtils.load(MockDataConstant.ADMIN_JSON_FILE, Person.class);
		Person newPerson = createPerson(MockDataConstant.ADMIN_EMAIL);
		when(personRepositoryMock.findByEmail(MockDataConstant.ADMIN_EMAIL)).thenReturn(null);
		when(personRepositoryMock.save(newPerson)).thenReturn(mockAdminPerson);
		when(personServiceMock.getDefaultEncodedPassword()).thenReturn(SecurityConstant.DEFAULT_PASSWORD);

		// Invoke method with admin user
		UserDetails user = userDetailsService.loadUserByUsername(MockDataConstant.ADMIN_EMAIL);

		verify(personRepositoryMock).findByEmail(MockDataConstant.ADMIN_EMAIL);
		verify(personRepositoryMock).save(newPerson);
		verify(personServiceMock).getDefaultEncodedPassword();
		assertTrue(user.getAuthorities().contains(new SimpleGrantedAuthority(SecurityConstant.ROLE_ADMIN)));
	}

	private Person createPerson(String email) {
		Person person = new Person();
		person.setEmail(email);
		person.setFirstName("");
		person.setLastName("");
		return person;
	}
}
