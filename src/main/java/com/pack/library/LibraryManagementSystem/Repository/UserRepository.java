package com.pack.library.LibraryManagementSystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pack.library.LibraryManagementSystem.entity.User;
@Repository
public interface UserRepository extends JpaRepository<User,Integer>{

}
