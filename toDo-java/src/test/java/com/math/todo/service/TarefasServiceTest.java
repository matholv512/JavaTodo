package com.math.todo.service;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.math.todo.exception.ErroVerificacao;
import com.math.todo.model.entity.Tarefas;
import com.math.todo.model.enums.StatusTarefas;
import com.math.todo.model.repository.TarefasRepository;
import com.math.todo.service.impl.TarefasServiceImpl;


@ExtendWith(SpringExtension.class)
//@ActiveProfiles("test")
public class TarefasServiceTest {
	@SpyBean
	TarefasServiceImpl service;
	
	@MockBean
	TarefasRepository repository;
	
	@Test
	public void deveValidarId() {
		//cenário
		Mockito.when(repository.existsById(Mockito.anyLong())).thenReturn(false);
		
		//acao
		service.VerificaAExistenciaDaTarefa(1l);
	}
	
	@Test
	public void deveVerificarUmaTarefaComSucesso() {
		//cenário
		Long id = 1l;
		String nome = "nome";
		String descricao = "descricao";
		StatusTarefas status = (StatusTarefas.CONCLUIDO);
		String observacoes = "observacoes";
		
		Tarefas tarefas = Tarefas.builder().nome(nome).descricao(descricao).status(status).observacoes(observacoes).id(id).build();
		Mockito.when(repository.findById(id)).thenReturn(Optional.of(tarefas));
		
		//ação
		Tarefas result = service.VerificaAExistenciaDaTarefa(id);
		
		//verificação
		org.assertj.core.api.Assertions.assertThat(result).isNotNull();
	}
	
	@Test
	public void deveLancarErroQuandoNaoEncontrarTarefaCadastradaComOIdInformado() {
		//cenário
		Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
		
		Long id = 1l;
		//ação
		Throwable exception =
				org.assertj.core.api.Assertions.catchThrowable( 
						() -> service.VerificaAExistenciaDaTarefa(id));
		
		//verificação
		org.assertj.core.api.Assertions.assertThat(exception)
			.isInstanceOf(ErroVerificacao.class)
			.hasMessage("Tarefa não encontrada para o Id informado!");
	}
}
