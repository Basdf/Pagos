package com.pagos.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Pagos")
@IdClass(PagoId.class)
public class Pago {

	@Id
	private String documentoIdentificacionArrendatario;
	@Id
	private String codigoInmueble;
	@Id
	private LocalDate fechaPago;
	@Column
	private int valorPagado;

}
