package com.pagos.model;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PagoId implements Serializable {

	private String documentoIdentificacionArrendatario;
	private String codigoInmueble;
	private LocalDate fechaPago;

}
