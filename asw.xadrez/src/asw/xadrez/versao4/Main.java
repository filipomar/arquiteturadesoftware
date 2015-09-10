package asw.xadrez.versao4;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import static asw.xadrez.versao4.Cor.*;

public class Main{

	
	static final Color COR_CASA_SELECIONADA = Color.CYAN;
	static final Color COR_CASA_NAO_SELECIONADA = new Color(120, 120, 120);

	private static final int LARGURA_EM_PIXELS = 256;
	private static final int ALTURA_EM_PIXELS = 256;
	static final String CASA_VAZIA = "_";

	public static void main(final String[] args) {

		final Tabuleiro tabuleiro = new Tabuleiro();

		final JFrame janelaJogo = criarJanela();

		final MouseAdapter tratadorCliques = new TratadorCliques();

		preencherJanelaComCasas(tabuleiro, janelaJogo, tratadorCliques);
		
		criarPlacar(tabuleiro, tratadorCliques);
		
		exibirJanela(janelaJogo);

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
			}
		}
	}
	
	private static JFrame criarPlacar(Tabuleiro tabuleiro, MouseAdapter tratadorCliques) {
		
		Jogador jogadorBranco = new Jogador(JOptionPane.showInputDialog("Nome do Jogador - peças brancas.").toString());
		jogadorBranco.setCorJogador(BRANCO);
		Jogador jogadorPreto = new Jogador(JOptionPane.showInputDialog("Nome do Jogador - peças pretas.").toString());
		jogadorPreto.setCorJogador(PRETO);
		
		final JFrame janelaPlacar = new JFrame("Placar");
		janelaPlacar.setSize(300, 100);
		janelaPlacar.setLocation(0, 0);
		janelaPlacar.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel painel = new JPanel(new GridLayout(3, 2));
		JLabel playerNome = new JLabel ("COR/Jogador");
		JLabel playerPontuacao= new JLabel ("Pontos");
		
        JLabel labelBranco = new JLabel("Brancas: "+jogadorBranco.getNomeJogador());
        JLabel pontosBranco = new JLabel (""+jogadorBranco.getPontuacao());
        JLabel labelPreto = new JLabel("Pretas: "+jogadorPreto.getNomeJogador());
        JLabel pontosPreto = new JLabel (""+jogadorPreto.getPontuacao());
        
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