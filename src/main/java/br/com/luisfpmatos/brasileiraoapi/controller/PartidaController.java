package br.com.luisfpmatos.brasileiraoapi.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.luisfpmatos.brasileiraoapi.dto.PartidaDTO;
import br.com.luisfpmatos.brasileiraoapi.dto.PartidaResponseDTO;
import br.com.luisfpmatos.brasileiraoapi.entity.Partida;
import br.com.luisfpmatos.brasileiraoapi.exception.StandardError;
import br.com.luisfpmatos.brasileiraoapi.service.PartidaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api("Api de partidas")
@RestController
@RequestMapping("/api/v1/partidas")
public class PartidaController {
	
	@Autowired
	private PartidaService partidaService;

	
	@ApiOperation(value = "Buscar partida por id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = Partida.class),
			@ApiResponse(code = 400, message = "Bad Request", response = StandardError.class),
			@ApiResponse(code = 401, message = "Unathorized", response = StandardError.class),
			@ApiResponse(code = 403, message = "Forbidden", response = StandardError.class),
			@ApiResponse(code = 404, message = "Not Found", response = StandardError.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = StandardError.class),
	})
	@GetMapping("{id}")
	public ResponseEntity<Partida> buscarPartidaPorId(@PathVariable("id") Long id){
		return ResponseEntity.ok().body(partidaService.buscarPartidaPorId(id));
	}
	
	@ApiOperation(value = "Listar partidas")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = PartidaResponseDTO.class),
			@ApiResponse(code = 400, message = "Bad Request", response = StandardError.class),
			@ApiResponse(code = 401, message = "Unathorized", response = StandardError.class),
			@ApiResponse(code = 403, message = "Forbidden", response = StandardError.class),
			@ApiResponse(code = 404, message = "Not Found", response = StandardError.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = StandardError.class),
	})
	@GetMapping
	public ResponseEntity<PartidaResponseDTO> listarPartidas(){
		return ResponseEntity.ok().body(partidaService.listarPartidas());
	}
	
	@ApiOperation(value = "Inserir partida")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Created", response = PartidaResponseDTO.class),
			@ApiResponse(code = 400, message = "Bad Request", response = StandardError.class),
			@ApiResponse(code = 401, message = "Unathorized", response = StandardError.class),
			@ApiResponse(code = 403, message = "Forbidden", response = StandardError.class),
			@ApiResponse(code = 404, message = "Not Found", response = StandardError.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = StandardError.class),
	})
	@PostMapping
	public ResponseEntity<Partida> inserirPartida(@Valid @RequestBody PartidaDTO partidaDTO){
		Partida partida = partidaService.inserirPartida(partidaDTO);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("{id}")
				.buildAndExpand(partida.getId()).toUri();
		
		return ResponseEntity.created(location).body(partida);
	}
	
	@ApiOperation(value = "Alterar partida")
	@ApiResponses(value = {
			@ApiResponse(code = 204, message = "no content", response = PartidaResponseDTO.class),
			@ApiResponse(code = 400, message = "Bad Request", response = StandardError.class),
			@ApiResponse(code = 401, message = "Unathorized", response = StandardError.class),
			@ApiResponse(code = 403, message = "Forbidden", response = StandardError.class),
			@ApiResponse(code = 404, message = "Not Found", response = StandardError.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = StandardError.class),
	})
	@PutMapping("/{id}")
	public ResponseEntity<Void> alterarPartida(@PathVariable("id") Long id, @Valid @RequestBody PartidaDTO partidaDTO){
		partidaService.alterarPartida(id, partidaDTO);
		return ResponseEntity.noContent().build();
	}
}
