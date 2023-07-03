package com.pack.library.LibraryManagementSystem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pack.library.LibraryManagementSystem.Repository.BooksRepository;
import com.pack.library.LibraryManagementSystem.Repository.UserRepository;
import com.pack.library.LibraryManagementSystem.entity.Books;
import com.pack.library.LibraryManagementSystem.entity.BooksStatus;
import com.pack.library.LibraryManagementSystem.entity.User;
import com.pack.library.LibraryManagementSystem.service.LibraryServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest
class ControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	@MockBean
	UserRepository userRepo;
	@MockBean
	BooksRepository booksRepo;
	@MockBean
	LibraryServiceImpl libraryService;
	@Autowired
	private ObjectMapper mapper;

	@Test
	void testFindById() throws Exception {
		Books book=new Books(10,"avengers","komal","comics",null,null,"Available",null);
		Mockito.when(booksRepo.save(book)).thenReturn(book);
		Mockito.when(libraryService.fetchBookById(ArgumentMatchers.any())).thenReturn(book);
		MvcResult result=mockMvc.perform(get("/library/findById/10")).andReturn();
		String res=result.getResponse().getContentAsString();
		Books b= new ObjectMapper().readValue(res, Books.class);
		assertEquals(b.getId(),book.getId());
	}
	@Test
	void testGetBooksByTitle() throws Exception {
		Books book=new Books(1,"avengers","komal","comics",null,null,"Available",null);
		Books book1=new Books(2,"avengers","komal","comics",null,null,"Available",null);
		List<Books> b=new ArrayList<>();
		b.add(book1);
		b.add(book);
		Mockito.when(libraryService.getBooksByTitle("avengers")).thenReturn(b);
		MvcResult result=mockMvc.perform(get("/library/findByTitle/{title}","avengers")).
				andExpect(status().isOk()).
				andReturn();
		String res=result.getResponse().getContentAsString();
		List<Books> lb=Arrays.asList(mapper.readValue(res, Books[].class));
		assertEquals(b.size(),lb.size());
		
	}
	@Test
	void testGetBookByCategory() throws Exception {
		Books book=new Books(1,"avengers","komal","comics",null,null,"Available",null);
		Books book1=new Books(2,"avengers","komal","comics",null,null,"Available",null);
		List<Books> b=new ArrayList<>();
		b.add(book1);
		b.add(book);
		Mockito.when(libraryService.getBooksByCategory("comics")).thenReturn(b);
		MvcResult result=mockMvc.perform(get("/library/findByCategory/{category}","comics")).andReturn();
		String res=result.getResponse().getContentAsString();
		List<Books> list=Arrays.asList(mapper.readValue(res, Books[].class));
		assertEquals(list.size(),b.size());
	}
	@Test
	void testGetBookByAuthor() throws Exception {
		Books book=new Books(1,"avengers","komal","comics",null,null,"Available",null);
		Books book1=new Books(2,"avengers","komal","comics",null,null,"Available",null);
		List<Books> b=new ArrayList<>();
		b.add(book1);
		b.add(book);
		Mockito.when(libraryService.getBooksByAuthor("komal")).thenReturn(b);
		MvcResult result=mockMvc.perform(get("/library/findByAuthor/{author}","komal")).andReturn();
		String res=result.getResponse().getContentAsString();
		List<Books> list=Arrays.asList(mapper.readValue(res, Books[].class));
		assertEquals(list.size(),b.size());
	}
	@Test
	void testGetAllBooks() throws Exception {
		Books book=new Books(1,"avengers","komal","comics",null,null,"Available",null);
		Books book1=new Books(2,"avengers","komal","comics",null,null,"Available",null);
		List<Books> b=new ArrayList<>();
		b.add(book1);
		b.add(book);
		Mockito.when(libraryService.getAllBooks()).thenReturn(b);
		MvcResult result=mockMvc.perform(get("/library/booksList")).andReturn();
		String res=result.getResponse().getContentAsString();
		List<Books> list=Arrays.asList(mapper.readValue(res, Books[].class));
		assertEquals(list.size(),b.size());
	}
	@Test
	void testBorrowBooks() throws Exception {
		Books book=new Books(1,"avengers","komal","comics",null,null,"Available",null);
		User u=new User(1,"komal",null);
		Mockito.when(booksRepo.save(book)).thenReturn(book);
		Mockito.when(libraryService.update(1,1)).thenReturn(book);
		String bookJson=mapper.writeValueAsString(book);
		Mockito.when(libraryService.findUserById(1)).thenReturn(u);
		MvcResult result=mockMvc.perform(put("/library/borrow/1/User/1").contentType(MediaType.APPLICATION_JSON)
					.characterEncoding("utf-8").content(bookJson).accept(MediaType.APPLICATION_JSON))
			.andReturn();
		String res=result.getResponse().getContentAsString();
		Books bo= new ObjectMapper().readValue(res, Books.class);
		assertEquals(bo.getBorrowedDate(),book.getBorrowedDate());
		
	}
	@Test
	void testDeleteById() throws Exception {
		Books book=new Books(1,"avengers","komal","comics",null,null,"Available",null);
		Books book1=new Books(2,"avengers","komal","comics",null,null,"Available",null);
		List<Books> b=new ArrayList<>();
		b.add(book1);
		b.add(book);
		mockMvc.perform(delete("/library/delete/{id}",1))
	       .andExpect(status().isOk());
		verify(libraryService,times(1)).deleteBook(1);
	}
	@Test
	void testUpdateBook() throws Exception {
		Books book=new Books(1,"avengers","komal","comics",null,null,"Available",null);
		Mockito.when(libraryService.save(book)).thenReturn(book);
		String bookJson=mapper.writeValueAsString(book);
		MvcResult result=mockMvc.perform(post("/library/update/1").contentType(MediaType.APPLICATION_JSON)
					.characterEncoding("utf-8").content(bookJson).accept(MediaType.APPLICATION_JSON))
			.andReturn();
		String res=result.getResponse().getContentAsString();
		Books b=mapper.readValue(res, Books.class);
		assertEquals(book.getAuthor(),b.getAuthor());
	}
	@Test
	void testReturnBook() throws Exception {
		Books book=new Books(1,"avengers","komal","comics",null,null,"Borrowed",null);
		book.setBorrowedDate(LocalDate.now());
		book.setReturnDate(LocalDate.of(2023, 07, 07));
		Mockito.when(libraryService.returnDate(1, "2023-07-01")).thenReturn(book);
		String bookJson=mapper.writeValueAsString(book);
		MvcResult result=mockMvc.perform(put("/library/borrow/1/ReturnDate/2023-07-01").contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("utf-8").content(bookJson).accept(MediaType.APPLICATION_JSON))
		.andReturn();
		String res=result.getResponse().getContentAsString();
		Books b=mapper.readValue(res, Books.class);
		assertEquals(b.getStatus(),book.getStatus());
		
	}
	@Test
	void testMemberById() throws Exception {
		User u=new User(1,"komal",null);
		Books book=new Books(1,"avengers","komal","comics",null,null,"Available",null);
		book.setUser(u);
		Books book1=new Books(2,"avengers","komal","comics",null,null,"Available",null);
		book1.setUser(u);
		List<Books> b=new ArrayList<>();
		b.add(book1);
		b.add(book);
		Mockito.when(libraryService.getBooksByMemberId(1)).thenReturn(b);
		MvcResult result=mockMvc.perform(get("/library/fetchByMemberId/1")).andReturn();
		String res=result.getResponse().getContentAsString();
		List<Books> books=Arrays.asList(mapper.readValue(res, Books[].class));
		assertEquals(books.size(),b.size());
	}
	@Test
	void testGetBooksNameByTitle() throws Exception {
		User u=new User(1,"komal",null);
		BooksStatus bs=new BooksStatus("avengers","Available",null);
		BooksStatus bs1=new BooksStatus("avengers","Borrowed",u);
		List<BooksStatus> b=new ArrayList<>();
		b.add(bs);
		b.add(bs1);
		Mockito.when(libraryService.fetchByTitle("avengers")).thenReturn(b);
		MvcResult result=mockMvc.perform(get("/library/fetchByTitle/avengers")).andReturn();
		String res=result.getResponse().getContentAsString();
		List<BooksStatus> books=Arrays.asList(mapper.readValue(res, BooksStatus[].class));
		assertEquals(b.size(),books.size());
	}
}
