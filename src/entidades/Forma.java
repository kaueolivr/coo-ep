package entidades;

import java.awt.Color;

import lib.GameLib;
import entidades.enums.Estado;
import entidades.enums.Formato;

public class Forma {
	protected double raio;
	protected Color cor;
	protected Formato formato;
	protected Estado estado;
	
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
		
		if (this.formato == Formato.CIRCLE) { 
			GameLib.drawCircle(x, y, this.raio);
		}
		if(this.formato == Formato.PLAYER) {
			GameLib.drawPlayer(x,y,this.raio);
		}
		if(this.formato == Formato.DIAMOND ) {
			GameLib.drawDiamond(x,y, this.raio);
		}
		
		}
	// TAVA TENTANDO DESENHAR O PROJETIL DO INIMIGO AQUI, MAS N SEI COMO ELE FUNCIONA POR SER EM ARRAYLIST
	
	//public void desenhaProjetil() {
		//while(this.estado == Estado.ATIVO) {
		// if(this.formato == Formato.DIAMOND || this.formato == Formato.CIRCLE) {
			
		//}
	//}
}