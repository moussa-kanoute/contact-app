/**
 * 
 */
package com.owt.contact.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import com.owt.contact.constant.ContactApiConstant;
import com.owt.contact.model.Skill;

/**
 * @author moussa.kanoute
 *
 */
@RepositoryRestResource(collectionResourceRel = ContactApiConstant.SKILL_REL, path = ContactApiConstant.SKILL_PATH)
@PreAuthorize("isAuthenticated()")
public interface SkillRepository extends PagingAndSortingRepository<Skill, Long> {

	/**
	 * @param name
	 * @return
	 */
	Skill findByName(String name);

}
