package br.com.luisfpmatos.brasileiraoapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.luisfpmatos.brasileiraoapi.dto.PartidaGoogleDTO;
import br.com.luisfpmatos.brasileiraoapi.entity.Partida;
import br.com.luisfpmatos.brasileiraoapi.util.ScrapingUtil;
import br.com.luisfpmatos.brasileiraoapi.util.StatusPartida;

@Service
public class ScrapingService {

	@Autowired
	private ScrapingUtil scrapingUtil;

	@Autowired
	private PartidaService partidaService;

	public void verificaPartidaPeriodo() {
		Integer quantidadePartida = partidaService.buscarQuantidadePartidasPeriodo();

		if (quantidadePartida > 0) {
			List<Partida> partidas = partidaService.listarPartidasPeriodo();

			partidas.forEach(partida -> {
				String urlPartida = scrapingUtil.montaUrlGoogle(partida.getEquipeCasa().getNomeEquipe(),
						partida.getEquipeVisitante().getNomeEquipe());

				PartidaGoogleDTO partidaGoogleDTO = scrapingUtil.obtemInformacoesPartida(urlPartida);

				if (partidaGoogleDTO.getStatusPartida() != StatusPartida.PARTIDA_NAO_INICIADA) {
					partidaService.atualizaPartida(partida, partidaGoogleDTO);
				}

			});
		}
	}

}
