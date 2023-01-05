package com.shukhrat.expensetrackerapi.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "tbl_users")
@Data
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Long id;
	
	private String name;
	
	@Column(unique = true)
	private String email;
	
	@JsonIgnore
	private String password;
	
	private Long age;
	
	@Column(name="created_at", nullable = false, updatable=false)
	@CreationTimestamp
	private Timestamp created_at;
	
	@Column(name="updated_at")
	@UpdateTimestamp
	private Timestamp updated_at;
	
}
