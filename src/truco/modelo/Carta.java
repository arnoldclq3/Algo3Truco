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
	
	@Override
	public boolean equals(Object objeto){
		
		if ( objeto instanceof Carta ) {
			
			Carta unaCarta = (Carta)objeto;
			return ( this.valor == unaCarta.valor && this.palo == unaCarta.palo );
			
		} else {
			
			return false;
		}
	}
	
}
