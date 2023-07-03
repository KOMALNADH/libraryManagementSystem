package com.pack.library.LibraryManagementSystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.pack.library.LibraryManagementSystem.Repository.BooksRepository;
import com.pack.library.LibraryManagementSystem.Repository.UserRepository;
import com.pack.library.LibraryManagementSystem.entity.Books;
import com.pack.library.LibraryManagementSystem.entity.User;

import java.time.LocalDate;
import java.util.*;

@SpringBootApplication
public class LibraryManagementSystemApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(LibraryManagementSystemApplication.class, args);
	}
	@Autowired
	BooksRepository booksRepo;
	@Autowired
	UserRepository userRepo;
	@Override
	public void run(String... args) throws Exception {
		//saveBooks();
		//saveUser();
	}

//	private void saveUser() {
//		List<User> userList=new ArrayList<>();
////		userList.add(new User(1,"komal",));
////		userList.add(new User(2,"saran"));
////		userList.add(new User(3,"pavan"));
//		userRepo.saveAll(userList);
//	}
//
//	private void saveBooks() {
//		List<Books> booksList=new ArrayList<>();
//		LocalDate date=LocalDate.now();
//		booksList.add(new Books(1,"Laws","verma","physics",null,null,"Available",null));
//		booksList.add(new Books(2,"Programming In Java","Komal","programming",null,null,"Available",null));
//		booksList.add(new Books(3,"Programming In Python","Komal","programming",null,null,"Available",null));
//		booksRepo.saveAll(booksList);
//	}

}
