package truco.modelo;

public class Carta {

	private int valor;
	private String palo;
	
	public Carta(int valor, String palo) {
		
		this.valor = valor;
		this.palo = palo;
	}
	
	boolean mismoPalo(Carta carta) {
		
		return ( this.palo == carta.palo );
	}
	
	int getValor() {
		
		return this.valor;
	}

}
