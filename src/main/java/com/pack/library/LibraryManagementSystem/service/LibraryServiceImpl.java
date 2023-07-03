package com.pack.library.LibraryManagementSystem.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pack.library.LibraryManagementSystem.Repository.BooksRepository;
import com.pack.library.LibraryManagementSystem.Repository.UserRepository;
import com.pack.library.LibraryManagementSystem.entity.Books;
import com.pack.library.LibraryManagementSystem.entity.BooksStatus;
import com.pack.library.LibraryManagementSystem.entity.User;

@Service
@Transactional
public class LibraryServiceImpl implements LibraryService{
	
	@Autowired
	BooksRepository booksRepo;
	@Autowired
	UserRepository userRepo;

	@Override
	public List<Books> getAllBooks() {
		List<Books> book=booksRepo.findAll();
		return book;
	}

	@Override
	public Books fetchBookById(Integer id) {
		Optional<Books> book=booksRepo.findById(id);
		Books b=null;
		if(book.isPresent()) {
			b= book.get();
		}
		else if(book.isEmpty()){
			b=new Books();
		}
		return b;
		
	}

	@Override
	public List<Books> getBooksByTitle(String title) {
		return booksRepo.findByTitle(title);
	}

	@Override
	public List<Books> getBooksByAuthor(String author) {
		return booksRepo.findByAuthor(author);
	}

	@Override
	public List<Books> getBooksByCategory(String category) {		
		return booksRepo.findByCategory(category);
	}



	@Override
	public Books update(Integer id,Integer userId) {
		Optional<Books> book=booksRepo.findById(id);
		Books b=null;
		if(book.isPresent()) {
		LocalDate ld=LocalDate.now();
		Books book1=book.get();
		book1.setBorrowedDate(ld);
		book1.setStatus("Borrowed");
		book1.setReturnDate(ld.plusDays(14));
		Optional<User> u=userRepo.findById(userId);
		if(u.isPresent()) {
		book1.setUser(u.get());	
		}
		b= booksRepo.save(book1);
		}
		else if(book.isEmpty()){
			b= new Books();
		}
		return b;
		
	}

	@Override
	public void deleteBook(Integer id) {
		Optional<Books> book=booksRepo.findById(id);
		if(book.isPresent()) {
		 booksRepo.delete(book.get());
		}
	}

	@Override
	public Books save(Books book) {
		return booksRepo.save(book);
		
	}

	@Override
	public Books returnDate(Integer id, String date) {
		Optional<Books> book=booksRepo.findById(id);
		Books book1=null;
		if(book.isPresent()) {
		Books b=book.get();
		LocalDate borrowedDate=b.getBorrowedDate();
		LocalDate returnDate=LocalDate.parse(date);
		long p2 = ChronoUnit.DAYS.between(borrowedDate, returnDate);
		if(p2>14) {
			b.setStatus("OVERDUED");
			b.setBorrowedDate(null);
			b.setReturnDate(null);
			b.setUser(null);
		}
		else {
			b.setBorrowedDate(null);
			b.setReturnDate(null);
			b.setStatus("AVAILABLE");
			b.setUser(null);
		}
		book1= booksRepo.save(b);
		}
		else {
			book1= new Books();
		}
		return book1;
		
	}

	@Override
	public List<Books> getBooksByMemberId(Integer id) {
		Optional<User> u=userRepo.findById(id);
		if(u.isPresent()) {
		return booksRepo.findByUser(u.get());
		}
		else {
			return new ArrayList<Books>();
		}
	}

	@Override
	public List<BooksStatus> fetchByTitle(String title) {
		List<Books> l=booksRepo.findByTitle(title);
		List<BooksStatus> list=new ArrayList<>();
		for(Books b:l) {
			BooksStatus bs=new BooksStatus();
			bs.setTitle(b.getTitle());
			bs.setStatus(b.getStatus());
			bs.setUser(b.getUser());
			list.add(bs);
			
		}
		return list;
	}

	@Override
	public User findUserById(Integer id) {
		Optional<User> u=userRepo.findById(id);
		if(u.isPresent()) {
		return u.get();
		}
		else {
			return new User();
		}
	}

}
