package com.pagos.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Arrendatarios")
@IdClass(ArrendatarioId.class)
public class Arrendatario {

	@Id
	private String documentoIdentificacionArrendatario;
	@Id
	private String codigoInmueble;
	@Column
	private String nombre;
	@Column
	private String correo;
	

}
