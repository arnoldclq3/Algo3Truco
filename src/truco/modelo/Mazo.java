package truco.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Mazo {

	private List<Carta> cartas;
	
	public Mazo() {
		
		this.cartas = new ArrayList<Carta>();
		
		for (int valorCarta = 1; valorCarta <= 7; valorCarta ++)
			for (Palo paloCarta : Palo.values() )
				this.cartas.add( new Carta(valorCarta,paloCarta) );
		
		for (int valorCarta = 10; valorCarta <= 12; valorCarta ++)
			for (Palo paloCarta : Palo.values() )
				this.cartas.add( new Carta(valorCarta,paloCarta) );
	}
	
	
	public int cantidadCartas() {
		
		return cartas.size();
	}
	
	public Carta repartirCarta() {
		
		Random random = new Random();
		int posicion = (random.nextInt(40));
		
		return cartas.remove(posicion);
	}
	
	public boolean contieneEstaCarta(Carta unaCarta) {
		
		return cartas.contains(unaCarta);
	}

}
