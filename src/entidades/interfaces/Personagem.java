package entidades.interfaces;

import entidades.enums.Estado;

// Interface que deve ser implementada pelas classes do jogador e dos inimigos
public interface Personagem {
	// Todos os personagens contém x, y, vX e vY (então é possível compor com Ponto2D), além de um estado, raio/tamamho, instantes de início/fim da explosão e de próximo tiro
	// Os inimigos também possuem ângulos (direção de movimento), velocidade de rotação e instante em que um novo inimigo de seu tipo deve aparecer
	// O inimigo de tipo 2 também possui a coordenada x do próximo inimigo de tipo 2 a aparecer e uma contagem de inimigos de tipo 2 (usada na "formação de voo")
	
	// public void colisaoComProjetil(Projetil projetil); // Verifica se ocorreu uma colisão com projéteis (entre jogador e inimigo ou inimigo e jogador), atualizando o estado em caso positivo
	// O jogador também deve ter um método de colisão com inimigo
	
	public Estado verificaEstado(long tempoAtual, long delta); // Verifica o estado do personagem e invoca os métodos a seguir, dependendo do estado
	public void movimenta(long delta); // Realiza o movimento dos inimigos ou, no caso do jogador, verifica a entrada do usuário (o que pode ser outro método)
	public Projetil atira(long tempoAtual); // Atira projéteis caso possível (e de acordo com a entrada do usuário, no caso do jogador)
	public void desenha(long tempoAtual); // Desenha o personagem na tela
	
	// O jogador pode ter um método de verificar a entrada do usuário e invocar algum dos métodos acima, conforme a entrada
}