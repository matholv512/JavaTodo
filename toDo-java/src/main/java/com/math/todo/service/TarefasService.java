package com.math.todo.service;

import com.math.todo.model.entity.Tarefas;

public interface TarefasService {
	Tarefas verificar(Long id);
	Tarefas salvarTarefas(Tarefas tarefas);
	void validarId(Long id);
}
