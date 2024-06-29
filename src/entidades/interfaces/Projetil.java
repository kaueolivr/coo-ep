package entidades.interfaces;

import entidades.enums.Estado;

// A interface deve ser implementada pelas classes os projétis dos inimigos e do jogador
public interface Projetil {
	// Todos os projéteis contém x, y, vX e vY (então é possível compor com Ponto2D), além de um estado
	// No caso dos projéteis inimigos, há um raio/tamanho
	
	public Estado verificaEstado(long tempoAtual, long delta); // Verifica o estado do projétil e invoca os métodos a seguir, dependendo do estado
	public void movimenta(long delta); // Realiza o deslocamento dos projéteis
	public void desenha(long tempoAtual); // Desenha o projétil na tela
}