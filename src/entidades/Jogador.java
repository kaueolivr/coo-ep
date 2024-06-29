package entidades;

import java.awt.Color;

import lib.GameLib;

import entidades.enums.*;

import entidades.interfaces.*;

// Classe do jogador
public class Jogador implements Personagem {
	private Ponto2D posicao; // Utiliza-se composição para representar a posição do inimigo por meio da classe Ponto2D
	private Forma forma; // Utiliza-se composição para representar o desenho (com tamanho, cor e formato) por meio da classe Forma
	
	private double inicioExplosao; // Instante de início da explosão
	private double fimExplosao; // Instante de fim da explosão
	
	// private ProjetilJogador projetil;
	
	private long proximoTiro; // Instante do próximo tiro
	
	protected Estado estado; // Estado (ativo, inativo ou explodindo)
	
	public Jogador(long tempoAtual) {
		this.posicao = new Ponto2D(GameLib.WIDTH / 2, GameLib.HEIGHT * 0.90, 0.25, 0.25);
		this.forma = new Forma(12.0, Color.BLUE, Formato.PLAYER);
		
		this.proximoTiro = tempoAtual;
		
		this.estado = Estado.ATIVO;
	}
	
	// Método get da coordenada X do jogador
	public double getX() {
		return this.posicao.getX();
	}
	
	// Método get da coordenada Y do jogador
	public double getY() {
		return this.posicao.getY();
	}
	
	// Verifica a colisão do jogador com um projétil dos inimigos
	public void colisaoComProjetil(long tempoAtual, double projetilX, double projetilY, double projetilRaio) {
		if (this.estado == Estado.ATIVO && this.posicao.distancia(projetilX, projetilY) < (this.forma.getRaio() + projetilRaio) * 0.8) {
			// Caso tenha ocorrido a colisão, atualiza o estado e os instantes da explosão
			this.estado = Estado.EXPLODINDO;
			this.inicioExplosao = tempoAtual;
			this.fimExplosao = tempoAtual + 2000;
		}
	}
	
	// Verifica a colisão do jogador com os inimigos
	public void colisaoComInimigo(long tempoAtual, double inimigoX, double inimigoY, double inimigoRaio) {
		if (this.estado == Estado.ATIVO && this.posicao.distancia(inimigoX, inimigoY) < (this.forma.getRaio() + inimigoRaio) * 0.8) {
			// Caso tenha ocorrido a colisão, atualiza o estado e os instantes da explosão
			this.estado = Estado.EXPLODINDO;
			this.inicioExplosao = tempoAtual;
			this.fimExplosao = tempoAtual + 2000;
		}
	}
	
	// Verifica se o jogador explodiu e, em caso positivo, se a explosão terminou
	public boolean verificaExplosao(long tempoAtual) {
		if (this.estado == Estado.EXPLODINDO && tempoAtual > this.fimExplosao) return true;
		return false;
	}
	
	// Verificação e atualização de estado do jogador
	public Estado verificaEstado(long tempoAtual, long delta) {
		if (this.verificaExplosao(tempoAtual)) this.estado = Estado.ATIVO; 
		if (this.estado == Estado.ATIVO) this.movimenta(delta);
		return this.estado;
	}
	
	// Movimentação do jogador
	public void movimenta(long delta) {
		if (GameLib.iskeyPressed(GameLib.KEY_UP)) this.posicao.setY(this.posicao.getY() - delta * this.posicao.getvY());
		if (GameLib.iskeyPressed(GameLib.KEY_DOWN)) this.posicao.setY(this.posicao.getY() + delta * this.posicao.getvY());
		if (GameLib.iskeyPressed(GameLib.KEY_LEFT)) this.posicao.setX(this.posicao.getX() - delta * this.posicao.getvX());
		if (GameLib.iskeyPressed(GameLib.KEY_RIGHT)) this.posicao.setX(this.posicao.getX() + delta * this.posicao.getvX());
		
		// Mantém o jogador dentro da tela após processar a entrada do usuário
		if (this.posicao.getX() < 0.0) this.posicao.setX(0.0);
		if (this.posicao.getX() >= GameLib.WIDTH) this.posicao.setX(GameLib.WIDTH - 1);
		if (this.posicao.getY() < 25.0) this.posicao.setY(25.0);
		if (this.posicao.getY() >= GameLib.HEIGHT) this.posicao.setY(GameLib.HEIGHT - 1);
	}
	
	// Verifica se é possível atirar e, em caso positivo, retorna um projétil novo
	public ProjetilJogador atira(long tempoAtual) {
		if (GameLib.iskeyPressed(GameLib.KEY_CONTROL) && tempoAtual > this.proximoTiro) {
			this.proximoTiro = tempoAtual + 100;
			return new ProjetilJogador(this.posicao.getX(), this.posicao.getY() - 2 * this.forma.getRaio(), 0.0, -1.0);
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
		// No estado ativo, desenha o jogador em si
		if (this.estado == Estado.ATIVO) this.forma.desenha(this.posicao.getX(), this.posicao.getY());
	}


	/*@Override
	public Estado verificaEstado(long tempoAtual, long delta) {
		if (this.estado == Estado.EXPLODINDO) this.fimExplosao(tempoAtual);
		if(this.estado == Estado.ATIVO) {
			if (this.posicao.getY() > GameLib.HEIGHT + 10) this.estado = Estado.INATIVO; 
			else {
				movimenta(delta);
				if (tempoAtual > this.proximoTiro) atira();// Também precisa ter um this.posicao.getvY() < jogador.getY()
			}
		}
		return estado;
	}

	 public void fimExplosao(long tempo) {
		 // TODO Auto-generated method stub
		 
	 }
	 
	 public void verificaSaidaDaTela() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void movimenta(long delta) {
		if(this.estado == Estado.ATIVO){
			
			if(GameLib.iskeyPressed(GameLib.KEY_UP)) posicao.setY(posicao.getY() - delta*posicao.getvY()); //setY(getY() - delta*posicao.getVY();
			if(GameLib.iskeyPressed(GameLib.KEY_DOWN)) posicao.setY(posicao.getY() + delta*posicao.getvY());
			if(GameLib.iskeyPressed(GameLib.KEY_LEFT)) posicao.setX(posicao.getX() - delta*posicao.getvX());
			if(GameLib.iskeyPressed(GameLib.KEY_RIGHT)) posicao.setX(posicao.getX() + delta*posicao.getvX());
			
					}
				}	
	@Override
	public void atira() {
		// TODO Auto-generated method stub]
		if(GameLib.iskeyPressed(GameLib.KEY_CONTROL)) {
			
			if(currentTime > proximoTiro){
				
				int free = findFreeIndex(projetil.verificaEstado(fimExplosao, currentTime));
										
				if(free < projectile_states.length){
					
					projectile_X[free] = player_X;
					projectile_Y[free] = player_Y - 2 * player_radius;
					projectile_VX[free] = 0.0;
					projectile_VY[free] = -1.0;
					projectile_states[free] = 1;
					player_nextShot = currentTime + 100;
		
	}
	@Override
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


	public Ponto2D getPosicao() {
		return posicao;
	}


	public void setPosicao(Ponto2D posicao) {
		this.posicao = posicao;
	}


	public double getPlayer_radius() {
		return tamanhoPlayer;
	}


	public void setPlayer_radius(double player_radius) {
		this.tamanhoPlayer = player_radius;
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




	public long getInicioExplosao() {
		return inicioExplosao;
	}




	public void setInicioExplosao(long inicioExplosao) {
		this.inicioExplosao = inicioExplosao;
	}




	public long getFimExplosao() {
		return fimExplosao;
	}




	public void setFimExplosao(long fimExplosao) {
		this.fimExplosao = fimExplosao;
	}




	public Forma getForma() {
		return forma;
	}




	public void setForma(Forma forma) {
		this.forma = forma;
	}




	public double getTamanhoPlayer() {
		return tamanhoPlayer;
	}




	public void setTamanhoPlayer(double tamanhoPlayer) {
		this.tamanhoPlayer = tamanhoPlayer;
	}*/
	

	
	
}