package com.co.ptq.financiera.infrastructure.adapters.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.co.ptq.financiera.domain.models.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	// Puedes agregar métodos personalizados aquí si es necesario
}
