package asw.xadrez.versao4;

import static asw.xadrez.versao4.Cor.BRANCO;
import static asw.xadrez.versao4.Cor.INDEFINIDO;
import static asw.xadrez.versao4.Cor.PRETO;
import static asw.xadrez.versao4.TipoDePeca.BISPO;
import static asw.xadrez.versao4.TipoDePeca.CAVALO;
import static asw.xadrez.versao4.TipoDePeca.NULO;
import static asw.xadrez.versao4.TipoDePeca.PEAO;
import static asw.xadrez.versao4.TipoDePeca.RAINHA;
import static asw.xadrez.versao4.TipoDePeca.REI;
import static asw.xadrez.versao4.TipoDePeca.TORRE;

import java.util.HashSet;
import java.util.Set;

public class Tabuleiro {
	Casa[][] casas;

	public Tabuleiro() {
		casas = new Casa[][] {
				{ new Casa(new Peca(TORRE, PRETO)), new Casa(new Peca(CAVALO, PRETO)), new Casa(new Peca(BISPO, PRETO)), new Casa(new Peca(RAINHA, PRETO)),
						new Casa(new Peca(REI, PRETO)), new Casa(new Peca(BISPO, PRETO)), new Casa(new Peca(CAVALO, PRETO)), new Casa(new Peca(TORRE, PRETO)) },
				{ new Casa(new Peca(PEAO, PRETO)), new Casa(new Peca(PEAO, PRETO)), new Casa(new Peca(PEAO, PRETO)), new Casa(new Peca(PEAO, PRETO)),
						new Casa(new Peca(PEAO, PRETO)), new Casa(new Peca(PEAO, PRETO)), new Casa(new Peca(PEAO, PRETO)), new Casa(new Peca(PEAO, PRETO)) },
				{ new Casa(new Peca(NULO, INDEFINIDO)), new Casa(new Peca(NULO, INDEFINIDO)), new Casa(new Peca(NULO, INDEFINIDO)),
						new Casa(new Peca(NULO, INDEFINIDO)), new Casa(new Peca(NULO, INDEFINIDO)), new Casa(new Peca(NULO, INDEFINIDO)),
						new Casa(new Peca(NULO, INDEFINIDO)), new Casa(new Peca(NULO, INDEFINIDO)) },
				{ new Casa(new Peca(NULO, INDEFINIDO)), new Casa(new Peca(NULO, INDEFINIDO)), new Casa(new Peca(NULO, INDEFINIDO)),
						new Casa(new Peca(NULO, INDEFINIDO)), new Casa(new Peca(NULO, INDEFINIDO)), new Casa(new Peca(NULO, INDEFINIDO)),
						new Casa(new Peca(NULO, INDEFINIDO)), new Casa(new Peca(NULO, INDEFINIDO)) },
				{ new Casa(new Peca(NULO, INDEFINIDO)), new Casa(new Peca(NULO, INDEFINIDO)), new Casa(new Peca(NULO, INDEFINIDO)),
						new Casa(new Peca(NULO, INDEFINIDO)), new Casa(new Peca(NULO, INDEFINIDO)), new Casa(new Peca(NULO, INDEFINIDO)),
						new Casa(new Peca(NULO, INDEFINIDO)), new Casa(new Peca(NULO, INDEFINIDO)) },
				{ new Casa(new Peca(NULO, INDEFINIDO)), new Casa(new Peca(NULO, INDEFINIDO)), new Casa(new Peca(NULO, INDEFINIDO)),
						new Casa(new Peca(NULO, INDEFINIDO)), new Casa(new Peca(NULO, INDEFINIDO)), new Casa(new Peca(NULO, INDEFINIDO)),
						new Casa(new Peca(NULO, INDEFINIDO)), new Casa(new Peca(NULO, INDEFINIDO)) },
				{ new Casa(new Peca(PEAO, BRANCO)), new Casa(new Peca(PEAO, BRANCO)), new Casa(new Peca(PEAO, BRANCO)), new Casa(new Peca(PEAO, BRANCO)),
						new Casa(new Peca(PEAO, BRANCO)), new Casa(new Peca(PEAO, BRANCO)), new Casa(new Peca(PEAO, BRANCO)), new Casa(new Peca(PEAO, BRANCO)) },
				{ new Casa(new Peca(TORRE, BRANCO)), new Casa(new Peca(CAVALO, BRANCO)), new Casa(new Peca(BISPO, BRANCO)), new Casa(new Peca(RAINHA, BRANCO)),
						new Casa(new Peca(REI, BRANCO)), new Casa(new Peca(BISPO, BRANCO)), new Casa(new Peca(CAVALO, BRANCO)),
						new Casa(new Peca(TORRE, BRANCO)) } };
	}

	public Peca getPeca(final int linha, final int coluna) {
		return casas[linha][coluna].getPeca();
	}

	public Peca getPeca(final Object origem) {
		for (final Casa[] c : casas) {
			for (final Casa casa : c) {
				if (casa.getPeca().representsLabel(origem)) {
					return casa.getPeca();
				}
			}
		}

		return null;
	}

	public boolean isOver() {
		final Set<Cor> remainingTeams = new HashSet<Cor>();

		for (final Casa[] c : casas) {
			for (final Casa casa : c) {
				final Peca peca = casa.getPeca();
				if (!peca.getTipoDePeca().equals(NULO)) {
					remainingTeams.add(peca.getCor());
					if (remainingTeams.size() > 1) {
						return false;
					}
				}
			}
		}

		return true;
	}

}