/**
 * 
 */
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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.owt.contact.constant.DbConstant;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author moussa.kanoute
 *
 */
@Data
@EqualsAndHashCode
@Entity
@Table(name = DbConstant.TAB_SKILL)
public class Skill {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = DbConstant.COL_SKI_ID)
	private Long id;

	@JsonBackReference
	@OneToMany(mappedBy = "skill", cascade = CascadeType.ALL)
	private Set<PersonSkill> personSkills;

	@Column(name = DbConstant.COL_SKI_NAME)
	private String name;

}
