package br.com.luisfpmatos.brasileiraoapi.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "equipe")
public class Equipe implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7515182017014027053L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "equipe_id")
	private Long id;
	
	@Column(name = "nome_equipe")
	private String nomeEquipe;
	
	@Column(name = "url_Logo_equipe")
	private String urlLogoEquipe;
}
