/**
 * 
 */
package com.owt.contact.mock;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.springframework.security.test.context.support.WithMockUser;

import com.owt.contact.constant.SecurityConstant;

/**
 * @author moussa.kanoute
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@WithMockUser(username = MockDataConstant.JOHN_USER_ID, authorities = SecurityConstant.ROLE_USER)
public @interface WithMockRoleUser {

}
