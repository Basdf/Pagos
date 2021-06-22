package com.pagos.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArrendatarioId implements Serializable{

	private String documentoIdentificacionArrendatario;

	private String codigoInmueble;

}
