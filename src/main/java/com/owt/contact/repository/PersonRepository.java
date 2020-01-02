/**
 * 
 */
package com.owt.contact.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.access.annotation.Secured;

import com.owt.contact.constant.ContactApiConstant;
import com.owt.contact.constant.SecurityConstant;
import com.owt.contact.model.Person;
import com.owt.contact.security.IsOwner;

/**
 * @author moussa.kanoute
 *
 */
@RepositoryRestResource(collectionResourceRel = ContactApiConstant.PERSON_REL, path = ContactApiConstant.PERSON_PATH)
public interface PersonRepository extends PagingAndSortingRepository<Person, Long> {

	@RestResource(path = ContactApiConstant.BY_EMAIL_PATH, rel = ContactApiConstant.BY_EMAIL_REL)
	Person findByEmail(@Param(ContactApiConstant.EMAIL_PARAM) String email);

	@Override
	@Secured(SecurityConstant.ROLE_ADMIN)
	Iterable<Person> findAll();

	@Override
	@Secured(SecurityConstant.ROLE_ADMIN)
	Page<Person> findAll(Pageable pageable);

	@Override
	@Secured(SecurityConstant.ROLE_ADMIN)
	Iterable<Person> findAll(Sort sort);

	@Override
	@Secured({ SecurityConstant.ROLE_ADMIN, SecurityConstant.ROLE_USER })
	@IsOwner
	Optional<Person> findById(@Param(ContactApiConstant.ID_PARAM) Long id);

}
