package com.mc.vendas.domain.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;



@Entity
@Table(name = "cliente")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
	@Column(nullable = false, length = 255)
	@NotEmpty(message = "{nome.obrigatorio}")
	private String nome;
	
	@Column(nullable = false, length = 11)
	@NotNull(message = "{cpf.obrigatorio}")
	@CPF(message = "{cpf.invalido}")
	private String cpf;
	
	@Column(name = "created_at", updatable = false)
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate createdAt;
	
	@PrePersist
	public void prePersist() {
		setCreatedAt(LocalDate.now());
	}
}
