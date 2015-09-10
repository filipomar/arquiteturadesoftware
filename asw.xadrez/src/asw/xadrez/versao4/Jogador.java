package asw.xadrez.versao4;

public class Jogador {
	private final String nomeJogador;
	private int pontuacaoJogador;
	private int numMovimentosJogador;
	private Cor corJogador;

	public Jogador(final String nomeJogador) {
		this.nomeJogador = nomeJogador.replaceAll("[^\\w]+", "");
	}

	public String getNomeJogador() {
		return nomeJogador;
	}

	public int getPontuacao() {
		return pontuacaoJogador;
	}

	public int getMovimentos() {
		return numMovimentosJogador;
	}

	public void movimenta() {
		this.numMovimentosJogador++;
	}

	public void atualizaPontuacao(final int pontuacaoPeca) {
		this.pontuacaoJogador += pontuacaoPeca;
	}

	public Cor getCorJogador() {
		return corJogador;
	}

	public void setCorJogador(final Cor cor) {
		this.corJogador = cor;
	}
}
