package com.math.todo.model.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import com.math.todo.model.enums.StatusTarefas;

import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "tarefas", schema = "todo")
@Data
@Builder
public class Tarefas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private StatusTarefas status;

    @Column(name = "observacoes")
    private String observacoes;

    @Column(name = "data_criacao")
    @Convert(converter = Jsr310JpaConverters.LocalDateConverter.class)
    private LocalDate data_criacao;

    @Column(name = "data_atualizacao")
    private LocalDate data_atualizacao;
}