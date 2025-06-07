package com.github.chiarelli.apiclientes.dtos;

import java.time.LocalDate;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ClienteResponse {
	
	private UUID id;
	
	private String nome;
	
	private String email;
	
	private String endereco;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataNascimento;
	
	
}
