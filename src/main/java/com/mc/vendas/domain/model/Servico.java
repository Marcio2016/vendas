package com.mc.vendas.domain.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "servico")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Servico {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
	@Column(nullable = false, length = 255)
	private String descricao;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id" )
	private Cliente cliente;
	
	private BigDecimal valor;

}
