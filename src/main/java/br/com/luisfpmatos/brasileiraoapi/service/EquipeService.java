package br.com.luisfpmatos.brasileiraoapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import antlr.collections.List;
import br.com.luisfpmatos.brasileiraoapi.dto.EquipeResponseDTO;
import br.com.luisfpmatos.brasileiraoapi.entity.Equipe;
import br.com.luisfpmatos.brasileiraoapi.exception.NotFoundException;
import br.com.luisfpmatos.brasileiraoapi.repository.EquipeRepository;

@Service
public class EquipeService {

	@Autowired
	private EquipeRepository equipeRepository;

	public Equipe buscarEquipeId(Long id) {
		return equipeRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Nenhuma equipe encontrada com o id informado: " + id));
	}

	public EquipeResponseDTO listarEquipes() {
		EquipeResponseDTO dto = new EquipeResponseDTO();
		dto.setEquipes(equipeRepository.findAll());
		return dto;
	}

}
