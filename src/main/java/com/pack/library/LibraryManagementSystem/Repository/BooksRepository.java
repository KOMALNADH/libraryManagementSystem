package com.pack.library.LibraryManagementSystem.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pack.library.LibraryManagementSystem.entity.Books;
import com.pack.library.LibraryManagementSystem.entity.BooksStatus;
import com.pack.library.LibraryManagementSystem.entity.User;

@Repository
public interface BooksRepository extends JpaRepository<Books,Integer>{
	
	List<Books> findByTitle(String title);
	List<Books> findByAuthor(String author);
	List<Books> findByCategory(String category);
	List<Books> findByUser(User user);
	@Query("select new com.pack.library.LibraryManagementSystem.entity.BooksStatus(b.title,b.status,b.user) from Books b where "
			+ "b.title=?1")
	List<BooksStatus> findBookTitleAndStatus(String title);
	
}
