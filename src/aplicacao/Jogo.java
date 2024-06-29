package aplicacao;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Iterator;
import java.awt.Color;

import lib.GameLib;

import entidades.enums.Estado;
import entidades.enums.Formato;

import entidades.Fundo;
import entidades.InimigoBasico;
import entidades.InimigoComposto;
import entidades.ProjetilInimigo;

public class Jogo {
	Fundo fundoDistante;
	Fundo fundoProximo;
	
	private LinkedList<InimigoBasico> inimigosTipo1; // Coleção de inimigos de tipo 1
	private double proxInimigoTipo1; // Instante de criação de um novo inimigo de tipo 1
	
	private LinkedList<InimigoComposto> inimigosTipo2; // Coleção de inimigos de tipo 2
	
	private LinkedList<ProjetilInimigo> projeteisInimigos; // Coleção dos projéteis dos inimigos
	
	// Inicializa as entidades do jogo
	public void inicializaEntidades(long tempoAtual) {
		// Criação dos objetos dos fundos próximo e distante
		this.fundoDistante = new Fundo(50, 0.045, Color.DARK_GRAY);
		this.fundoProximo = new Fundo(20, 0.070, Color.GRAY);
		
		this.inimigosTipo1 = new LinkedList<InimigoBasico>(); // Cria a coleção de inimigos de tipo 1
		this.proxInimigoTipo1 = tempoAtual + 2000; // Salva o instante para ser criado o primeiro inimigo de tipo 1
		
		this.inimigosTipo2 = new LinkedList<InimigoComposto>(); // Cria a coleção de inimigos de tipo 2
		InimigoComposto.proxInimigo = tempoAtual + 7000; // Define o instante inicial em que um inimigo de tipo 2 deve ser criado
		
		this.projeteisInimigos = new LinkedList<ProjetilInimigo>(); // Cria a coleção de projéteis dos inimigos (de todos os tipos)
	}
	
	// Verifica se é necessário criar novos inimigos e projéteis
	public void verificaCriacao(long tempoAtual) {
		if (tempoAtual > this.proxInimigoTipo1) {
			// Cria um novo inimigo de tipo 1 e o adiciona ao final da coleção
			this.inimigosTipo1.addLast(new InimigoBasico(Math.random() * (GameLib.WIDTH - 20.0) + 10.0, -10.0, 0.20 + Math.random() * 0.15, 3 * Math.PI / 2, 0.0, tempoAtual + 500, 9.0, Color.CYAN, Formato.CIRCLE));
			this.proxInimigoTipo1 = tempoAtual + 500; // Atualiza o instante em que deve ser criado um novo inimigo de tipo 1
		}
		
		// Verifica se é possível criar um inimigo de tipo 2 e o faz, se for o caso
		if (tempoAtual > InimigoComposto.proxInimigo) this.inimigosTipo2.addLast(new InimigoComposto(tempoAtual));
	}
	
	// Verifica o estado das entidades
	public void verificaEstado(long tempoAtual, long delta) {
		// Cria um iterador para percorrer a LinkedList de inimigos de tipo 1
		Iterator<InimigoBasico> i1 = this.inimigosTipo1.iterator();
		
		while (i1.hasNext()) {
			Estado estadoInimigo = i1.next().verificaEstado(tempoAtual, delta); 
			
			// Caso um inimigo tenha ficado inativo (saído da tela), remove-o da LinkedList
			if (estadoInimigo == Estado.INATIVO) i1.remove();
		}
		
		// Cria um iterador para percorrer a LinkedList de inimigos de tipo 2
		Iterator<InimigoComposto> i2 = this.inimigosTipo2.iterator();
		
		while (i2.hasNext()) {
			Estado estadoInimigo = i2.next().verificaEstado(tempoAtual, delta); 
			
			// Caso um inimigo tenha ficado inativo (saído da tela), remove-o da LinkedList
			if (estadoInimigo == Estado.INATIVO) i2.remove();
		}
		
		// Cria um iterador para percorrer a LinkedList de projéteis dos inimigos
		Iterator<ProjetilInimigo> pI = this.projeteisInimigos.iterator();
		
		while (pI.hasNext()) {
			Estado estadoProjetil = pI.next().verificaEstado(tempoAtual, delta); 
			
			// Caso um projétil tenha ficado inativo (saído da tela), remove-o da LinkedList
			if (estadoProjetil == Estado.INATIVO) pI.remove();
		}
	}
	
	// Atira os projéteis dos personagens
	public void atiraProjeteis(long tempoAtual) {
		for (InimigoBasico i : this.inimigosTipo1) {
			ProjetilInimigo projetil = (ProjetilInimigo) i.atira(tempoAtual);
			if (projetil != null) this.projeteisInimigos.addLast(projetil);
		}
		
		for (InimigoComposto i : this.inimigosTipo2) {
			ArrayList<ProjetilInimigo> projeteis = i.atira();
			if (projeteis != null) {
				for (ProjetilInimigo j : projeteis) this.projeteisInimigos.addLast(j);
			}
		}
	}
	
	// Desenha as entidades na tela
	public void desenha(long tempoAtual, long delta) {
		// Desenha os fundos
		this.fundoDistante.desenha(delta);
		this.fundoProximo.desenha(delta);
		
		// Desenha os inimigos de tipo 1
		for (InimigoBasico i : this.inimigosTipo1) i.desenha(tempoAtual);
		
		// Desenha os inimigos de tipo 2
		for (InimigoComposto i : this.inimigosTipo2) i.desenha(tempoAtual);
		
		// Desenha os projéteis inimigos
		for (ProjetilInimigo i : this.projeteisInimigos) i.desenha(tempoAtual);
	}
}