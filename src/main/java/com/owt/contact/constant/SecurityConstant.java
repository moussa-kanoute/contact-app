/**
 * 
 */
package com.owt.contact.constant;

/**
 * @author moussa.kanoute
 *
 */
public class SecurityConstant {

	private SecurityConstant() {
	}

	public static final String ADMIN_USER_PATTERN = "admin";
	public static final String ATTR_CLAIMS = "claims";
	public static final String CLAIMS_ROLES = "roles";
	public static final String CLAIMS_ROLES_SEPARATOR = ",";
	public static final String DEFAULT_PASSWORD = "password";
	public static final String DEFAULT_ROLE = "ROLE_VIEWER";
	public static final int EXPIRATION_TIME_DAY = 1;
	public static final String HEADER_JWT = "Authorization";
	public static final String ROLE_ADMIN = "ROLE_ADMIN";
	public static final String ROLE_USER = "ROLE_USER";
	public static final String SECRET = "ThisIsASecret";
	public static final String TOKEN_PREFIX = "Bearer ";

	// Authorized URL list
	public static final String SIGN_UP_URL = "/users/sign-up";
	public static final String SWAGGER_DOC_URL = "/v2/api-docs";
	public static final String SWAGGER_CON_URL = "/configuration/ui";
	public static final String SWAGGER_RES_URL = "/swagger-resources/**";
	public static final String SWAGGER_SEC_URL = "/configuration/security";
	public static final String SWAGGER_SUI_URL = "/swagger-ui.html";
	public static final String SWAGGER_WEB_URL = "/webjars/**";

}
