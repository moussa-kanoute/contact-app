/**
 * 
 */
package com.owt.contact.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.owt.contact.constant.DbConstant;
import com.owt.contact.constant.ESkillLevel;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author moussa.kanoute
 *
 */
@Data
@EqualsAndHashCode(exclude = { "person", "skill" })
@ToString(exclude = { "person", "skill" })
@Entity
@Table(name = DbConstant.TAB_PERSON_SKILL)
public class PersonSkill {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = DbConstant.COL_PSK_ID)
	private Long id;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = DbConstant.COL_PER_ID)
	private Person person;

	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = DbConstant.COL_SKI_ID)
	private Skill skill;

	@Column(name = DbConstant.COL_PSK_LEVEL)
	@Enumerated(EnumType.STRING)
	private ESkillLevel level;

}
