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
import com.owt.contact.constant.SecurityConstant;

/**
 * @author moussa.kanoute
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("hasRole('" + SecurityConstant.ROLE_ADMIN + "') or T(java.lang.String).valueOf(#"
		+ ContactApiConstant.ID_PARAM + ") == authentication.name")
public @interface IsOwner {

}
