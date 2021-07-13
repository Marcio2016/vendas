package com.mc.vendas.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mc.vendas.domain.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}
