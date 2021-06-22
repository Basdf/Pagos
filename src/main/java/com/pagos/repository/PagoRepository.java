package com.pagos.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pagos.model.Pago;
import com.pagos.model.PagoId;

@Repository
public interface PagoRepository extends JpaRepository<Pago, PagoId> {

	List<Pago> findAllByDocumentoIdentificacionArrendatarioAndCodigoInmuebleAndFechaPagoBetween(
			String documentoIdentificacionArrendatario, String codigoInmueble, LocalDate fechaPagoStart,
			LocalDate fechaPagoEnd);

}
