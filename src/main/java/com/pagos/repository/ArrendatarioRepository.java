package com.pagos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pagos.model.Arrendatario;
import com.pagos.model.ArrendatarioId;

@Repository
public interface ArrendatarioRepository extends JpaRepository<Arrendatario,ArrendatarioId> {

}
