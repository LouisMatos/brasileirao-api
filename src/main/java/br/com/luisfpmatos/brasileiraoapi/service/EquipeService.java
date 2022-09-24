package br.com.luisfpmatos.brasileiraoapi.service;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.luisfpmatos.brasileiraoapi.dto.EquipeDTO;
import br.com.luisfpmatos.brasileiraoapi.dto.EquipeResponseDTO;
import br.com.luisfpmatos.brasileiraoapi.entity.Equipe;
import br.com.luisfpmatos.brasileiraoapi.exception.BadRequestException;
import br.com.luisfpmatos.brasileiraoapi.exception.NotFoundException;
import br.com.luisfpmatos.brasileiraoapi.repository.EquipeRepository;

@Service
public class EquipeService {

	@Autowired
	private EquipeRepository equipeRepository;

	@Autowired
	private ModelMapper mapper;

	public Equipe buscarEquipeId(Long id) {
		return equipeRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Nenhuma equipe encontrada com o id informado: " + id));
	}

	public Equipe buscarEquipePorNome(String nomeEquipe) {
		return equipeRepository.findByNomeEquipe(nomeEquipe).orElseThrow(
				() -> new NotFoundException("Nenhuma equipe encontrada com o nome informado: " + nomeEquipe));
	}

	public EquipeResponseDTO listarEquipes() {
		EquipeResponseDTO dto = new EquipeResponseDTO();
		dto.setEquipes(equipeRepository.findAll());
		return dto;
	}

	public Equipe inserirEquipe(@Valid EquipeDTO equipeDTO) {
		boolean exists = equipeRepository.existsByNomeEquipe(equipeDTO.getNomeEquipe());
		if (exists) {
			throw new BadRequestException("Já existe uma equipe cadastrada com o nome informado.");
		}

		Equipe equipe = mapper.map(equipeDTO, Equipe.class);
		return equipeRepository.save(equipe);
	}

	public void alterarEquipe(Long id, @Valid EquipeDTO equipeDTO) {
		boolean exists = equipeRepository.existsById(id);
		if (!exists) {
			throw new NotFoundException("Não foi possivel alterar a equipe: ID inexistente;");
		}
		Equipe equipe = mapper.map(equipeDTO, Equipe.class);
		equipe.setId(id);
		equipeRepository.save(equipe);
	}

}
