package com.routine.mainapp.definitionOps.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.routine.mainapp.definitionOps.entity.Book;

public interface BookRepo extends JpaRepository<Book, Long>{
    
}
