package com.github.chiarelli.apiclientes.controllers;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.chiarelli.apiclientes.dtos.ClienteRequest;
import com.github.chiarelli.apiclientes.dtos.ClienteResponse;
import com.github.chiarelli.apiclientes.dtos.PageCollectionJsonResponse;
import com.github.chiarelli.apiclientes.interfaces.ClientesService;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClientesController {
	
	private final ClientesService clientesService;
	
	public ClientesController(ClientesService clientesService) {
		super();
		this.clientesService = clientesService;
	}

	// CREATE - Criar um novo cliente
    @PostMapping
    public ResponseEntity<ClienteResponse> criarCliente(@RequestBody ClienteRequest cliente) {
        var clientCreated = clientesService.adicionar(cliente);
    	
        return new ResponseEntity<>(clientCreated, HttpStatus.CREATED);
    }

    // READ - Listar todos os clientes
    @GetMapping
    public PageCollectionJsonResponse<ClienteResponse> listarClientes(
       @RequestParam(defaultValue = "1") @Min(1) int page,
       @RequestParam(defaultValue = "25") @Max(100) int size,
       @RequestParam(defaultValue = "nome") String sort
	) {
        var pageResponse = clientesService.consultar(page, size, sort);
        var resp = new PageCollectionJsonResponse<>(pageResponse);
        System.out.println("page: " + page + " size: " + size + " sort: " + sort);
            resp.setCurrentPage(page);
        return resp;
    }

    // READ - Buscar um cliente por ID
    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponse> buscarCliente(@PathVariable UUID id) {
        return new ResponseEntity<>(clientesService.buscarPorId(id), HttpStatus.OK);
    }

    // UPDATE - Atualizar um cliente
    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponse> atualizarCliente(@PathVariable UUID id, @RequestBody ClienteRequest cliente) {
    	var clientUpdated = clientesService.atualizar(id, cliente);
        return new ResponseEntity<>(clientUpdated, HttpStatus.OK);
    }

    // DELETE - Remover um cliente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable UUID id) {
    	clientesService.excluir(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
