package com.parcialSpring.electiva.repository;

import com.parcialSpring.electiva.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
