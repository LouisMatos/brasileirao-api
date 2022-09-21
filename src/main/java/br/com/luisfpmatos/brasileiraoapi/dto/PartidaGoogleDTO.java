package br.com.luisfpmatos.brasileiraoapi.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PartidaGoogleDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6068832162822538450L;

	private String statusPartida;
	private String tempoPartida;

	private String nomeEquipeCasa;
	private String urlEquipeCasa;
	private Integer placarEquipeCasa;
	private String golsEquipeCasa;
	private String placarEstendidoEquipeCasa;

	private String nomeEquipeVisitante;
	private String urlEquipeVisitante;
	private Integer placarEquipeVisitante;
	private String golsEquipeVisitante;
	private String placarEstendidoEquipeVisitante;
}
