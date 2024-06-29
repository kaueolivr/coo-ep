package entidades.interfaces;

import entidades.enums.Estado;

// Interface que deve ser implementada pelas classes do jogador e dos inimigos
public interface Personagem {
	
	public double getX(); // Método get da coordenada X do personagem
	public double getY(); // Método get da coordenada Y do personagem
	
	public boolean verificaExplosao(long tempoAtual); // Verifica se ocorreu uma explosão e define o estado como inativo ao fim dela
	public Estado verificaEstado(long tempoAtual, long delta); // Verifica o estado do personagem e invoca os métodos a seguir, dependendo do estado
	public void movimenta(long delta); // Realiza o movimento dos inimigos ou, no caso do jogador, verifica a entrada do usuário (o que pode ser outro método)
	public void desenha(long tempoAtual); // Desenha o personagem na tela
	
	// O jogador pode ter um método de verificar a entrada do usuário e invocar algum dos métodos acima, conforme a entrada
}