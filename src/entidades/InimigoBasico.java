package entidades;

import java.awt.Color;

import lib.GameLib;

import entidades.enums.*;

import entidades.interfaces.Personagem;
import entidades.interfaces.Projetil;

public class InimigoBasico implements Personagem {		
	private Ponto2D posicao; // Utiliza-se composição para representar a posição do inimigo por meio da classe Ponto2D
	private Forma forma; // Utiliza-se composição para representar o desenho (com tamanho, cor e formato) por meio da classe Forma
	
	private Estado estado; // Estado (ativo, inativo ou explodindo)
	private double angulo; // Ângulo (indica direção do movimento)
	private double rv; // Velocidade de rotação
	private double inicioExplosao; // Instante de início da explosão
	private double fimExplosao; // Instante de fim da explosão
	private double proximoTiro; // Instante do próximo tiro
	
	public InimigoBasico(double posicaoX, double posicaoY, double velocidadeInimigo, double anguloInimigo, double velocidadeRotacaoInimigo, double proximoTiroInimigo, double tamanhoInimigo, Color corInimigo, Formato formatoInimigo) {
		this.posicao = new Ponto2D(posicaoX, posicaoY, 0, velocidadeInimigo); // O ideal é não ter o 0, pois há uma única velocidade, apesar de Ponto2D ter duas
		this.forma = new Forma(tamanhoInimigo, corInimigo, formatoInimigo);
		
		this.angulo = anguloInimigo;
		this.rv = velocidadeRotacaoInimigo;
		this.estado = Estado.ATIVO;
		this.proximoTiro = proximoTiroInimigo;
	}

	/*public void colisaoComProjetil(Projetil projetil) {
		// TODO Auto-generated method stub

	}*/

	public Estado verificaEstado(long tempoAtual, long delta) {
		if (this.estado == Estado.EXPLODINDO && tempoAtual > this.fimExplosao) this.estado = Estado.INATIVO; // fimExplosao
		if (this.estado == Estado.ATIVO) {
			if (this.posicao.getY() > GameLib.HEIGHT + 10) this.estado = Estado.INATIVO; // Define o estado como inativo caso o inimigo tenha saído da tela
			else movimenta(delta);
		}
		return this.estado;
	}

	// Realiza o deslocamento
	public void movimenta(long delta) {
		this.posicao.setX(this.posicao.getX() + (this.posicao.getvY() * Math.cos(this.angulo) * delta)); // No caso do inimigo de tipo 1, o ângulo é 3pi/2 e o cosseno resulta em zero, de forma que não há deslocamento horizontal
		this.posicao.setY(this.posicao.getY() + (this.posicao.getvY() * Math.sin(this.angulo) * delta * (-1.0)));
		this.angulo += this.rv * delta; 
	}
	
	// Verifica se é possível atirar e, em caso positivo, retorna um projétil novo
	public Projetil atira(long tempoAtual) {
		if (tempoAtual > this.proximoTiro) {
			this.proximoTiro = (long) (tempoAtual + 200 + Math.random() * 500); // Atualiza o instante do próximo tiro
			return new ProjetilInimigo(this.posicao.getX(), this.posicao.getY(), Math.cos(this.angulo) * 0.45, Math.sin(this.angulo) * 0.45 * (-1.0)); // Cria um projétil novo
		}
		return null;
	}

	// Desenha o inimigo na tela
	public void desenha(long tempoAtual) {
		// Desenha a explosão, caso o estado seja "explodindo"
		if (this.estado == Estado.EXPLODINDO) {
			double alpha = (tempoAtual - this.inicioExplosao) / (this.fimExplosao - this.inicioExplosao);
			GameLib.drawExplosion(this.posicao.getX(), this.posicao.getY(), alpha);
		}
		// No estado ativo, desenha o inimigo em si
		if (this.estado == Estado.ATIVO) this.forma.desenha(this.posicao.getX(), this.posicao.getY());
	}
}