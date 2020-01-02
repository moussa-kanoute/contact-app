/**
 * 
 */
package com.owt.contact.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
public class Moussa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = DbConstant.COL_SKI_ID)
	private Long id;

	@Column(name = DbConstant.COL_SKI_NAME)
	private String name;

}
