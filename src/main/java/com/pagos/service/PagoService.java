package com.pagos.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pagos.repository.PagoRepository;
import com.pagos.responses.DetailResponse;
import com.pagos.constants.BusinessConstants;
import com.pagos.dtos.PagoDTO;
import com.pagos.exception.DuplicateDataException;
import com.pagos.exception.PairDayException;
import com.pagos.exception.OverflowValueException;
import com.pagos.mappers.PagoMapper;
import com.pagos.model.Pago;
import com.pagos.model.PagoId;

@Service
public class PagoService {

	@Autowired
	private PagoRepository pagoRepository;

	public List<PagoDTO> getAllPagos() {
		return pagoRepository.findAll().stream().map(PagoMapper::entityToDto).collect(Collectors.toList());
	}

	public PagoDTO getPago(PagoId pagoId) {
		return pagoRepository.findById(pagoId).map(PagoMapper::entityToDto).orElseThrow();
	}

	public DetailResponse createPago(PagoDTO pagoDto) {
		PagoId pagoId = PagoId.builder()
				.documentoIdentificacionArrendatario(pagoDto.getDocumentoIdentificacionArrendatario())
				.codigoInmueble(pagoDto.getCodigoInmueble()).fechaPago(pagoDto.getFechaPago()).build();
		// Valido si existe ya el pago
		pagoRepository.findById(pagoId).ifPresent(s -> {
			throw new DuplicateDataException(
					"Ocurrió un problema Procesando el pago, no se pueden crear dos pagos el mismo dia");
		});

		// Valido si el dia es impar
		if (pagoDto.getFechaPago().getDayOfMonth() % 2 == 0)
			throw new PairDayException("lo siento pero no se puede recibir el pago por decreto de administración");

		// Validacion del costo
		LocalDate fechaPago = pagoDto.getFechaPago();
		LocalDate start = fechaPago.withDayOfMonth(1);

		List<Pago> pagos = pagoRepository
				.findAllByDocumentoIdentificacionArrendatarioAndCodigoInmuebleAndFechaPagoBetween(
						pagoDto.getDocumentoIdentificacionArrendatario(), pagoDto.getCodigoInmueble(), start,
						fechaPago);

		int valorPagadoTotal = pagos.stream().mapToInt(Pago::getValorPagado).sum() + pagoDto.getValorPagado();

		if (valorPagadoTotal > BusinessConstants.VALOR_PAGADO_MAX)
			throw new OverflowValueException(
					"El valor total pagado no puede superar a " + BusinessConstants.VALOR_PAGADO_MAX);

		pagoRepository.save(PagoMapper.dtoToEntity(pagoDto));
		return DetailResponse.builder().timestamp(new Date())
				.details(valorPagadoTotal < BusinessConstants.VALOR_PAGADO_MAX
						? "Gracias por tu abono, sin embargo recuerda que te hace falta pagar "
								+ (BusinessConstants.VALOR_PAGADO_MAX - valorPagadoTotal)
						: "Gracias por pagar todo tu arriendo")
				.request("Crear Pago").build();
	}

	public void deletePago(PagoId pagoId) {
		pagoRepository.deleteById(pagoId);

	}

}
