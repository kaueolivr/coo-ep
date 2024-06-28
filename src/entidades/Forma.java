package entidades;

import java.awt.Color;

import lib.GameLib;

import entidades.enums.Formato;

public class Forma {
	protected double raio;
	protected Color cor;
	protected Formato formato;
	
	protected Forma(double raio, Color cor, Formato formato) {
		this.raio = raio;
		this.cor = cor;
		this.formato = formato;
	}
	
	// MANTER GETTERS E SETTERS?
	protected double getRaio() {
		return raio;
	}

	protected void setRaio(double raio) {
		this.raio = raio;
	}

	protected Color getCor() {
		return cor;
	}

	protected void setCor(Color cor) {
		this.cor = cor;
	}

	protected Formato getFormato() {
		return formato;
	}

	protected void setFormato(Formato formato) {
		this.formato = formato;
	}


	public void desenha(double x, double y) {
		GameLib.setColor(this.cor);
		
		if (this.formato == Formato.CIRCLE) GameLib.drawCircle(x, y, this.raio);
	}
}