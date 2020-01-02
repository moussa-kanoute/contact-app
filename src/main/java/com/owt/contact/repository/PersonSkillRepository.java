/**
 * 
 */
package com.owt.contact.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.annotation.Secured;

import com.owt.contact.constant.ContactApiConstant;
import com.owt.contact.constant.SecurityConstant;
import com.owt.contact.model.PersonSkill;
import com.owt.contact.security.IsSkillOwner;

/**
 * @author moussa.kanoute
 *
 */
@RepositoryRestResource(collectionResourceRel = ContactApiConstant.PERSON_SKILL_REL, path = ContactApiConstant.PERSON_SKILL_PATH)
@Secured({ SecurityConstant.ROLE_ADMIN, SecurityConstant.ROLE_USER })
public interface PersonSkillRepository extends PagingAndSortingRepository<PersonSkill, Long> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Override
	@IsSkillOwner
	<S extends PersonSkill> S save(@Param(ContactApiConstant.PERSON_SKILL_PARAM) S personSkill);

}
