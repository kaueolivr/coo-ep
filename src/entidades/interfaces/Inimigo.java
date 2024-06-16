package entidades.interfaces;

public interface Inimigo {
	public void criaNovoInimigo(long tempo); // Cria novos inimigos, de acordo com o tempo e a disponibilidade no array - talvez esse método possa ser estático, precisa confirmar
	// A interface também pode levar em conta o ângulo e a velocidade de rotação para definir métodos específicos de movimentação
}