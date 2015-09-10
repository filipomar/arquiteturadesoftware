package asw.xadrez.versao4;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import javax.swing.JLabel;

final class TratadorCliques extends MouseAdapter {
	private JLabel casaOrigem;
	private final List<Function<List<JLabel>, Object>> callbacks = new ArrayList<Function<List<JLabel>, Object>>();

	public TratadorCliques() {
		super();
	}

	@Override
	public void mousePressed(final MouseEvent e) {
		final JLabel casaClicada = (JLabel) e.getSource();
		movimentoPeca(casaClicada);
	}

	private void movimentoPeca(final JLabel casaClicada) {
		if (nenhumaCasaSelecionada()) {
			if (casaClicada.getText().equals(Main.CASA_VAZIA))
				return;
			selecionarCasa(casaClicada);
		} else {
			if (casaClicada != casaOrigem)
				moverPeca(casaClicada);
			if (casaClicada == casaOrigem)
				deselecionarCasa(casaClicada);
		}
	}

	private void deselecionarCasa(final JLabel casaClicada) {
		casaClicada.setBackground(Main.COR_CASA_NAO_SELECIONADA);
		casaOrigem = null;
	}

	private boolean nenhumaCasaSelecionada() {
		return casaOrigem == null;
	}

	private void selecionarCasa(final JLabel casaClicada) {
		casaClicada.setBackground(Main.COR_CASA_SELECIONADA);
		casaOrigem = casaClicada;
	}

	private void moverPeca(final JLabel casaDestino) {
		casaDestino.setText(casaOrigem.getText());

		casaOrigem.setText(Main.CASA_VAZIA);
		casaOrigem.setBackground(Main.COR_CASA_NAO_SELECIONADA);

		casaDestino.setForeground(casaOrigem.getForeground());
		casaOrigem.setForeground(ConversorCores.deCorParaAwtColor(Cor.INDEFINIDO));

		final JLabel tempOrigem = casaOrigem;
		casaOrigem = null;

		for (final Function<List<JLabel>, Object> callback : callbacks) {
			final ArrayList<JLabel> labels = new ArrayList<JLabel>();
			labels.add(tempOrigem);
			labels.add(casaDestino);
			callback.apply(labels);
		}

	}

	public void pecaMovida(final Function<List<JLabel>, Object> callback) {
		this.callbacks.add(callback);
	}

}