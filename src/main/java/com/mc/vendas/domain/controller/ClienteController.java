package com.mc.vendas.domain.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.mc.vendas.domain.model.Cliente;
import com.mc.vendas.domain.repository.ClienteRepository;

@RestController
@RequestMapping("/clientes")
@CrossOrigin("http://localhost:4200")
public class ClienteController {
	
	@Autowired
	private ClienteRepository repository;
	
	@GetMapping
	public List<Cliente> Buscar(){
		return repository.findAll(); 
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente salvar(@RequestBody @Valid Cliente cliente) {		
		return repository.save(cliente);		 
	}
	
	@GetMapping("/{id}")
	public Cliente buscarPorId(@PathVariable Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,  "Cliente não encontrado"));		 
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Long id) {
		repository.findById(id)
		.map(cliente -> {
			repository.delete(cliente);
			return Void.TYPE;
		})
		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));		
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizar(@PathVariable Long id, @RequestBody @Valid Cliente cliente) {
		repository.findById(id)
		.map(c -> {
			BeanUtils.copyProperties(cliente, c, "id");
//			c.setNome(cliente.getNome());
//			c.setCpf(cliente.getCpf());						
			return repository.save(c);
		})
		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,  "Cliente não encontrado"));
	}

}
