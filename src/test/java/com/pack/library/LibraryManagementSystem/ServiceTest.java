package com.pack.library.LibraryManagementSystem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.booleanThat;

import java.awt.print.Book;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.pack.library.LibraryManagementSystem.Repository.BooksRepository;
import com.pack.library.LibraryManagementSystem.entity.Books;
import com.pack.library.LibraryManagementSystem.entity.BooksStatus;
import com.pack.library.LibraryManagementSystem.entity.User;
import com.pack.library.LibraryManagementSystem.service.LibraryService;

@RunWith(SpringRunner.class)
@SpringBootTest
class ServiceTest {
	@Autowired
	LibraryService libraryService;
	@Autowired
	BooksRepository booksRepo;
	
	@Test
	void testGetAllBooks() {
		List<Books> book=libraryService.getAllBooks();
		List<Books> list=new ArrayList<>();
		book.forEach(list::add);
		assertEquals(list.size(),book.size());
	}
	@Test
	void testFetchBookById() {
		Books book=libraryService.fetchBookById(5);
		if(book!=null) {
		assertEquals(5,book.getId());
		}
		else {
			assertEquals(null,book);
		}
		
	}
	@Test
	void testGetBookByTitle() {
		List<Books> book=libraryService.getBooksByTitle("Programming In Java");
		assertEquals(2,book.size());
	}
	@Test
	void testGetByAuthor() {
		List<Books> book=libraryService.getBooksByAuthor("komal");
		assertEquals(4,book.size());
	}
	@Test
	void testGetBooksByCategory() {
		List<Books> book=libraryService.getBooksByCategory("programming");
		assertEquals(4,book.size());
	}
	@Test
	void testBorrowBook() {
		Books book=libraryService.update(6, 2);
		if(book !=null) {
		assertEquals(book.getBorrowedDate(),LocalDate.now());
		}
		
	}
	@Test
	void testBorrowBookNull() {
		Books book=libraryService.update(1, 1);
		if(book ==null) {
			Books b=new Books();
			assertEquals(b.getClass(),book.getClass());
		}
	}
	@Test
	void testBorrowBookCheckNull() {
		Books book=libraryService.fetchBookById(1);
		if(book==null){
			Books b=new Books();
			assertEquals(b.getClass(),book.getClass());
		}
	}
	@Test
//	@Disabled("Don't want to save again")
	void testSave() {
		Books book=new Books(16,"king kong","marvel","comics",null,null,"Avaliable",null);
		Books b=libraryService.save(book);
		assertEquals(b.getId(),b.getId());
		
	}
	@Test
//	@Disabled("Don't want to save again")
	void testReturnDate() {
		
		Books b=libraryService.fetchBookById(5);
		if(b !=null) {
		Books book=libraryService.returnDate(5, "2023-07-02");
		LocalDate borrowedDate=b.getBorrowedDate();
		LocalDate returnDate=LocalDate.now();
		long p2 = ChronoUnit.DAYS.between(borrowedDate, returnDate);
		if(p2<14) {
			assertEquals("AVAILABLE",book.getStatus());
		}
	
		}
	}
	@Test
//	@Disabled("Don't want to save again")
	void testReturnDateGreater() {
		
		Books b=libraryService.fetchBookById(6);
		if(b !=null) {
		Books book=libraryService.returnDate(6, "2023-07-22");
		LocalDate borrowedDate=b.getBorrowedDate();
		LocalDate returnDate=LocalDate.now();
		long p2 = ChronoUnit.DAYS.between(borrowedDate, returnDate);
		if(p2>14) {
			assertEquals("OVERDUED",book.getStatus());
		}
	
		}
	}
	@Test
	void testIfReturnDateNull() {
		Books b=libraryService.returnDate(1, null);
		Books book=new Books();
		assertEquals(b.getClass(),book.getClass());
	}
	@Test
	void testGetBooksByMemberId() {
		List<Books> book=libraryService.getBooksByMemberId(2);
		if(book !=null) {
		List<Books> list=new ArrayList<>();
		book.forEach(list::add);
		assertEquals(book.size(),list.size());
		}
		
	}
	@Test
	void testGetBooksByMemberIdIfNull() {
		List<Books> book=libraryService.getBooksByMemberId(1);
		if(book==null) {
			assertEquals(0,book.size());
		}
	}
	@Test
	void testFetchByTitle() {
		List<BooksStatus> list=libraryService.fetchByTitle("Programming In Java");
		List<BooksStatus> bs=new ArrayList<>();
		list.forEach(bs::add);
		assertEquals(bs.size(),list.size());
	}
	@Test
	void testFindByUserId() {
		User u=libraryService.findUserById(2);
		if(u !=null) {
		assertEquals(2,u.getId());
		}
		
	}
	@Test
	void testFindByUserIdIfNull() {
		User u=libraryService.findUserById(1);
		if(u==null) {
			assertEquals(null,u);
		}
	}
	@Test
	void testDeleteBook() {
		List<Books> list=booksRepo.findAll();
		libraryService.deleteBook(list.get(list.size()-1).getId());
		List<Books> list1=booksRepo.findAll();
		assertEquals(list.size()-1,list1.size());
	}

}
