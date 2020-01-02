/**
 * 
 */
package com.owt.contact.repository;

import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;

import com.owt.contact.mock.DataUtils;
import com.owt.contact.mock.MockDataConstant;
import com.owt.contact.mock.WithMockRoleAdmin;
import com.owt.contact.mock.WithMockRoleUser;
import com.owt.contact.model.Person;

/**
 * @author moussa.kanoute
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class PersonRepositoryTest {

	@Autowired
	PersonRepository personRepository;

	@Before
	public void setup() {
		Person mockJohnPerson = DataUtils.load(MockDataConstant.JOHN_JSON_FILE, Person.class);
		Person mockAdminPerson = DataUtils.load(MockDataConstant.ADMIN_JSON_FILE, Person.class);

		personRepository.save(mockJohnPerson);
		personRepository.save(mockAdminPerson);
	}

	@Test(expected = AuthenticationCredentialsNotFoundException.class)
	public void whenNoAuthenticatedUser_findAll_thenExceptionCredNotFound() {
		personRepository.findAll();
	}

	@Test(expected = AccessDeniedException.class)
	@WithAnonymousUser
	public void whenAnonymousUser_findAll_thenExceptionAccessDenied() {
		personRepository.findAll();
	}

	@Test(expected = AccessDeniedException.class)
	@WithMockRoleUser
	public void whenUser_findAll_thenExceptionAccessDenied() {
		personRepository.findAll();
	}

	@Test
	@WithMockRoleAdmin
	public void whenAdmin_findAll_thenOK() {

		Iterable<Person> personList = personRepository.findAll();
		assertTrue(personList.iterator().hasNext());
	}

	@Test(expected = AuthenticationCredentialsNotFoundException.class)
	public void whenNoAuthenticatedUser_findById_thenExceptionCredNotFound() {
		personRepository.findById(Long.valueOf(MockDataConstant.JOHN_USER_ID));
	}

	@Test(expected = AccessDeniedException.class)
	@WithAnonymousUser
	public void whenAnonymousUser_findById_thenExceptionAccessDenied() {
		personRepository.findById(Long.valueOf(MockDataConstant.JOHN_USER_ID));
	}

	@Test(expected = AccessDeniedException.class)
	@WithMockRoleUser
	public void whenUser_findById_thenExceptionAccessDenied() {
		personRepository.findById(Long.valueOf(MockDataConstant.ADMIN_USER_ID));
	}

	@Test
	@WithMockRoleUser
	public void whenUser_findById_thenOk() {
		Optional<Person> person = personRepository.findById(Long.valueOf(MockDataConstant.JOHN_USER_ID));
		assertTrue(person.isPresent());
	}

	@Test
	@WithMockRoleAdmin
	public void whenAdmin_findById_thenOK() {

		Optional<Person> person = personRepository.findById(Long.valueOf(MockDataConstant.JOHN_USER_ID));
		assertTrue(person.isPresent());
	}

}
