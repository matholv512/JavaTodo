package com.math.todo.service.impl;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.math.todo.exception.ErroVerificacao;
import com.math.todo.model.repository.TarefasRepository;
import com.math.todo.service.TarefasService;
import com.math.todo.model.entity.Tarefas;
import com.math.todo.model.enums.StatusTarefas;

@Service
public class TarefasServiceImpl implements TarefasService{
	private TarefasRepository repository;
	
	public TarefasServiceImpl(TarefasRepository repository) {
		super();
		this.repository = repository;
	}
	
	@Override
	public Tarefas VerificaAExistenciaDaTarefa(Long id) {
	    Optional<Tarefas> tarefas = repository.findById(id);
	    if (!tarefas.isPresent()) {
	        throw new ErroVerificacao("Tarefa não encontrada para o ID informado!");
	    } else {
	        return tarefas.get();
	    }
	}
	
	@Override
	public Tarefas listarTarefas(Long id) {
		Optional<Tarefas> tarefas = repository.findById(id);
		VerificaAExistenciaDaTarefa(id);
		return tarefas.get();
	}
	
	@Override
	@Transactional
	public Tarefas salvarTarefas(Tarefas tarefas) {
	    return repository.save(tarefas);
	}
	
	@Override
	@Transactional
	public Tarefas atualizarTarefas(Tarefas tarefas) {
	    Tarefas tarefaExistente = VerificaAExistenciaDaTarefa(tarefas.getId());
	    tarefaExistente.setNome(tarefas.getNome());
	    tarefaExistente.setDescricao(tarefas.getDescricao());
	    tarefaExistente.setStatus(tarefas.getStatus());
	    tarefaExistente.setObservacoes(tarefas.getObservacoes());
	    tarefaExistente.setData_atualizacao(LocalDate.now());
	    return repository.save(tarefaExistente);
	}

    @Override
    @Transactional
    public Tarefas deletarTarefas(Long id) {
        Tarefas tarefa = VerificaAExistenciaDaTarefa(id);
        repository.deleteById(id);
        return tarefa;
    }
    
    public static Tarefas criarTarefa() {
    	return Tarefas.builder()
                .nome("Título da tarefa")
                .descricao("Mussum Ipsum, cacilds vidis litro abertis. Eu nunca mais boto a boca num copo de cachaça, agora eu só uso canudis! Leite de capivaris, leite de mula manquis sem cabeça.")
                .status(StatusTarefas.CONCLUIDO)
                .observacoes("Concluir até a data 25/05/2024")
                .data_criacao(LocalDate.now())
                .build();
	}
}
