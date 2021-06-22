package com.pagos.mappers;

import com.pagos.dtos.PagoDTO;
import com.pagos.model.Pago;

public class PagoMapper {
	 public static PagoDTO entityToDto(Pago pago) {
	        return PagoDTO.builder()
	        		.documentoIdentificacionArrendatario(pago.getDocumentoIdentificacionArrendatario())
	        		.codigoInmueble(pago.getCodigoInmueble())
	        		.valorPagado(pago.getValorPagado())
	        		.fechaPago(pago.getFechaPago())
	                .build();
	    }
	 public static Pago dtoToEntity(PagoDTO pagoDTO) {
	        return Pago.builder()
	        		.documentoIdentificacionArrendatario(pagoDTO.getDocumentoIdentificacionArrendatario())
	        		.codigoInmueble(pagoDTO.getCodigoInmueble())
	        		.valorPagado(pagoDTO.getValorPagado())
	        		.fechaPago(pagoDTO.getFechaPago())
	                .build();
	    }

}
