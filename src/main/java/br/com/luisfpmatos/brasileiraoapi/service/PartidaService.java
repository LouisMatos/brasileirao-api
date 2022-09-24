package br.com.luisfpmatos.brasileiraoapi.service;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.luisfpmatos.brasileiraoapi.dto.PartidaDTO;
import br.com.luisfpmatos.brasileiraoapi.dto.PartidaGoogleDTO;
import br.com.luisfpmatos.brasileiraoapi.dto.PartidaResponseDTO;
import br.com.luisfpmatos.brasileiraoapi.entity.Partida;
import br.com.luisfpmatos.brasileiraoapi.exception.NotFoundException;
import br.com.luisfpmatos.brasileiraoapi.repository.PartidaRepository;

@Service
public class PartidaService {

	@Autowired
	private PartidaRepository partidaRepository;

	@Autowired
	private EquipeService equipeService;

	@Autowired
	private ModelMapper mapper;

	public Partida buscarPartidaPorId(Long id) {
		return partidaRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Nenhuma partida encontrada com o id informado: " + id));
	}

	public PartidaResponseDTO listarPartidas() {
		PartidaResponseDTO dto = new PartidaResponseDTO();
		dto.setPartidas(partidaRepository.findAll());
		return dto;
	}

	public Partida inserirPartida(@Valid PartidaDTO partidaDTO) {
		Partida partida = mapper.map(partidaDTO, Partida.class);
		partida.setEquipeCasa(equipeService.buscarEquipePorNome(partidaDTO.getNomeEquipeCasa()));
		partida.setEquipeVisitante(equipeService.buscarEquipePorNome(partidaDTO.getNomeEquipeVisitante()));
		return salvarPartida(partida);
	}

	public void alterarPartida(Long id, @Valid PartidaDTO partidaDTO) {
		boolean exists = partidaRepository.existsById(id);
		if (!exists) {
			throw new NotFoundException("Não foi possivel alterar a partida: ID inexistente;");
		}
		Partida partida = buscarPartidaPorId(id);
		partida.setEquipeCasa(equipeService.buscarEquipePorNome(partidaDTO.getNomeEquipeCasa()));
		partida.setEquipeVisitante(equipeService.buscarEquipePorNome(partidaDTO.getNomeEquipeVisitante()));
		partida.setDataHoraPartida(partidaDTO.getDataHoraPartida());
		partida.setLocalPartida(partidaDTO.getLocalPartida());
		salvarPartida(partida);
	}

	public void atualizaPartida(Partida partida, PartidaGoogleDTO partidaGoogleDTO) {
		partida.setPlacarEquipeCasa(partidaGoogleDTO.getPlacarEquipeCasa());
		partida.setPlacarEquipeVisitante(partidaGoogleDTO.getPlacarEquipeVisitante());
		partida.setGolsEquipeCasa(partidaGoogleDTO.getGolsEquipeCasa());
		partida.setGolsEquipeVisitante(partidaGoogleDTO.getGolsEquipeVisitante());
		partida.setPlacarEstendidoEquipeCasa(partidaGoogleDTO.getPlacarEstendidoEquipeCasa());
		partida.setPlacarEstendidoEquipeVisitante(partidaGoogleDTO.getPlacarEstendidoEquipeVisitante());
		partida.setTempoPartida(partidaGoogleDTO.getTempoPartida());
		salvarPartida(partida);
	}

	public List<Partida> listarPartidasPeriodo() {
		return partidaRepository.listarPartidasPeriodo();

	}

	public Integer buscarQuantidadePartidasPeriodo() {
		return partidaRepository.buscarQuantidadePartidasPeriodo();
	}

	private Partida salvarPartida(Partida partida) {
		return partidaRepository.save(partida);
	}

}
