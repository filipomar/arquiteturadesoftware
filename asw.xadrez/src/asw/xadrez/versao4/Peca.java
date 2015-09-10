package asw.xadrez.versao4;

public class Peca {
	private final Cor corDaPeca;
	private Object label;
	private TipoDePeca tipoDePeca;

	public Cor getCor() {
		return corDaPeca;
	}

	public TipoDePeca getTipoDePeca() {
		return tipoDePeca;
	}

	public boolean representsLabel(final Object label) {
		return this.label.equals(label);
	}

	public void setLabel(final Object label) {
		this.label = label;
	}

	public void setTipoDePeca(final TipoDePeca tipoDePeca) {
		this.tipoDePeca = tipoDePeca;
	}

	@Override
	public String toString() {
		return String.valueOf(tipoDePeca.getCaractere());

	}

	public Peca(final TipoDePeca tipoDePeca, final Cor corDaPeca) {
		super();
		this.tipoDePeca = tipoDePeca;
		this.corDaPeca = corDaPeca;
	}
}