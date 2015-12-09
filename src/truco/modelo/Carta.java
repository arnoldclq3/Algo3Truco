package truco.modelo;

public class Carta {

	private int valor;
	private Palo palo;
	private static final EscalaDeCartas escala = new EscalaDeCartas();
	
	public Carta(int valor, Palo palo) {
		
		this.valor = valor;
		this.palo = palo;
	}
	
	boolean mismoPalo(Carta carta) {
		
		return ( this.palo == carta.palo );
	}
	
	public int getValor() {
		
		return this.valor;
	}
	
	public int compararCon(Carta unaCarta){
		return Carta.escala.enfrentarCartas(this, unaCarta);		
	}
	
	
	@Override
	public boolean equals(Object unObjeto){
		
		if ( ! ( unObjeto  instanceof Carta ) )
			return false;
			
		Carta unaCarta = (Carta)unObjeto;
		return ( this.valor == unaCarta.valor && this.palo == unaCarta.palo );
		
	}
	
	@Override
	public int hashCode(){
		return ( this.valor * 13 + this.palo.valor() ) * 3;
		
	}
	
}
