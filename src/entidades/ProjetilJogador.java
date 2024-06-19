package entidades;

import java.awt.Color;
import java.util.ArrayList;

import entidades.interfaces.Projetil;
import lib.GameLib;

public class ProjetilJogador implements Projetil {
	
	public static final int INACTIVE = 0;
	public static final int ACTIVE = 1;
	public static final int EXPLODING = 2;
	
	private Ponto2D posicao; //posição através do ponto 2d (X, y, vX, Vy)
	private int estado; //estado do projetil
	private Color cor; //cor do tiro do jogador
	private double proximoProjetil;
	
	public ProjetilJogador (double posicaoX, double posicaoY, double velocidadeProjetil, double proximoTiro,Color corTiroInimigo) {
		this.posicao = new Ponto2D(posicaoX, posicaoY, 0, velocidadeProjetil); // O ideal Ã© nÃ£o ter o 0, pois hÃ¡ uma Ãºnica velocidade, apesar de Ponto2D ter duas
		this.estado = ACTIVE;
		this.proximoProjetil = proximoTiro;
		this.cor = corTiroInimigo;
	}
	
	public int verificaEstado(long tempoAtual, long delta) {
		if(this.estado== ACTIVE) {
			if(this.posicao.getY() < 0) this.estado = INACTIVE;
			else {
				movimenta(delta);
			}
		}
		return this.estado;
	}
	
	public void verificaSaidaDaTela() {
		if(this.posicao.getvY() < 0) this.estado = INACTIVE;
	}

	
	public void movimenta(long delta) {
		this.posicao.setX(this.posicao.getX() + (this.posicao.getvX() * delta));
		this.posicao.setY(this.posicao.getY() + (this.posicao.getvY() * delta));
	}

	public void desenha(long tempoAtual) {
			if (this.estado == ACTIVE) {
		}
			GameLib.setColor(this.cor);
			GameLib.drawLine(this.posicao.getX(), this.posicao.getY() - 5, this.posicao.getX(), this.posicao.getY() + 5);
			GameLib.drawLine(this.posicao.getX() - 1, this.posicao.getY() - 3, this.posicao.getX() - 1, this.posicao.getY() + 3);
			GameLib.drawLine(this.posicao.getX() + 1, this.posicao.getY() - 3, this.posicao.getX() + 1, this.posicao.getY() + 3);
	}
	public void teste () {
		
	}
}
