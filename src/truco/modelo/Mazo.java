package truco.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Mazo {

	private List<Carta> cartas;
	
	public Mazo() {
		
		this.cartas = new ArrayList<Carta>();
		
		for (int i=1; i<=7; i++) {
			
			this.cartas.add(new Carta(i,"espada"));
			this.cartas.add(new Carta(i,"basto"));
			this.cartas.add(new Carta(i,"oro"));
			this.cartas.add(new Carta(i,"copa"));
		}
		
		for (int i=10; i<=12; i++) {
			
			this.cartas.add(new Carta(i,"espada"));
			this.cartas.add(new Carta(i,"basto"));
			this.cartas.add(new Carta(i,"oro"));
			this.cartas.add(new Carta(i,"copa"));
		}
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
