package com.pack.library.LibraryManagementSystem.controller;

import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

import com.pack.library.LibraryManagementSystem.Repository.UserRepository;
import com.pack.library.LibraryManagementSystem.entity.Books;
import com.pack.library.LibraryManagementSystem.entity.BooksStatus;
import com.pack.library.LibraryManagementSystem.service.LibraryService;

@RestController
@RequestMapping(value="/library")
public class LibraryController {
	
	@Autowired
	LibraryService libraryService;
	@Autowired
	UserRepository userRepo;
	protected  static final org.apache.juli.logging.Log logger=LogFactory.getLog(LibraryController.class);
	@GetMapping(value="/booksList",produces="application/json")
	public ResponseEntity<List<Books>> getAllBooks(){
		logger.info("get all books");
		List<Books> booksList=libraryService.getAllBooks();
		return new ResponseEntity<>(booksList,HttpStatus.OK);
	}
	@GetMapping(value="/findById/{id}",produces="application/json")
	public ResponseEntity<Books> getBookById(@PathVariable Integer id){
		Books book=libraryService.fetchBookById(id);
		return new ResponseEntity<>(book,HttpStatus.OK);
	}
	@GetMapping(value="/findByTitle/{title}",produces="application/json")
	public ResponseEntity<List<Books>> getBooksByTitle(@PathVariable String title){
		List<Books> booksList=libraryService.getBooksByTitle(title);
		return new ResponseEntity<>(booksList,HttpStatus.OK);
	}
	@GetMapping(value="/findByAuthor/{author}",produces="application/json")
	public ResponseEntity<List<Books>> getBooksByAuthor(@PathVariable String author){
		List<Books> booksList=libraryService.getBooksByAuthor(author);
		return new ResponseEntity<>(booksList,HttpStatus.OK);
	}
	@GetMapping(value="/findByCategory/{category}",produces="application/json")
	public ResponseEntity<List<Books>> getBooksByCategory(@PathVariable String category){
		List<Books> booksList=libraryService.getBooksByCategory(category);
		return new ResponseEntity<>(booksList,HttpStatus.OK);
	}
	@PutMapping(value="/borrow/{bookId}/User/{userId}",consumes="application/json",produces="application/json")
	public ResponseEntity<Books> borrowBook(@PathVariable Integer bookId,@PathVariable Integer userId){
		logger.info("borrow book");
		Books book=libraryService.update(bookId,userId);
		return new ResponseEntity<>(book,HttpStatus.OK);
		
		
	}
	@DeleteMapping(value="/delete/{id}")
	public ResponseEntity<Books> deleteById(@PathVariable Integer id){
		libraryService.deleteBook(id);
		 return new ResponseEntity<>(HttpStatus.OK);
		
	}
	@PostMapping(value="/update/{id}",consumes="application/json",produces="application/json")
	public ResponseEntity<Books> updateBook(@PathVariable Integer id,@RequestBody Books book){
		libraryService.save(book);
		return new ResponseEntity<>(book,HttpStatus.OK);
	}
	@PutMapping(value="/borrow/{bookId}/ReturnDate/{date}",consumes="application/json",produces="application/json")
	public ResponseEntity<Books> returnDate(@PathVariable Integer bookId,@PathVariable String date){
		Books book=libraryService.returnDate(bookId,date);
		
		return new ResponseEntity<>(book,HttpStatus.OK);
		
		
	}
	@GetMapping(value="/fetchByMemberId/{id}",produces="application/json")
	public ResponseEntity<List<Books>> getBooksByMemberId(@PathVariable Integer id){
		List<Books> list=libraryService.getBooksByMemberId(id);
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	@GetMapping(value="/fetchByTitle/{title}",produces="application/json")
	public ResponseEntity<List<BooksStatus>> getBooksNameByTitle(@PathVariable String title){
		List<BooksStatus> list=libraryService.fetchByTitle(title);
		return new ResponseEntity<>(list,HttpStatus.OK);
		
	}
}
