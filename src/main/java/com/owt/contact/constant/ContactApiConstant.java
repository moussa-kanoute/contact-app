/**
 * 
 */
package com.owt.contact.constant;

/**
 * @author moussa.kanoute
 *
 */
public class ContactApiConstant {

	private ContactApiConstant() {

	}

	public static final String BY_EMAIL_PATH = "by_email";
	public static final String BY_EMAIL_REL = "findByEmail";
	public static final String EMAIL_PARAM = "email";
	public static final String ID_PARAM = "id";
	public static final String PERSON_SKILL_PARAM = "personSkill";
	public static final String PERSON_SKILL_SPL_ID = "personSkill?.person?.id";
	public static final String PERSON_PATH = "persons";
	public static final String PERSON_REL = "persons";
	public static final String PERSON_SKILL_PATH = "skillspersons";
	public static final String PERSON_SKILL_REL = "skillspersons";
	public static final String SKILL_PATH = "skills";
	public static final String SKILL_REL = "skills";

	public static final String EMAIL_NOT_VALIDL_MSG = "Email should be valid";
	public static final String FIRSTNAME_NOT_NULL_MSG = "First name cannot be null";
	public static final String LASTNAME_NOT_NULL_MSG = "Last name cannot be null";
}
