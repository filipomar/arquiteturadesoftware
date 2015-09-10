package asw.xadrez.versao4;



public class Jogador {
	private String nomeJogador;
	private int pontuacaoJogador;
	private int numMovimentosJogador;
	private int turno;
	private Cor corJogador;
	
	
	public Jogador (String _nomeJogador)
	{
		this.nomeJogador = _nomeJogador;
	}
	
	public int getTurno() {
		return turno;
	}

	public void setTurno(int turno) {
		this.turno = turno;
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

	public void setMovimentos(int movimentos) {
		this.numMovimentosJogador = movimentos;
	}

	public void setPontuacao(int pontuacao) {
		this.pontuacaoJogador = pontuacao;
	}

	public Cor getCorJogador() {
		return corJogador;
	}	
	public void setCorJogador(Cor cor) {
		this.corJogador= cor;
	}
}

