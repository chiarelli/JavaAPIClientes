package com.github.chiarelli.apiclientes.services;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.github.chiarelli.apiclientes.dtos.ClienteRequest;
import com.github.chiarelli.apiclientes.dtos.ClienteResponse;
import com.github.chiarelli.apiclientes.entities.Clientes;
import com.github.chiarelli.apiclientes.interfaces.ClientesService;
import com.github.chiarelli.apiclientes.repositories.ClientesRepository;

@Service
public class ClienteServiceImpl implements ClientesService {
	
	private final ModelMapper mapper;
	private final ClientesRepository repository;

	public ClienteServiceImpl(ModelMapper mapper, ClientesRepository repository) {
		super();
		this.mapper = mapper;
		this.repository = repository;
	}

	@Override
	public ClienteResponse adicionar(ClienteRequest request) {
		var cliente = mapper.map(request, Clientes.class);
			cliente.setId(UUID.randomUUID());
			
		repository.save(cliente);
		
		return mapper.map(cliente, ClienteResponse.class);
	}

	@Override
	public ClienteResponse atualizar(UUID cliente_id, ClienteRequest request) {
		var cliente = repository.findById(cliente_id)
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
		
		cliente = mapper.map(request, Clientes.class);
		cliente.setId(cliente_id);
		
		repository.save(cliente);
		
		return mapper.map(cliente, ClienteResponse.class);
	}

	@Override
	public ClienteResponse excluir(UUID cliente_id) {
		var cliente = repository.findById(cliente_id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.GONE, "Cliente não encontrado."));
		repository.delete(cliente);
		
		return mapper.map(cliente, ClienteResponse.class);
	}

	@Override
	public Page<ClienteResponse> consultar(int page, int size, String order) {
		var sort = Sort.by(order);		
		var pageable = PageRequest.of(page-1, size, sort);
				
		var clientes = repository.findAll(pageable);
		
		var clientesResp = clientes.stream()
				.map(c -> mapper.map(c, ClienteResponse.class))
				.toList();
		
		return new PageImpl<ClienteResponse>(clientesResp, pageable, clientes.getTotalElements());
	}

	@Override
	public ClienteResponse buscarPorId(UUID cliente_id) {
		var cliente = repository.findById(cliente_id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "Cliente não encontrado."));
		
		return mapper.map(cliente, ClienteResponse.class);
	}

}
