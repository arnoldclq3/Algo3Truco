package truco.modelo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import truco.excepciones.mano.NoHayGanadorHastaQueLaManoTermineException;
import truco.excepciones.mano.NoHayGanadorHuboEmpateException;
import truco.excepciones.mano.NoSePuedeJugarMasCartasManoTerminadaException;

public class Mano {

	private HashMap<Jugador,Carta> cartasJugadas;
	private ArrayList<Carta> cartasTapadas;
	private int cantidadMaximaCartasAJugar;
	private int cantidadCartasJugadas;
	private Jugador ganador;
	private boolean manoTerminada;
	private boolean huboEmpate;
	
	public Mano(int cantidad) {
		
		this.cartasJugadas = new HashMap<Jugador,Carta>();
		this.cartasTapadas = new ArrayList<Carta>();
		this.cantidadMaximaCartasAJugar = cantidad;
		this.cantidadCartasJugadas = 0;
		this.ganador = null;
		this.manoTerminada = false;
		this.huboEmpate = false;
		
	}

	public void jugarCarta(Jugador unJugador, Carta unaCarta) {
			
		if ( this.manoTerminada ) throw new NoSePuedeJugarMasCartasManoTerminadaException();
		
		this.cartasJugadas.put(unJugador, unaCarta);
		this.cantidadCartasJugadas++;
		
		if ( this.cantidadCartasJugadas == this.cantidadMaximaCartasAJugar ) {

			this.enfrentarTodasLasCartas();
			this.manoTerminada = true;
		}
	}
	
	public void jugarCartaTapada(Carta carta) {
		
		if ( this.manoTerminada ) throw new NoSePuedeJugarMasCartasManoTerminadaException();
		
		this.cartasTapadas.add(carta);
		this.cantidadCartasJugadas++; 
		
		if ( this.cantidadCartasJugadas == this.cantidadMaximaCartasAJugar ) {

			this.enfrentarTodasLasCartas();
			this.manoTerminada = true;
		}
	}
	
	public void unJugadorSeFueAlMazo() {
		
		this.cantidadCartasJugadas++;
		
		if ( this.cantidadCartasJugadas == this.cantidadMaximaCartasAJugar ) {

			this.enfrentarTodasLasCartas();
			this.manoTerminada = true;
		}
	}
	
	public Carta mostrarUltimaCartaJugadaPor(Jugador unJugador) {
		
		return ( this.cartasJugadas.get(unJugador) ); 
	}

	public boolean estaTerminada() {
		
		return this.manoTerminada;
	}

	public Jugador obtenerGanador() {
		
		if ( this.huboEmpate ) throw new NoHayGanadorHuboEmpateException();
		
		if ( !this.manoTerminada ) throw new NoHayGanadorHastaQueLaManoTermineException();
		
		return this.ganador;
	}
	
	private void enfrentarTodasLasCartas() {
		
		Carta cartaGanadora = null;
		
		Collection<Carta> cartas = this.cartasJugadas.values();
		Iterator<Carta> iteradorCarta = cartas.iterator();
		cartaGanadora = iteradorCarta.next();
		
		while (iteradorCarta.hasNext() ) {
			
			Carta carta = iteradorCarta.next();
			if ( cartaGanadora.compararCon(carta) == -1) {
				
				cartaGanadora = carta;
				this.huboEmpate = false;
			
			} else if ( cartaGanadora.compararCon(carta) == 0 ){
				
				this.huboEmpate = true;
			}
		}
		
		if ( !this.huboEmpate ) {
		
			for ( Jugador jugador : cartasJugadas.keySet() ) {
			
				Carta carta = cartasJugadas.get(jugador);
				if ( carta.equals(cartaGanadora) ) {
					this.ganador = jugador;
				}
			}
		}
	}

	public Collection<Carta> devolverCartas() {
		Collection<Carta> cartas = this.cartasJugadas.values();
		cartas.addAll(cartasTapadas);
		return cartas;
	}

	public Carta mostrarCartaDelJugador(Jugador unJugador) {
		return this.cartasJugadas.get(unJugador);
	}
}
