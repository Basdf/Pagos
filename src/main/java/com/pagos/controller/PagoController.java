package com.pagos.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pagos.service.PagoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import com.pagos.constants.BusinessConstants;
import com.pagos.constants.EndpointsConstants;
import com.pagos.dtos.PagoDTO;
import com.pagos.model.PagoId;
import com.pagos.responses.DetailResponse;
import com.pagos.responses.ErrorResponse;

@RestController
@RequestMapping(EndpointsConstants.ENDPOINT_PAGOS)
@Tag(name = "Pagos", description = "The pagos API")
public class PagoController {

	@Autowired
	private PagoService pagoService;

	@Operation(summary = "Get all Pagos")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found all Pagos", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = PagoDTO.class))})})
	@GetMapping
	public ResponseEntity<List<PagoDTO>> getAllPagos() {
		return ResponseEntity.status(HttpStatus.OK)
				.body(pagoService.getAllPagos());
	}

	@Operation(summary = "Get Pago by id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found Pago", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = PagoDTO.class))}),
			@ApiResponse(responseCode = "400", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))}),
			@ApiResponse(responseCode = "404", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))})})
	@GetMapping(value = EndpointsConstants.ENDPOINT_GETPAGO)
	public ResponseEntity<PagoDTO> getPago(@PathVariable String id,
			@RequestParam(value = "codigo", required = true) String codigo,
			@RequestParam(value = "fecha", required = true) @DateTimeFormat(pattern = BusinessConstants.FECHA_FORMAT) LocalDate fecha) {
		PagoId pagoId = PagoId.builder().documentoIdentificacionArrendatario(id)
				.codigoInmueble(codigo).fechaPago(fecha).build();
		return ResponseEntity.status(HttpStatus.OK)
				.body(pagoService.getPago(pagoId));
	}

	@Operation(summary = "Create Pago")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Pago Created", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = DetailResponse.class))}),
			@ApiResponse(responseCode = "400", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))}),
			@ApiResponse(responseCode = "409", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))}),})
	@PostMapping
	public ResponseEntity<DetailResponse> createPago(
			@Valid @RequestBody PagoDTO Pago) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(pagoService.createPago(Pago));
	}

	@Operation(summary = "Delete Pago")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Pago Deleted", content = @Content),
			@ApiResponse(responseCode = "400", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))}),
			@ApiResponse(responseCode = "404", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))})})
	@DeleteMapping(value = EndpointsConstants.ENDPOINT_DELETEPAGO)
	public ResponseEntity<Void> deletePago(@PathVariable String id,
			@RequestParam(value = "codigo", required = true) String codigo,
			@RequestParam(value = "fecha", required = true) @DateTimeFormat(pattern = BusinessConstants.FECHA_FORMAT) LocalDate fecha) {
		PagoId pagoId = PagoId.builder().documentoIdentificacionArrendatario(id)
				.codigoInmueble(codigo).fechaPago(fecha).build();
		pagoService.deletePago(pagoId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
