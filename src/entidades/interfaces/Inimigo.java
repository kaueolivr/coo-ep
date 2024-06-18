package entidades.interfaces;

public interface Inimigo {
	public void verificaNovoInimigo(long tempoAtual); // Verifica se um novo inimigo precisa ser criado - talvez esse método possa ser estático, precisa confirmar
	public void criaNovoInimigo(long tempoAtual); // Cria um novo inimigo
	// A interface também pode levar em conta o ângulo e a velocidade de rotação para definir métodos específicos de movimentação
}