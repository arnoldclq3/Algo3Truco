package truco.modelo;

public enum Palo {
	
	BASTO(1),
	COPA(2),
	ESPADA(3),
	ORO(4);
	
	private int valorPalo;

	Palo(int unValor){
		this.valorPalo = unValor;
	}
	
	public int valor(){
		return this.valorPalo;
	}
	
}
