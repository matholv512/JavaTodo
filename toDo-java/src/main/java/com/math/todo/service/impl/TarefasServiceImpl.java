package com.math.todo.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.math.todo.exception.ErroVerificacao;
import com.math.todo.model.repository.TarefasRepository;
import com.math.todo.service.TarefasService;
import com.math.todo.model.entity.Tarefas;

@Service
public class TarefasServiceImpl implements TarefasService{
	private TarefasRepository repository;
	
	public TarefasServiceImpl(TarefasRepository repository) {
		super();
		this.repository = repository;
	}
	
	@Override
	public Tarefas verificar(Long id) {
		Optional<Tarefas> tarefas = repository.findById(id);
		
		if(!tarefas.isPresent()) {
			throw new ErroVerificacao("Tarefa não encontrada para o ID informado!");
		}
		return tarefas.get();
	}
	
	@Override
	@Transactional
	public Tarefas salvarTarefas(Tarefas tarefas) {
		validarId(tarefas.getId());
		return repository.save(tarefas);
	}
	
	@Override
	public void validarId(Long id) {
		boolean existe = repository.existsById(id);
		if(existe) {
			throw new ErroVerificacao("Id já existente");
		}
	}
}
