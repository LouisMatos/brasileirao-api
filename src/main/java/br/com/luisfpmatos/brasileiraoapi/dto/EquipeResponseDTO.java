package br.com.luisfpmatos.brasileiraoapi.dto;

import java.io.Serializable;

import antlr.collections.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EquipeResponseDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2899877737910550611L;
	private List equipes;

}
