package com.github.chiarelli.apiclientes.interfaces;

import java.util.UUID;

import org.springframework.data.domain.Page;

import com.github.chiarelli.apiclientes.dtos.ClienteRequest;
import com.github.chiarelli.apiclientes.dtos.ClienteResponse;

public interface ClientesService {
	
	ClienteResponse adicionar(ClienteRequest request);
	
	ClienteResponse atualizar(UUID cliente_id, ClienteRequest request);
	
	ClienteResponse excluir(UUID cliente_id);
	
	Page<ClienteResponse> consultar(int page, int size, String order);
	
	ClienteResponse buscarPorId(UUID cliente_id);
}
