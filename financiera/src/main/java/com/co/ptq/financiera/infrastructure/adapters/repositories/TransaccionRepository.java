package com.co.ptq.financiera.infrastructure.adapters.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.co.ptq.financiera.domain.models.Transaccion;

@Repository
public interface TransaccionRepository extends JpaRepository<Transaccion, Long> {
}