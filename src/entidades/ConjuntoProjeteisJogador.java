package entidades;

import java.util.ArrayList;
import java.util.Iterator;

import java.awt.Color;
import lib.GameLib;


public class ConjuntoProjeteisJogador {
	
	// Constantes relacionadas aos estados que os elementos do jogo (player, projeteis ou inimigos) podem assumir.
	public static final int INACTIVE = 0;
	public static final int ACTIVE = 1;
	public static final int EXPLODING = 2;
	
	private ArrayList<ProjetilJogador> projeteisjogador;
	
	private Color cor; //cor do tiro do inimigo
	private long proxProjetil;
	
	public ConjuntoProjeteisJogador(Color corProjetilJogador, long tempoAtual){
		this.projeteisjogador = new ArrayList<ProjetilJogador>();
		
		this.cor = corProjetilJogador;
		
		this.proxProjetil = tempoAtual +  100;
	}

	public void verificaNovoProjetil(long tempoAtual) {
		if (tempoAtual > this.proxProjetil) criaNovoProjetil(tempoAtual);
	}
	//recebe atributos do inimigo?
	public void criaNovoProjetil(long tempoAtual) {
		ProjetilJogador novoProjetil = new ProjetilJogador(10,1500, -1, tempoAtual + 500, this.cor);
		projeteisjogador.add(novoProjetil);
		this.proxProjetil = tempoAtual + 100;
	}
	
	public void verificaEstado(long tempoAtual, long delta) {
		// Cria um iterador para percorrer a ArrayList de inimigos
		Iterator<ProjetilJogador> i = projeteisjogador.iterator();
		
		while (i.hasNext()) {
			// Caso um projetil tenha ficado inativo (saído da tela), remove-o da ArrayList
			if (i.next().verificaEstado(tempoAtual, delta) == INACTIVE) {
				i.remove();
			}
		}
	}

	public void desenha(long tempoAtual) {
		for (ProjetilJogador i : this.projeteisjogador) i.desenha(tempoAtual);
	}

}

