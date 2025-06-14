package com.github.chiarelli.apiclientes.dtos;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ClienteRequest {
	
	@NotBlank(message = "Nome é obrigatório")
	@Size(min = 8, max = 150, message = "O nome deve ter de 8 a 150 caracteres")
	private String nome;
	
	@Email(message = "Email é inválido")
	@NotBlank(message = "Email é obrigatório")
	private String email;
	
	@NotBlank(message = "Endereco é obrigatório")
	@Size(min = 8, max = 250, message = "O endereço deve ter de 8 a 250 caracteres")
	private String endereco;
	
	@NotNull(message = "Data de nascimento é obrigatória.")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataNascimento;
	
}
