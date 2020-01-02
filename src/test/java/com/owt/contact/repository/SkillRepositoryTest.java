/**
 * 
 */
package com.owt.contact.repository;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;

import com.owt.contact.mock.DataUtils;
import com.owt.contact.mock.MockDataConstant;
import com.owt.contact.mock.WithMockRoleUser;
import com.owt.contact.model.Skill;

/**
 * @author moussa.kanoute
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class SkillRepositoryTest {

	@Autowired
	SkillRepository skillRepository;

	@Test(expected = AuthenticationCredentialsNotFoundException.class)
	public void whenNoAuthenticatedUser_findByName_thenExceptionCredNotFound() {
		skillRepository.findByName(MockDataConstant.ANGULAR_SKILL_NAME);
	}

	@Test
	@WithMockRoleUser
	public void whenUser_findByName_thenOk() {
		initDataSkill();
		Skill skill = skillRepository.findByName(MockDataConstant.ANGULAR_SKILL_NAME);
		assertTrue(MockDataConstant.ANGULAR_SKILL_NAME.equalsIgnoreCase(skill.getName()));
	}

	private void initDataSkill() {
		skillRepository.save(DataUtils.load(MockDataConstant.ANGULAR_JSON_FILE, Skill.class));
		skillRepository.save(DataUtils.load(MockDataConstant.SCRUM_JSON_FILE, Skill.class));
		skillRepository.save(DataUtils.load(MockDataConstant.SPRING_JSON_FILE, Skill.class));
	}

}
