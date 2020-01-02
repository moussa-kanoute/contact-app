/**
 * 
 */
package com.owt.contact.mock;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import com.owt.contact.constant.SecurityConstant;

/**
 * @author moussa.kanoute
 *
 */
public class MockDataConstant {

	private MockDataConstant() {

	}

	public static final String JOHN_EMAIL = "john.doe@owt.com";
	public static final String JOHN_USER_ID = "1";
	public static final String JOHN_JSON_FILE = "jsondatafile/john_doe_person.json";

	public static final String ADMIN_EMAIL = "admin.doe@owt.com";
	public static final String ADMIN_USER_ID = "2";
	public static final String ADMIN_JSON_FILE = "jsondatafile/admin_doe_person.json";

	public static final User JOHN_USER_DETAIL = new User(JOHN_USER_ID, "",
			AuthorityUtils.createAuthorityList(SecurityConstant.DEFAULT_ROLE, SecurityConstant.ROLE_USER));

	public static final String ANGULAR_SKILL_NAME = "AngularJS";
	public static final String ANGULAR_JSON_FILE = "jsondatafile/angular_skill.json";
	public static final String SCRUM_JSON_FILE = "jsondatafile/scrum_skill.json";
	public static final String SPRING_JSON_FILE = "jsondatafile/spring_skill.json";

	public static final String JOHN_SKILL_JSON_FILE = "jsondatafile/john_skill.json";

}
