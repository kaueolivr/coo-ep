package aplicacao;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Iterator;
import java.awt.Color;

import entidades.enums.Estado;

import entidades.Fundo;
import entidades.InimigoSimples;
import entidades.InimigoComposto;
import entidades.ProjetilJogador;
import entidades.ProjetilInimigo;

import lib.GameLib;

public class Jogo {
	Fundo fundoDistante; // Objeto do fundo distante
	Fundo fundoProximo; // Objeto do fundo próximo
	
	private LinkedList<InimigoSimples> inimigosTipo1; // Coleção de inimigos de tipo 1
	private LinkedList<InimigoComposto> inimigosTipo2; // Coleção de inimigos de tipo 2
	
	private LinkedList<ProjetilJogador> projeteisJogador; // Coleção de projéteis do jogador
	private LinkedList<ProjetilInimigo> projeteisInimigos; // Coleção de projéteis dos inimigos
	
	// Inicializa as entidades do jogo
	public void inicializaEntidades(long tempoAtual) {
		// Criação dos objetos dos fundos próximo e distante
		this.fundoDistante = new Fundo(50, 0.045, Color.DARK_GRAY);
		this.fundoProximo = new Fundo(20, 0.070, Color.GRAY);
		
		this.inimigosTipo1 = new LinkedList<InimigoSimples>(); // Cria a coleção de inimigos de tipo 1
		InimigoSimples.proxInimigo = tempoAtual + 2000; // Salva o instante para ser criado o primeiro inimigo de tipo 1
		
		this.inimigosTipo2 = new LinkedList<InimigoComposto>(); // Cria a coleção de inimigos de tipo 2
		InimigoComposto.proxInimigo = tempoAtual + 7000; // Define o instante inicial em que um inimigo de tipo 2 deve ser criado
		
		this.projeteisInimigos = new LinkedList<ProjetilInimigo>(); // Cria a coleção de projéteis dos inimigos (de todos os tipos)
		this.projeteisJogador = new LinkedList<ProjetilJogador>(); // Cria a coleção de projéteis do jogador
	}
	
	public void verificaColisoes(long tempoAtual) {
		// Verifica se ocorreram colisões entre os projéteis do jogador e os inimigos
		for (ProjetilJogador pJ : projeteisJogador) {
			for (InimigoSimples i1 : this.inimigosTipo1) i1.colisaoComProjetil(tempoAtual, pJ.posicao);
			for (InimigoComposto i2 : this.inimigosTipo2) i2.colisaoComProjetil(tempoAtual, pJ.posicao);
		}
	}
	
	// Verifica o estado das entidades
	public void verificaEstados(long tempoAtual, long delta) {
		// Cria um iterador para percorrer a coleção de inimigos de tipo 1
		Iterator<InimigoSimples> i1 = this.inimigosTipo1.iterator();
		
		while (i1.hasNext()) {
			InimigoSimples inimigo1 = i1.next();
			Estado estadoInimigo = inimigo1.verificaEstado(tempoAtual, delta); 
			
			// Caso um inimigo tenha ficado inativo (saído da tela), remove-o da coleção
			if (estadoInimigo == Estado.INATIVO) i1.remove();
			// Caso o inimigo esteja ativo, tenta atirar e armazena os projéteis na coleção, caso tenha ocorrido o disparo
			else if (estadoInimigo == Estado.ATIVO) {
				ProjetilInimigo projetil = (ProjetilInimigo) inimigo1.atira(tempoAtual);
				if (projetil != null) this.projeteisInimigos.addLast(projetil);
			}
		}
		
		// Cria um iterador para percorrer a coleção de inimigos de tipo 2
		Iterator<InimigoComposto> i2 = this.inimigosTipo2.iterator();
		
		while (i2.hasNext()) {
			InimigoComposto inimigo2 = i2.next();
			Estado estadoInimigo = inimigo2.verificaEstado(tempoAtual, delta); 
			
			// Caso um inimigo tenha ficado inativo (saído da tela), remove-o da coleção
			if (estadoInimigo == Estado.INATIVO) i2.remove();
			// Caso o inimigo esteja ativo, tenta atirar e armazena os projéteis na coleção, caso tenha ocorrido o disparo
			else if (estadoInimigo == Estado.ATIVO) {
				ArrayList<ProjetilInimigo> projeteis = inimigo2.atira();
				if (projeteis != null) {
					for (ProjetilInimigo j : projeteis) this.projeteisInimigos.addLast(j);
				}
			}
		}
		
		// Cria um iterador para percorrer a coleção de projéteis dos inimigos
		Iterator<ProjetilInimigo> pI = this.projeteisInimigos.iterator();
		
		while (pI.hasNext()) {
			Estado estadoProjetil = pI.next().verificaEstado(tempoAtual, delta); 
			
			// Caso um projétil tenha ficado inativo (saído da tela), remove-o da coleção
			if (estadoProjetil == Estado.INATIVO) pI.remove();
		}
		
		// Cria um iterador para percorrer a coleção de projéteis do jogador
		Iterator<ProjetilJogador> pJ = this.projeteisJogador.iterator();
		
		while (pJ.hasNext()) {
			Estado estadoProjetil = pJ.next().verificaEstado(tempoAtual, delta); 
			
			// Caso um projétil tenha ficado inativo (saído da tela), remove-o da coleção
			if (estadoProjetil == Estado.INATIVO) pJ.remove();
		}
	}
	
	private long t = 100;
	public void teste(long tempoAtual) {
		if (tempoAtual >= t) {
			this.projeteisJogador.addLast(new ProjetilJogador(Math.random() * (GameLib.WIDTH - 20.0) + 10.0, GameLib.HEIGHT - 10, 0.0, -1.0));
			t = tempoAtual + 100;
		}
	}
	
	// Verifica se é necessário criar novos inimigos e projéteis
	public void verificaCriacao(long tempoAtual) {
		// Verifica se é possível criar um inimigo de tipo 1 e, se for o caso, cria o inimigo e o adiciona à coleção
		if (tempoAtual > InimigoSimples.proxInimigo) this.inimigosTipo1.addLast(new InimigoSimples(tempoAtual));
		
		// Verifica se é possível criar um inimigo de tipo 2 e, se for o caso, cria o inimigo e o adiciona à coleção
		if (tempoAtual > InimigoComposto.proxInimigo) this.inimigosTipo2.addLast(new InimigoComposto(tempoAtual));
	}
	
	// Desenha as entidades na tela
	public void desenhaEntidades(long tempoAtual, long delta) {
		// Desenha os fundos
		this.fundoDistante.desenha(delta);
		this.fundoProximo.desenha(delta);
		
		// Desenha os inimigos de tipo 1
		for (InimigoSimples i1 : this.inimigosTipo1) i1.desenha(tempoAtual);
		
		// Desenha os inimigos de tipo 2
		for (InimigoComposto i1 : this.inimigosTipo2) i1.desenha(tempoAtual);
		
		// Desenha os projéteis dos inimigos
		for (ProjetilInimigo pI : this.projeteisInimigos) pI.desenha(tempoAtual);
		
		// Desenha os projéteis do jogador
		for (ProjetilJogador pJ : this.projeteisJogador) pJ.desenha(tempoAtual);
	}
}