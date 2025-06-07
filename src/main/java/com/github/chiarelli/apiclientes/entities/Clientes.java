package com.github.chiarelli.apiclientes.entities;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Data;

@Document(collection = "clientes")
@Data
public class Clientes {
	
	@Id
	private UUID id;

	@Field("nome_cliente")
	private String nome;

	@Field("email_cliente")
	private String email;

	@Field("endereco_cliente")
	private String endereco;

	@DateTimeFormat(iso = ISO.DATE)
	@Field("data_nasc_cliente")
	private LocalDate dataNascimento;
	
}
