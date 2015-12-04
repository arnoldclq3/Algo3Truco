package truco.controlador;

import truco.modelo.*;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ControladorBotonRepartirCartas implements EventHandler<ActionEvent> {

	private Jugador unJugador;
	private Mazo unMazo;
	
	public ControladorBotonRepartirCartas(Jugador unJugador, Mazo unMazo){
		
		this.unJugador = unJugador;
		this.unMazo = unMazo;
	}
	
	@Override
    public void handle(ActionEvent actionEvent) {

		this.unJugador.tomarCarta(this.unMazo.repartirCarta());
		this.unJugador.tomarCarta(this.unMazo.repartirCarta());
		this.unJugador.tomarCarta(this.unMazo.repartirCarta());
		
    }
}