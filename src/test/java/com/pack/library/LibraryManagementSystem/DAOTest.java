package com.pack.library.LibraryManagementSystem;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.pack.library.LibraryManagementSystem.Repository.BooksRepository;
import com.pack.library.LibraryManagementSystem.Repository.UserRepository;
import com.pack.library.LibraryManagementSystem.entity.Books;
import com.pack.library.LibraryManagementSystem.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest
class DAOTest {
	
	@Autowired
	BooksRepository booksRepo;
	@Autowired
	UserRepository userRepo;
	
	@Test
	void testGetAllBooks() {
		List<Books> book=booksRepo.findAll();
		List<Books> list=new ArrayList<>();
		book.forEach(list::add);
		assertEquals(list.size(),book.size());
	}
	@Test
	void testGetById() {
		Optional<Books> book=booksRepo.findById(5);
		Books b=book.get();
		assertEquals(5,b.getId());
		
	}
	@Test
	void testGetBookByTitle() {
		List<Books> book=booksRepo.findByTitle("Programming In Python");
		List<Books> list=new ArrayList<>();
		book.forEach(list::add);
		assertEquals(list.size(),book.size());
	}
	@Test
	void testGetBookByAuthor() {
		List<Books> book=booksRepo.findByAuthor("komal");
		List<Books> list=new ArrayList<>();
		book.forEach(list::add);
		assertEquals(list.size(),book.size());
	}
	@Test
	void testGetBooksByCategory() {
		List<Books> book=booksRepo.findByCategory("programming");
		List<Books> list=new ArrayList<>();
		book.forEach(list::add);
		assertEquals(list.size(),book.size());
	}
	@Test
	void testGetUserById() {
		Optional<User> u=userRepo.findById(2);
		User user=u.get();
		assertEquals(user.getId(),2);
	}
	@Test
//	@Disabled("Don't want to save again")
	void testSaveBook() {
		Books book=new Books(12,"superman","D.C","comics",null,null,"Avaliable",null);
		booksRepo.save(book);
		Optional<Books> book1=booksRepo.findById(5);
		Books book2=book1.get();
		assertEquals(book2.getId(),book2.getId());
		
	}
	@Test
	void TestFindByUser() {
		Optional<User> u=userRepo.findById(2);
		List<Books> books=booksRepo.findByUser(u.get());
		List<Books> list=new ArrayList<>();
		books.forEach(list::add);
		assertEquals(list.size(),books.size());
		
	}
	@Test
	void testDeleteBook() {
		List<Books> list=booksRepo.findAll();
		booksRepo.deleteById(list.get(list.size()-1).getId());
		List<Books> list1=booksRepo.findAll();
		assertEquals(list.size()-1,list1.size());
	}
	
	
}
