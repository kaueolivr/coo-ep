package entidades;

import java.awt.Color;

import entidades.enums.Formato;
import lib.GameLib;

public class InimigoNovo extends InimigoSimples {
	private static double proxInimigoPosX = GameLib.WIDTH * 0.05; // Coordenada x do próximo inimigo de tipo 3 a aparecer 
	public static long proxInimigo;

	public InimigoNovo(long tempoAtual) {
		super(InimigoNovo.proxInimigoPosX, -10.0, 0.42, (3 * Math.PI) / 2, 0.0, (long) 0.0, 12.0, Color.YELLOW, Formato.LINE);;
		
	}
	public void movimenta(long delta) {
		this.posicao.setX(this.posicao.getX() + (this.posicao.getvY() * Math.sin(this.angulo) * delta)); // No caso do inimigo de tipo 1, o ângulo é 3pi/2 e o cosseno resulta em zero, de forma que não há deslocamento horizontal
		this.posicao.setY(this.posicao.getY() + (this.posicao.getvY() * Math.cos(this.angulo) * delta * (-1.0)));
		this.angulo += this.rv * delta; 
	}
	
}
