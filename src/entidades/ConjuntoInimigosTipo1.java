package entidades;

import java.util.ArrayList;
import java.util.Iterator;

import entidades.interfaces.Inimigo;

import java.awt.Color;

import lib.GameLib;

public class ConjuntoInimigosTipo1 implements Inimigo {
	// Constantes relacionadas aos estados que os inimigos de tipo 1 podem assumir
	public static final int INACTIVE = 0;
	public static final int ACTIVE = 1;
	public static final int EXPLODING = 2;
	
	private ArrayList<InimigoTipo1> inimigos;
	
	private double angulo;
	private double velocidadeRotacao;
	private Color cor;
	private double raio; // Tamanho/raio
	private long proxInimigo; // Instante em que um novo inimigo de tipo 1 deve aparecer
	
	public ConjuntoInimigosTipo1(double anguloInimigos1, double velocidadeRotacaoInimigos1, double raioInimigos1, Color corInimigos1, long tempoAtual) {
		this.inimigos = new ArrayList<InimigoTipo1>();
	
		this.angulo = anguloInimigos1;
		this.velocidadeRotacao = velocidadeRotacaoInimigos1;
		this.raio = raioInimigos1;
		this.cor = corInimigos1;
		
		this.proxInimigo = tempoAtual + 2000;
	}
	
	// Verifica se novos inimigos de tipo 1 devem ser criados e o faz, caso necessário
	public void verificaNovoInimigo(long tempoAtual) {
		if (tempoAtual > this.proxInimigo) criaNovoInimigo(tempoAtual);
	}
	
	// Cria um novo inimigo de tipo 1 e o adiciona na ArrayList
	public void criaNovoInimigo(long tempoAtual) {
		InimigoTipo1 novoInimigo = new InimigoTipo1(Math.random() * (GameLib.WIDTH - 20.0) + 10.0, -10.0, 0.20 + Math.random() * 0.15, this.angulo, this.velocidadeRotacao, tempoAtual + 500, this.raio, this.cor);
		inimigos.add(novoInimigo);
		this.proxInimigo = tempoAtual + 500;
	}
	
	// Verifica o estado de todos os inimigos
	public void verificaEstado(long tempoAtual, long delta) {
		// Cria um iterador para percorrer a ArrayList de inimigos
		Iterator<InimigoTipo1> i = inimigos.iterator();
		
		while (i.hasNext()) {
			// Caso um inimigo tenha ficado inativo (saído da tela), remove-o da ArrayList
			if (i.next().verificaEstado(tempoAtual, delta) == INACTIVE) {
				i.remove();
			}
		}
	}
	
	// Desenha todos os inimigos
	public void desenha(long tempoAtual) {
		for (InimigoTipo1 i : this.inimigos) i.desenha(tempoAtual);
	}
}