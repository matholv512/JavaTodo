package com.math.todo.model.repository;

import java.time.LocalDate;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.math.todo.model.entity.Tarefas;
import com.math.todo.model.enums.StatusTarefas;

@ExtendWith( SpringExtension.class )
@DataJpaTest
//@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class TarefasRepositoryTests {
	@Autowired
	TarefasRepository repository;
	
	@Autowired
	TestEntityManager entityManager;
	
	@Test
	public void devePersistirATarefaNaBaseDeDados() {
		//cenário
		Tarefas tarefa = criarTarefa();
		//ação
		Tarefas tarefaSalva = repository.save(tarefa);
				
		//verificação
		Assertions.assertThat(tarefaSalva.getId()).isNotNull();
	}
	
	@Test
	public void deveAlterarATarefaNaBaseDeDados() {
		//cenário
        Tarefas tarefa = criarTarefa();
        Tarefas tarefaSalva = repository.save(tarefa);

        tarefaSalva.setNome("Título 2");
        tarefaSalva.setDescricao("Descrição 2");
        tarefaSalva.setStatus(StatusTarefas.FAZENDO);
        tarefaSalva.setObservacoes("Observação 2");
        tarefaSalva.setData_atualizacao(LocalDate.now());

        //ação
        Tarefas tarefaAtualizada = repository.save(tarefaSalva);
        //verificação
        Assertions.assertThat(tarefaAtualizada.getId()).isEqualTo(tarefaSalva.getId());
        Assertions.assertThat(tarefaAtualizada.getNome()).isEqualTo("Título 2");
        Assertions.assertThat(tarefaAtualizada.getDescricao()).isEqualTo("Descrição 2");
        Assertions.assertThat(tarefaAtualizada.getStatus()).isEqualTo(StatusTarefas.FAZENDO);
        Assertions.assertThat(tarefaAtualizada.getObservacoes()).isEqualTo("Observação 2");
    }
	
	@Test
	public void deveExcluirATarefaNaBaseDeDados() {
        //cenário
        Tarefas tarefa = criarTarefa();
        Tarefas tarefaSalva = repository.save(tarefa);

        //ação
        repository.delete(tarefaSalva);

        //verificação
        boolean exists = repository.existsById(tarefaSalva.getId());
        Assertions.assertThat(exists).isFalse();
    }
	
	@Test
	public void deveRetornarVazioAoBuscarTarefaPorIdQuandoNaoExisteNaBase() {
	    // cenário

	    // verificação
	    boolean exists = repository.existsById(1);

	    Assertions.assertThat(exists).isFalse();
	}
	
	@Test
	public void deveVerificarAExistenciaDeUmaTarefa() {
	    // cenário
	    Tarefas tarefa = criarTarefa();
	    entityManager.persist(tarefa);

	    // ação/execução
	    boolean result = repository.existsById(tarefa.getId());

	    // verificação
	    Assertions.assertThat(result).isTrue();
	}
	
	@Test
	public void deveRetornarFalsoQuandoNaoHouverTarefaCadastradaComOId() {
		//cenário
		
		//ação
		boolean result = repository.existsById(1l);
		
		//verificação
		Assertions.assertThat(result).isFalse();
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
