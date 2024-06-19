package entidades.interfaces;
import java.awt.Color;

import entidades.enums.*;
import lib.GameLib;

public class Jogador implements Personagem {
	public Projetil projetil_player;								// estado
	double player_X = GameLib.WIDTH / 2;					// coordenada x
	double player_Y = GameLib.HEIGHT * 0.90;				// coordenada y
	double player_VX = 0.25;								// velocidade no eixo x
	double player_VY = 0.25;								// velocidade no eixo y
	double player_radius = 12.0;							// raio (tamanho aproximado do player)
	double player_explosion_start = 0;						// instante do início da explosão
	double player_explosion_end = 0;						// instante do final da explosão
	long player_nextShot;
	long currentTime; 									// instante a partir do qual pode haver um próximo tiro
	private Estado jogador = Estado.ACTIVE;
	
	
	@Override
	public void colisaoComProjetil(Projetil projetil) {
		
		
	}

	public Jogador() {
		super();
	}

	@Override
	public void verificaEstado() {
		this.jogador = Estado.ACTIVE;
		System.out.print(" " + jogador);
	}

	@Override
	public void fimExplosao(long tempo) {
		
	}

	@Override
	public void verificaSaidaDaTela() {
		
		
	}

	@Override
	public void movimenta() {
		
		
	}

	@Override
	public void atira() {
		
		
	}

	@Override
	public void desenha() {
		GameLib.setColor(Color.BLUE);
		GameLib.drawPlayer(player_X, player_Y, player_radius);
		}
		
	}


