/**
 * 
 */
package com.owt.contact.repository;

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

import com.owt.contact.constant.ESkillLevel;
import com.owt.contact.mock.DataUtils;
import com.owt.contact.mock.MockDataConstant;
import com.owt.contact.mock.WithMockRoleAdmin;
import com.owt.contact.model.Person;
import com.owt.contact.model.PersonSkill;
import com.owt.contact.model.Skill;

/**
 * @author moussa.kanoute
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class PersonSkillRepositoryTest {

	@Autowired
	PersonSkillRepository personSkillRepository;

	@Autowired
	PersonRepository personRepository;

	@Autowired
	SkillRepository skillRepository;

	@Test(expected = AuthenticationCredentialsNotFoundException.class)
	public void whenNoAuthenticatedUser_save_thenExceptionCredNotFound() {
		personSkillRepository.save(DataUtils.load(MockDataConstant.JOHN_SKILL_JSON_FILE, PersonSkill.class));
	}

	@Test(expected = AccessDeniedException.class)
	@WithAnonymousUser
	public void whenAnonymousUser_save_thenExceptionAccessDenied() {
		personSkillRepository.save(initJohnSkill());
	}

	@Test(expected = AccessDeniedException.class)
	@WithMockRoleAdmin
	public void whenAnotherUser_save_thenExceptionAccessDenied() {
		personSkillRepository.save(initJohnSkill());
	}

	private PersonSkill initJohnSkill() {

		Person mockJohnPerson = personRepository.save(DataUtils.load(MockDataConstant.JOHN_JSON_FILE, Person.class));
		Skill mockAngularSkill = skillRepository.save(DataUtils.load(MockDataConstant.ANGULAR_JSON_FILE, Skill.class));

		PersonSkill newPersonSkill = new PersonSkill();
		newPersonSkill.setLevel(ESkillLevel.INTERMEDIATE);
		newPersonSkill.setPerson(mockJohnPerson);
		newPersonSkill.setSkill(mockAngularSkill);

		return newPersonSkill;
	}
}
