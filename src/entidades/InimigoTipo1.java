package entidades;

import java.awt.Color;

import lib.GameLib;

import entidades.interfaces.Personagem;
import entidades.Ponto2D;

public class InimigoTipo1 implements Personagem {
	// Constantes relacionadas aos estados que o inimigo pode assumir
	public static final int INACTIVE = 0;
	public static final int ACTIVE = 1;
	public static final int EXPLODING = 2;
		
	private Ponto2D posicao; // Utiliza-se composição para representar a posição do inimigo por meio da classe Ponto2D
	private int estado;
	private double angulo; // Ângulo (indica direção do movimento)
	private double rv; // Velocidade de rotação
	private double inicioExplosao; // Instante de início da explosão
	private double fimExplosao; // Instante de fim da explosão
	private double proximoTiro; // Instante do próximo tiro
	private double raio; // Tamanho/raio
	// private long proxInimigo; // Instante em que um novo inimigo de tipo 1 deve aparecer
	private Color cor;
	
	public InimigoTipo1(double posicaoX, double posicaoY, double velocidadeInimigo, double anguloInimigo, double velocidadeRotacaoInimigo, double proximoTiroInimigo, double tamanhoInimigo1, Color corInimigo) {
		this.posicao = new Ponto2D(posicaoX, posicaoY, 0, velocidadeInimigo); // O ideal é não ter o 0, pois há uma única velocidade, apesar de Ponto2D ter duas
		
		this.angulo = anguloInimigo;
		this.rv = velocidadeRotacaoInimigo;
		this.estado = ACTIVE;
		this.proximoTiro = proximoTiroInimigo;
		this.raio = tamanhoInimigo1;
		this.cor = corInimigo;
	}

	/*public void colisaoComProjetil(Projetil projetil) {
		// TODO Auto-generated method stub

	}*/

	public int verificaEstado(long tempoAtual, long delta) {
		if (this.estado == EXPLODING) this.fimExplosao(tempoAtual);
		if (this.estado == ACTIVE) {
			if (this.posicao.getY() > GameLib.HEIGHT + 10) this.estado = INACTIVE; // verificaSaidaDaTela
			else {
				movimenta(delta);
				if (tempoAtual > this.proximoTiro) atira();// Também precisa ter um this.posicao.getvY() < jogador.getY()
			}
		}
		return this.estado;
	}

	// Manter este método?
	public void fimExplosao(long tempoAtual) {
		if (tempoAtual > this.fimExplosao) this.estado = INACTIVE;
	}

	// Manter este método?
	public void verificaSaidaDaTela() {
		if (this.posicao.getY() > GameLib.HEIGHT + 10) this.estado = INACTIVE;
	}

	// Realiza o deslocamento
	public void movimenta(long delta) {
		this.posicao.setX(this.posicao.getX() + (this.posicao.getvY() * Math.cos(this.angulo) * delta)); // No caso do inimigo de tipo 1, o ângulo é 3pi/2 e o cosseno resulta em zero, de forma que não há deslocamento horizontal
		this.posicao.setY(this.posicao.getY() + (this.posicao.getvY() * Math.sin(this.angulo) * delta * (-1.0)));
		this.angulo += this.rv * delta; 
	}
	
	// Implementar
	public void atira() {
		// TODO Auto-generated method stub

	}

	// Desenha o inimigo na tela
	public void desenha(long tempoAtual) {
		// Desenha a explosão, caso o estado seja "explodindo"
		if (this.estado == EXPLODING) {
			double alpha = (tempoAtual - this.inicioExplosao) / (this.fimExplosao - this.inicioExplosao);
			GameLib.drawExplosion(this.posicao.getX(), this.posicao.getY(), alpha);
		}
		// No estado ativo, desenha o inimigo em si
		if (this.estado == ACTIVE) {
			GameLib.setColor(this.cor);
			GameLib.drawCircle(this.posicao.getX(),  this.posicao.getY(), this.raio);
		}

	}

}
