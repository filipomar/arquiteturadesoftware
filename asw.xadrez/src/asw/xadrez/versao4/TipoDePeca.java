package asw.xadrez.versao4;

enum TipoDePeca {
	TORRE('t', 4), CAVALO('c', 2), BISPO('b', 3), RAINHA('d', 5), REI('r', 6), PEAO('p', 1), NULO('_', 0);

	private final char caractere;
	private int valor;

	public char getCaractere() {
		return caractere;
	}

	private TipoDePeca(final char caractere, final int valor) {
		this.caractere = caractere;
		this.valor = valor;
	}

	public int getValor() {
		return valor;
	}

}