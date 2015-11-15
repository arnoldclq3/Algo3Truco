package truco.modelo;

import java.util.HashMap;

public class EscalaDeCartas {

	private HashMap<Carta,Integer> escala;
	
	public EscalaDeCartas() {
		
		this.escala = new HashMap<Carta,Integer>();
		this.establecerEscala();
	}
	
	public int enfrentarCartas(Carta carta1, Carta carta2) {
		
		int valorCarta1 = this.escala.get(carta1);
		int valorCarta2 = this.escala.get(carta2);
		
		if (valorCarta1 == valorCarta2)
			return 0;
		if (valorCarta1 > valorCarta2)
			return 1;
		return -1;
	}
	
	
	private void agregarTodasLasCartasConValor( int valorCarta ,int valorEscala ){
		
		for ( Palo unPalo : Palo.values() )
			this.escala.put( new Carta( valorCarta , unPalo ) , valorEscala );
		
	}
	
	
	private void establecerEscala() {
		
		// Los 4 tendran el valor 1, los 5 el valor 2, y los 6 el valor 3
		for (int valorCarta = 4 ; valorCarta < 7 ; valorCarta ++)
			this.agregarTodasLasCartasConValor( valorCarta  , valorCarta - 3 );
		// Se agregan el 7 de Basto y 7 de Copa con valor 4.
		this.escala.put( new Carta( 7 , Palo.BASTO ) , 4 );
		this.escala.put( new Carta( 7 , Palo.COPA ) , 4 );
		// Se agrega a las escalas (Key : Value) = (10 : 5) (11 : 6) (12 : 7)
		for (int valorCarta = 10 ; valorCarta <= 12 ; valorCarta ++)
			this.agregarTodasLasCartasConValor(valorCarta, valorCarta - 5);
		// Se agregan el 1 de Oro y 7 de Copa con valor 8.
		this.escala.put( new Carta( 1 , Palo.ORO ) , 8 );
		this.escala.put( new Carta( 1 , Palo.COPA ) , 8 );
		// Se agrega a las escalas (Key : Value) = (2 : 9) (3 : 10)
		for (int valorCarta = 2 ; valorCarta <= 3 ; valorCarta ++)
			this.agregarTodasLasCartasConValor(valorCarta, valorCarta + 7);
		// Se agregan las 4 Cartas mas valiosas con los mayores valores de la escala
		this.escala.put( new Carta( 7 , Palo.ORO ) , 11 );
		this.escala.put( new Carta( 7 , Palo.ESPADA ) , 12 );
		this.escala.put( new Carta( 1 , Palo.BASTO ) , 13 );
		this.escala.put( new Carta( 1 , Palo.ESPADA ) , 14 );
		
	}
}
