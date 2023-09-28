package com.math.todo.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.math.todo.model.entity.Tarefas;

public interface TarefasRepository extends JpaRepository<Tarefas, Long> {
	boolean existsById(long id);
	
	Optional<Tarefas> findById(long id);
}
