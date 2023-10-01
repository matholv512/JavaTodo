package com.math.todo.service;

import com.math.todo.model.entity.Tarefas;

public interface TarefasService {
	Tarefas VerificaAExistenciaDaTarefa(Long id);
	Tarefas listarTarefas(Long id);
	Tarefas salvarTarefas(Tarefas tarefas);
	Tarefas atualizarTarefas(Tarefas tarefas);
	Tarefas deletarTarefas(Long id);
}
