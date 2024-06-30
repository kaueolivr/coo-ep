package entidades;

import java.awt.Color;

import entidades.enums.Estado;
import entidades.enums.Formato;
import lib.GameLib;

public class InimigoNovo extends InimigoSimples {
	public InimigoNovo(long tempoAtual) {
		super(tempoAtual);
		// TODO Auto-generated constructor stub
	}

	public static long proxInimigo;
	public static int contagem = 0;
	private Forma forma;
	private Ponto2D posicao;
	private Estado estado;
	private Color cor;
	long inicioExplosao;
	long fimExplosao;


	public void movimenta(long delta) {
		this.posicao.setX(this.posicao.getX() + (this.posicao.getvY() * Math.sin(this.angulo) * delta)); // No caso do inimigo de tipo 1, o ângulo é 3pi/2 e o cosseno resulta em zero, de forma que não há deslocamento horizontal
		this.posicao.setY(this.posicao.getY() + (this.posicao.getvY() * Math.cos(this.angulo) * delta * (-1.0)));
		this.angulo += this.rv * delta; 
	}
}