package com.pack.library.LibraryManagementSystem.service;

import java.util.*;

import com.pack.library.LibraryManagementSystem.entity.Books;
import com.pack.library.LibraryManagementSystem.entity.BooksStatus;
import com.pack.library.LibraryManagementSystem.entity.User;

public interface LibraryService {
	
	List<Books> getAllBooks(); 
	Books fetchBookById(Integer id);
	List<Books> getBooksByTitle(String title);
	List<Books> getBooksByAuthor(String author);
	List<Books> getBooksByCategory(String category);
	Books update(Integer id,Integer userId);
	void deleteBook(Integer id);
	Books save(Books book);
	Books returnDate(Integer id,String date);
	List<Books> getBooksByMemberId(Integer id);
	List<BooksStatus> fetchByTitle(String title);
	User findUserById(Integer id);
}
