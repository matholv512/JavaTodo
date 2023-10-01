package com.math.todo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.math.todo.service.impl.TarefasServiceImpl;
import com.math.todo.exception.ErroVerificacao;
import com.math.todo.model.entity.Tarefas;
import com.math.todo.model.enums.StatusTarefas;

@SpringBootApplication
public class TodoApplication {
	public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(TodoApplication.class, args);

        TarefasServiceImpl tarefasService = context.getBean(TarefasServiceImpl.class);

        // Validando o CRUD:
        // Criar tarefa para teste 
        //Tarefas novaTarefa = TarefasServiceImpl.criarTarefa();

        //Tarefas tarefaSalva = tarefasService.salvarTarefas(novaTarefa);

        //System.out.println("Tarefa criada e salva: " + tarefaSalva);
        
        // Atualizando uma tarefa teste
        //Long idTarefaExistente = 1L;
        //Tarefas tarefaExistente = tarefasService.listarTarefas(idTarefaExistente);
        //tarefaExistente.setNome("Novo Nome");
        //tarefaExistente.setDescricao("Nova Descrição");
        //tarefaExistente.setStatus(StatusTarefas.FAZENDO);
        
        //Tarefas tarefaAtualizada = tarefasService.atualizarTarefas(tarefaExistente);

        //System.out.println("Tarefa atualizada: " + tarefaAtualizada);
        
        // Deletando uma tarefa
        //Long idTarefa = 10L;
        
        //try {
            //Tarefas tarefaExcluida = tarefasService.deletarTarefas(idTarefa);
            //System.out.println("Tarefa excluída: " + tarefaExcluida);
        //} catch (ErroVerificacao e) {
        	//System.out.println(e.getMessage());
        //}
        
    }
}
