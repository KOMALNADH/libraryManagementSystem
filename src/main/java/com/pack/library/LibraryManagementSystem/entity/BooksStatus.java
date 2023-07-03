package com.pack.library.LibraryManagementSystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BooksStatus {
	private String title;
	private String status;
	private User user;
	
}
