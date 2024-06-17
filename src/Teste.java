import java.awt.Color;

import lib.GameLib;

import entidades.Fundo;

public class Teste {
	
	// Constantes relacionadas aos estados que os elementos do jogo (player, projeteis ou inimigos) podem assumir.
	public static final int INACTIVE = 0;
	public static final int ACTIVE = 1;
	public static final int EXPLODING = 2;
	

	// Espera, sem fazer nada, até que o instante de tempo atual seja maior ou igual ao instante especificado no parâmetro "time".
	public static void busyWait(long time){
		
		while(System.currentTimeMillis() < time) Thread.yield();
	}
	
	// Método principal
	public static void main(String [] args){
		// Indica que o jogo está em execução
		boolean running = true;

		// Variáveis usadas no controle de tempo efetuado no main loop
		long delta;
		long currentTime = System.currentTimeMillis();
		
		// Criação dos objetos dos fundos próximo e distante
		Fundo fundoDistante = new Fundo(50, 0.045, Color.DARK_GRAY);
		Fundo fundoProximo = new Fundo(20, 0.070, Color.GRAY);
						
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
		
		while(running){
		
			// Usada para atualizar o estado dos elementos do jogo player, projéteis e inimigos) "delta" indica quantos ms se passaram desde a última atualização.
			delta = System.currentTimeMillis() - currentTime;
			
			// Já a variável "currentTime" nos dá o timestamp atual.
			currentTime = System.currentTimeMillis();

			/*******************/
			/* Desenho da cena */
			/*******************/
			
			// Desenha os fundos
			fundoDistante.desenha(delta);
			fundoProximo.desenha(delta);
						
			// Chamama a display() da classe GameLib atualiza o desenho exibido pela interface do jogo.
			GameLib.display();
			
			// Faz uma pausa de modo que cada execução do laço do main loop demore aproximadamente 5 ms.
			busyWait(currentTime + 5);
		}
		
		System.exit(0);
	}
}
