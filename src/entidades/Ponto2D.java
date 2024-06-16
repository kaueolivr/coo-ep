package entidades;

public class Ponto2D {
	private double x; // Coordenada x
	private double y; // Coordenada y
	
	private double vX; // Velocidade no eixo x
	private double vY; // Velocidade no eixo y
	
	protected Ponto2D(double x, double y, double vX, double vY) {
		this.x = x;
		this.y = y;
		this.vX = vX;
		this.vY = vY;
	}

	protected double getX() {
		return x;
	}

	protected void setX(double x) {
		this.x = x;
	}

	protected double getY() {
		return y;
	}

	protected void setY(double y) {
		this.y = y;
	}

	protected double getvX() {
		return vX;
	}

	protected void setvX(double vX) {
		this.vX = vX;
	}

	protected double getvY() {
		return vY;
	}

	protected void setvY(double vY) {
		this.vY = vY;
	}
	
	
	// Calcula a distância entre dois pontos
	protected double distancia(Ponto2D outroPonto) {
		double dx = this.x - this.getX();
		double dy = this.y - this.getY();
		double dist = Math.sqrt(dx * dx + dy * dy);
		
		return dist;
	}
}