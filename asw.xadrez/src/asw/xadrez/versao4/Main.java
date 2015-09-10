package asw.xadrez.versao4;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main {

	static final Color COR_CASA_SELECIONADA = Color.CYAN;
	static final Color COR_CASA_NAO_SELECIONADA = new Color(120, 120, 120);

	private static final int LARGURA_EM_PIXELS = 256;
	private static final int ALTURA_EM_PIXELS = 256;

	static final String CASA_VAZIA = "_";

	public static void main(final String[] args) {
		final Tabuleiro tabuleiro = new Tabuleiro();

		final JFrame janela = criarJanela();

		final MouseAdapter tratadorCliques = new TratadorCliques();

		preencherJanelaComCasas(tabuleiro, janela, tratadorCliques);

		exibirJanela(janela);

		// try {
		// final boolean requestSent = RequestHelper.makeRequest("POST",
		// "http://localhost:9000/game/save",
		// "{\"loser\":\"jose\",\"winner\":\"joao\",\"moves\":5}");
		// System.out.println("Request went ok: " + requestSent);
		// } catch (final Exception e) {
		// System.out.println("Exception " + e);
		// }
	}

	private static JFrame criarJanela() {
		final JFrame janela = new JFrame("Xadrez");
		janela.setLayout(new GridLayout(Constantes.NUMERO_LINHAS_TABULEIRO, Constantes.NUMERO_COLUNAS_TABULEIRO));
		janela.setSize(LARGURA_EM_PIXELS, ALTURA_EM_PIXELS);
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		return janela;
	}

	private static void preencherJanelaComCasas(final Tabuleiro tabuleiro, final JFrame janela, final MouseAdapter tratadorCliques) {
		for (int i = 0; i < Constantes.NUMERO_LINHAS_TABULEIRO; ++i) {
			for (int j = 0; j < Constantes.NUMERO_COLUNAS_TABULEIRO; ++j) {
				final Peca peca = tabuleiro.getPeca(i, j);
				final JLabel label = new JLabel(peca.toString());
				label.setForeground(peca.getCor() == Cor.INDEFINIDO ? Color.CYAN : peca.getCor() == Cor.PRETO ? Color.BLACK : Color.WHITE);
				label.setBackground(COR_CASA_NAO_SELECIONADA);
				label.setHorizontalAlignment(JLabel.CENTER);
				label.setVerticalAlignment(JLabel.CENTER);
				label.setOpaque(true);
				label.addMouseListener(tratadorCliques);
				janela.add(label);
			}
		}
	}

	private static void exibirJanela(final JFrame janela) {
		janela.setLocationRelativeTo(null);
		janela.setVisible(true);
	}
}