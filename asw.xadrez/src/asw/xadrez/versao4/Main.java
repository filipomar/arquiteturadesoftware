package asw.xadrez.versao4;

import static asw.xadrez.versao4.Cor.BRANCO;
import static asw.xadrez.versao4.Cor.PRETO;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.util.List;
import java.util.function.Function;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Main {

	static final Color COR_CASA_SELECIONADA = Color.CYAN;
	static final Color COR_CASA_NAO_SELECIONADA = new Color(120, 120, 120);

	private static final int LARGURA_EM_PIXELS = 256;
	private static final int ALTURA_EM_PIXELS = 256;
	static final String CASA_VAZIA = "_";

	public static void main(final String[] args) {
		final Tabuleiro tabuleiro = new Tabuleiro();

		final Jogador jogadorBranco = new Jogador(JOptionPane.showInputDialog("Nome do Jogador - peças brancas.").toString());
		jogadorBranco.setCorJogador(BRANCO);
		final Jogador jogadorPreto = new Jogador(JOptionPane.showInputDialog("Nome do Jogador - peças pretas.").toString());
		jogadorPreto.setCorJogador(PRETO);

		final JFrame janelaJogo = criarJanela();

		final TratadorCliques tratadorCliques = new TratadorCliques();
		tratadorCliques.pecaMovida(new Function<List<JLabel>, Object>() {

			@Override
			public Object apply(final List<JLabel> labels) {
				final JLabel origem = labels.get(0);
				final JLabel destino = labels.get(1);

				final Peca ativa = tabuleiro.getPeca(origem);
				final Peca passiva = tabuleiro.getPeca(destino);

				final Jogador movimentador = ativa.getCor().equals(jogadorPreto.getCorJogador()) ? jogadorPreto : jogadorBranco;
				movimentador.atualizaPontuacao(passiva.getTipoDePeca().getValor());
				movimentador.movimenta();

				final boolean isKing = passiva.getTipoDePeca().equals(TipoDePeca.REI);

				passiva.setTipoDePeca(TipoDePeca.NULO);

				if (isKing || tabuleiro.isOver()) {
					registrarGanhador(ativa.getCor(), tabuleiro, jogadorBranco, jogadorPreto);
				}

				return null;
			}
		});

		preencherJanelaComCasas(tabuleiro, janelaJogo, tratadorCliques);

		criarPlacar(tabuleiro, tratadorCliques, jogadorBranco, jogadorPreto);

		exibirJanela(janelaJogo);
	}

	private static void registrarGanhador(final Cor cor, final Tabuleiro tabuleiro, final Jogador jogadorBranco, final Jogador jogadorPreto) {
		final Jogador ganhador = cor.equals(jogadorBranco.getCorJogador()) ? jogadorBranco : jogadorPreto;
		final Jogador perdedor = ganhador == jogadorBranco ? jogadorPreto : jogadorBranco;

		final StringBuilder json = new StringBuilder();
		json.append('{');
		json.append("\"loser\":\"").append(perdedor.getNomeJogador()).append("\",");
		json.append("\"winner\":\"").append(ganhador.getNomeJogador()).append("\",");
		// json.append("\"points\":").append(ganhador.getPontuacao()).append(",");
		json.append("\"moves\":" + (ganhador.getMovimentos() + perdedor.getMovimentos()));
		json.append('}');

		try {
			System.out.println(json.toString());
			final boolean requestSent = RequestHelper.makeRequest("POST", "http://localhost:9000/game/save", json.toString());
			System.out.println("Request went ok: " + requestSent);
		} catch (final Exception e) {
			System.out.println("Exception " + e);
		}
	}

	private static JFrame criarJanela() {
		final JFrame janelaJogo = new JFrame("Xadrez");
		janelaJogo.setLayout(new GridLayout(Constantes.NUMERO_LINHAS_TABULEIRO, Constantes.NUMERO_COLUNAS_TABULEIRO));
		janelaJogo.setSize(LARGURA_EM_PIXELS, ALTURA_EM_PIXELS);
		janelaJogo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		return janelaJogo;
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

				peca.setLabel(label);
			}
		}
	}

	private static JFrame criarPlacar(final Tabuleiro tabuleiro, final MouseAdapter tratadorCliques, final Jogador jogadorBranco, final Jogador jogadorPreto) {
		final JFrame janelaPlacar = new JFrame("Placar");
		janelaPlacar.setSize(300, 100);
		janelaPlacar.setLocation(0, 0);
		janelaPlacar.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		final JPanel painel = new JPanel(new GridLayout(3, 2));
		final JLabel playerNome = new JLabel("COR/Jogador");
		final JLabel playerPontuacao = new JLabel("Pontos");

		final JLabel labelBranco = new JLabel("Brancas: " + jogadorBranco.getNomeJogador());
		final JLabel pontosBranco = new JLabel("" + jogadorBranco.getPontuacao());
		final JLabel labelPreto = new JLabel("Pretas: " + jogadorPreto.getNomeJogador());
		final JLabel pontosPreto = new JLabel("" + jogadorPreto.getPontuacao());

		painel.add(playerNome);
		painel.add(playerPontuacao);
		painel.add(labelBranco);
		painel.add(pontosBranco);
		painel.add(labelPreto);
		painel.add(pontosPreto);

		janelaPlacar.add(painel);
		janelaPlacar.setVisible(true);

		return janelaPlacar;
	}

	private static void exibirJanela(final JFrame janela) {
		janela.setLocationRelativeTo(null);
		janela.setVisible(true);
	}
}