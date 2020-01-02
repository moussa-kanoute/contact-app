package com.owt.contact.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.owt.contact.constant.DbConstant;
import com.owt.contact.constant.ContactApiConstant;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author moussa.kanoute
 *
 */
@Data
@EqualsAndHashCode
@Entity
@Table(name = DbConstant.TAB_PERSON)
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = DbConstant.COL_PER_ID)
	private Long id;

	@OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
	private Set<PersonSkill> personSkills;

	@Column(name = DbConstant.COL_PER_FIRSTNAME)
	@NotNull(message = ContactApiConstant.FIRSTNAME_NOT_NULL_MSG)
	private String firstName;

	@Column(name = DbConstant.COL_PER_LASTNAME)
	@NotNull(message = ContactApiConstant.LASTNAME_NOT_NULL_MSG)
	private String lastName;

	@Column(name = DbConstant.COL_PER_FULLNAME)
	private String fullName;

	@Column(name = DbConstant.COL_PER_ADDRESS)
	private String address;

	@Column(name = DbConstant.COL_PER_EMAIL)
	@NotNull(message = ContactApiConstant.EMAIL_NOT_VALIDL_MSG)
	@Email(message = ContactApiConstant.EMAIL_NOT_VALIDL_MSG)
	private String email;

	@Column(name = DbConstant.COL_PER_MOBILE_NUMBER)
	private String mobileNumber;

}
