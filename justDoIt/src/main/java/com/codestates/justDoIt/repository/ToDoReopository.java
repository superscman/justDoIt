package com.codestates.justDoIt.repository;

import com.codestates.justDoIt.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ToDoReopository extends JpaRepository<Todo, Long> {

    Optional<Todo> findByTitle(String title);
}
