/**
 * 
 */
package com.owt.contact.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.security.access.prepost.PreAuthorize;

import com.owt.contact.constant.ContactApiConstant;

/**
 * @author moussa.kanoute
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("T(java.lang.String).valueOf(#" + ContactApiConstant.PERSON_SKILL_SPL_ID + ") == authentication.name")
public @interface IsSkillOwner {

}
