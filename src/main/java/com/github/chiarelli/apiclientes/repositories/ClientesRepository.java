package com.github.chiarelli.apiclientes.repositories;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.github.chiarelli.apiclientes.entities.Clientes;

@Repository
public interface ClientesRepository extends MongoRepository<Clientes, UUID> {

}
