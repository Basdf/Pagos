package com.pagos.dtos;

import java.time.LocalDate;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.pagos.constants.BusinessConstants;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PagoDTO {

	@Schema(example = "123456789")
	@NotNull(message = "El documento no puede ser nulo")
	@Pattern(regexp = "^[0-9]+$", message = "El documento debe ser solo numerico")
	private String documentoIdentificacionArrendatario;

	@Schema(example = "5548A55D")
	@NotNull(message = "El codigo inmueble no puede ser nulo")
	@Pattern(regexp = "^[A-z0-9]+$", message = "El codigo del inmueble debe ser alfanumerico")
	private String codigoInmueble;

	@Schema(example = "10/10/2000", type = "string", pattern = BusinessConstants.FECHA_FORMAT)
	@NotNull(message = "La fecha de pago no puede ser nulo")
	@JsonFormat(pattern = BusinessConstants.FECHA_FORMAT)
	private LocalDate fechaPago;

	@NotNull(message = "El valor pagado no puede ser nulo")
	@Min(value = BusinessConstants.VALOR_PAGADO_MIN)
	@Max(value = BusinessConstants.VALOR_PAGADO_MAX)
	private Integer valorPagado;

}
