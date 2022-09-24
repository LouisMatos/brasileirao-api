package br.com.luisfpmatos.brasileiraoapi.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EquipeDTO implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 6967463287593938603L;

	@NotBlank
	private String nomeEquipe;

	@NotBlank
	private String urlLogoEquipe;

}
