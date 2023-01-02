package com.example.springbootlibrary.dao;

import com.example.springbootlibrary.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository <Book, Long>{

}
