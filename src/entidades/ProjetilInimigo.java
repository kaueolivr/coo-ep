package entidades;

import java.awt.Color;

import entidades.enums.Estado;
import entidades.enums.Formato;

import entidades.interfaces.Projetil;

import lib.GameLib;

public class ProjetilInimigo implements Projetil {
	private Ponto2D posicao; // Utiliza-se composição para representar a posição do projétil inimigo por meio da classe Ponto2D
	private Forma forma = new Forma(2.0, Color.RED, Formato.CIRCLE); // Utiliza-se composição para representar o desenho (com tamanho, cor e formato) por meio da classe Forma
	
	private Estado estado; // Estado do projétil
	
	public ProjetilInimigo (double posicaoX, double posicaoY, double velocidadeX, double velocidadeY) {
		this.posicao = new Ponto2D(posicaoX, posicaoY, velocidadeX, velocidadeY);
		this.estado = Estado.ATIVO;
	}
	
	public Estado verificaEstado(long tempoAtual, long delta) {
		if (this.estado == Estado.ATIVO) {
			if (this.posicao.getY() > GameLib.HEIGHT) this.estado = Estado.INATIVO; // Define o estado como inativo caso o projétil tenha saído da tela
			else movimenta(delta);
		}
		return this.estado;
	}
	
	public void movimenta(long delta) {
		this.posicao.setX(this.posicao.getX() + (this.posicao.getvX() * delta));   
		this.posicao.setY(this.posicao.getY() + (this.posicao.getvY() * delta));   
	}
	
	public void desenha(long tempoAtual) {
		if (this.estado == Estado.ATIVO) this.forma.desenha(this.posicao.getX(), this.posicao.getY());
	}
}
