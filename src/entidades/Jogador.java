package entidades;
import java.awt.Color;

import entidades.Ponto2D;
import entidades.enums.*;
import entidades.interfaces.Personagem;
import lib.GameLib;

public class Jogador implements Personagem{// estado
	private Ponto2D posicao;
	private double player_radius;							// raio (tamanho aproximado do player)
	private double player_explosion_start;						// instante do início da explosão
	private double player_explosion_end;						// instante do final da explosão
	private long proximoTiro;
	private long currentTime; 									// instante a partir do qual pode haver um próximo tiro
	private Estado estado = Estado.ACTIVE;
	private Formato formato = Formato.PLAYER;
	private long inicioExplosao;
	private long fimExplosao;
	
	
	public Jogador(double player_explosion_start, double player_explosion_end,
			long player_nextShot, long currentTime, Estado estado) {
		super();
		this.posicao = new Ponto2D(GameLib.WIDTH / 2,GameLib.HEIGHT * 0.90,0.25,0.25);
		this.player_radius = 12.0;
		this.player_explosion_start = player_explosion_start;
		this.player_explosion_end = player_explosion_end;
		this.proximoTiro = player_nextShot;
		this.currentTime = currentTime;
		this.estado = estado;
	}
	
	
	public Jogador() {
		super();
		this.posicao = new Ponto2D(GameLib.WIDTH / 2,GameLib.HEIGHT * 0.90,0.25,0.25);
		this.player_radius = 12.0;
	}


	@Override
	public int verificaEstado(long tempoAtual, long delta) {
		if (this.estado == Estado.EXPLODING) this.fimExplosao(tempoAtual);
		if(this.estado == Estado.ACTIVE) {
			if (this.posicao.getY() > GameLib.HEIGHT + 10) this.estado = Estado.INACTIVE; 
			else {
				movimenta(delta);
				if (tempoAtual > this.proximoTiro) atira();// Também precisa ter um this.posicao.getvY() < jogador.getY()
			}
		}
		return 0;
	}
	@Override
	public void fimExplosao(long tempo) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void verificaSaidaDaTela() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void movimenta(long delta) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void atira() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void desenha(long tempoAtual) {
		// Desenha a explosão, caso o estado seja "explodindo"
		if (this.estado == Estado.EXPLODING) {
			double alpha = (tempoAtual - this.inicioExplosao) / (this.fimExplosao - this.inicioExplosao);
			GameLib.drawExplosion(this.posicao.getX(), this.posicao.getY(), alpha);
		}
		// No estado ativo, desenha o inimigo em si
		if (this.estado == Estado.ACTIVE) {
			GameLib.setColor(Color.GREEN);
			GameLib.drawPlayer(this.posicao.getX(),  this.posicao.getY(), this.getPlayer_radius());
		}

	}


	public Ponto2D getPosicao() {
		return posicao;
	}


	public void setPosicao(Ponto2D posicao) {
		this.posicao = posicao;
	}


	public double getPlayer_radius() {
		return player_radius;
	}


	public void setPlayer_radius(double player_radius) {
		this.player_radius = player_radius;
	}


	public double getPlayer_explosion_start() {
		return player_explosion_start;
	}


	public void setPlayer_explosion_start(double player_explosion_start) {
		this.player_explosion_start = player_explosion_start;
	}


	public double getPlayer_explosion_end() {
		return player_explosion_end;
	}


	public void setPlayer_explosion_end(double player_explosion_end) {
		this.player_explosion_end = player_explosion_end;
	}


	public long getProximoTiro() {
		return proximoTiro;
	}


	public void setProximoTiro(long proximoTiro) {
		this.proximoTiro = proximoTiro;
	}


	public long getCurrentTime() {
		return currentTime;
	}


	public void setCurrentTime(long currentTime) {
		this.currentTime = currentTime;
	}


	public Estado getEstado() {
		return estado;
	}


	public void setEstado(Estado estado) {
		this.estado = estado;
	}


	public Formato getFormato() {
		return formato;
	}


	public void setFormato(Formato formato) {
		this.formato = formato;
	}
}