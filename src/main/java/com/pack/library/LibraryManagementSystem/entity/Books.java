package com.pack.library.LibraryManagementSystem.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="books")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Books {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="book_id")
	private Integer id;
	private String title;
	private String author;
	private String category;
	@Column(name="borrowed_date")
	private LocalDate borrowedDate;
	@Column(name="return_date")
	private LocalDate returnDate;
	private String status;
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="user_id")
	private User user;
//	public Books(Integer id, String title, String author, String category, LocalDate borrowedDate, LocalDate returnDate,
//			String status) {
//		super();
//		this.id = id;
//		this.title = title;
//		this.author = author;
//		this.category = category;
//		this.borrowedDate = borrowedDate;
//		this.returnDate = returnDate;
//		this.status = status;
//	}
	

}
