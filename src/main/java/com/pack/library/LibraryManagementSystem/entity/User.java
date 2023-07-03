package com.pack.library.LibraryManagementSystem.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;
@Entity
@Table(name="user")
//@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_id")
	private Integer id;
	private String name;
	
	@OneToMany(cascade=CascadeType.ALL)
//	@JoinColumn(name="user_id")
	private List<Books> books=new ArrayList<>();

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", books=" + books + "]";
	}

	


	

}
