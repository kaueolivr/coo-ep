package entidades;

import java.util.ArrayList;
import java.util.Iterator;

import java.awt.Color;
import lib.GameLib;

public class ConjuntoProjeteisInimigos{
	
	// Constantes relacionadas aos estados que os elementos do jogo (player, projeteis ou inimigos) podem assumir.
	public static final int INACTIVE = 0;
	public static final int ACTIVE = 1;
	public static final int EXPLODING = 2;
	
	private ArrayList<ProjetilInimigo> projeteisinimigo;
	
	private double raio; //raio do projetil inimigo
	private Color cor; //cor do tiro do inimigo
	private long proxProjetil;
	
	public ConjuntoProjeteisInimigos(double raioProjetilInimigo,Color corProjetilInimigo, long tempoAtual){
		this.projeteisinimigo = new ArrayList<ProjetilInimigo>();
		
		this.raio = raioProjetilInimigo;
		this.cor = corProjetilInimigo;
		
		this.proxProjetil = tempoAtual +  100;
	}
	
	
	

	public void verificaNovoProjetil(long tempoAtual) {
		if (tempoAtual > this.proxProjetil) criaNovoProjetil(tempoAtual);
	}
	//recebe atributos do inimigo?
	public void criaNovoProjetil(long tempoAtual) {
		ProjetilInimigo novoProjetil = new ProjetilInimigo(Math.random() * (GameLib.WIDTH - 20.0) + 10.0, -10.0, 0.20 + Math.random() * 0.15, tempoAtual + 500, this.raio, this.cor);
		projeteisinimigo.add(novoProjetil);
		this.proxProjetil = tempoAtual + 200;
	}
	
	public void verificaEstado(long tempoAtual, long delta) {
		// Cria um iterador para percorrer a ArrayList de inimigos
		Iterator<ProjetilInimigo> i = projeteisinimigo.iterator();
		
		while (i.hasNext()) {
			// Caso um projetil tenha ficado inativo (saído da tela), remove-o da ArrayList
			if (i.next().verificaEstado(tempoAtual, delta) == INACTIVE) {
				i.remove();
			}
		}
	}

	public void desenha(long tempoAtual) {
		for (ProjetilInimigo i : this.projeteisinimigo) i.desenha(tempoAtual);
	}

}
