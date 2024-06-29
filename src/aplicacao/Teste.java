package aplicacao;

import lib.GameLib;

public class Teste {
	// Espera, sem fazer nada, até que o instante de tempo atual seja maior ou igual ao instante especificado no parâmetro "time".
	public static void busyWait(long time) {
		while(System.currentTimeMillis() < time) Thread.yield();
	}
	
	// Método principal
	public static void main(String [] args) {
		// Indica que o jogo está em execução
		boolean running = true;
		

		// Variáveis usadas no controle de tempo efetuado no main loop
		long delta;
		long currentTime = System.currentTimeMillis();
		
		Jogo jogo = new Jogo();
		
		jogo.inicializaEntidades(currentTime);
		
		//ConjuntoProjeteisJogador projeteisjogador1 = new ConjuntoProjeteisJogador(Color.GREEN, currentTime); 
		
		//Jogador player = new Jogador();
		
		// Inicia interface gráfica
		GameLib.initGraphics();
		
		/*************************************************************************************************/
		/*                                                                                               */
		/* Main loop do jogo                                                                             */
		/*                                                                                               */
		/* O main loop do jogo possui executa as seguintes operações:                                    */
		/*                                                                                               */
		/* 1) Verifica se há colisões e atualiza estados dos elementos conforme a necessidade.           */
		/*                                                                                               */
		/* 2) Atualiza estados dos elementos baseados no tempo que correu desde a última atualização     */
		/*    e no timestamp atual: posição e orientação, execução de disparos de projéteis, etc.        */
		/*                                                                                               */
		/* 3) Processa entrada do usuário (teclado) e atualiza estados do player conforme a necessidade. */
		/*                                                                                               */
		/* 4) Desenha a cena, a partir dos estados dos elementos.                                        */
		/*                                                                                               */
		/* 5) Espera um período de tempo (de modo que delta seja aproximadamente sempre constante).      */
		/*                                                                                               */
		/*************************************************************************************************/
		
		while (running) {
		
			// Usada para atualizar o estado dos elementos do jogo player, projéteis e inimigos) "delta" indica quantos ms se passaram desde a última atualização.
			delta = System.currentTimeMillis() - currentTime;
			
			// Já a variável "currentTime" nos dá o timestamp atual.
			currentTime = System.currentTimeMillis();
			
			// Verifica as colisões entre personagens e projéteis
			jogo.verificaColisoes(currentTime);
			
			// Verifica o estado das entidades do jogo - realizando movimento e disparo de projéteis se possível
			jogo.verificaEstados(currentTime, delta);
			
			// Verifica se novos inimigos devem ser criados
			jogo.verificaCriacao(currentTime);
			
			// jogo.teste(currentTime);
			
			// Desenha as entidades do jogo
			jogo.desenhaEntidades(currentTime, delta);
			
			//projeteisjogador1.verificaNovoProjetil(currentTime);
			
			//projeteisjogador1.verificaEstado(currentTime, delta);
			
			//projeteisjogador1.desenha(currentTime);
			
			//player.desenha(currentTime);
						
			// Chamama a display() da classe GameLib atualiza o desenho exibido pela interface do jogo.
			GameLib.display();
			
			// Faz uma pausa de modo que cada execução do laço do main loop demore aproximadamente 5 ms.
			busyWait(currentTime + 5);
		}
		
		System.exit(0);
	}
}