package truco.modelo;

public class Carta {

	private int valor;
	private Palo palo;
	
	public Carta(int valor, Palo palo) {
		
		this.valor = valor;
		this.palo = palo;
	}
	
	boolean mismoPalo(Carta carta) {
		
		return ( this.palo == carta.palo );
	}
	
	int getValor() {
		
		return this.valor;
	}
	
	/*
	public String getNombre() {
		
		String nombre;
		
		if (this.valor == 1) { 
			nombre = "ancho" + palo;
		}else{
			nombre = this.valor + this.palo;
		}
		
		return nombre;
	}
	*/
	
	@Override
	public boolean equals(Object unObjeto){
		
		if ( ! ( unObjeto  instanceof Carta ) )
			return false;
			
		Carta unaCarta = (Carta)unObjeto;
		return ( this.valor == unaCarta.valor && this.palo == unaCarta.palo );
			
	
	}
	
	@Override
	public int hashCode(){
		return ( this.valor * 51 + this.palo.valor() ) * 17;
		
	}
	
}
