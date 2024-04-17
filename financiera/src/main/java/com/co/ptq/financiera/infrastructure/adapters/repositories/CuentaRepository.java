package com.co.ptq.financiera.infrastructure.adapters.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.co.ptq.financiera.domain.models.Cuenta;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {

	boolean existsByClienteId(Long idCliente);

	List<Cuenta> findByClienteId(Long clienteId);
}