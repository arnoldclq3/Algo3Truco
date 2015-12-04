package truco.controlador;

import truco.modelo.*;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ControladorBotonDevolverCartas implements EventHandler<ActionEvent> {

	private Jugador unJugador;
	private Mazo unMazo;
	
	public ControladorBotonDevolverCartas(Jugador unJugador, Mazo unMazo){
		
		this.unJugador = unJugador;
		this.unMazo = unMazo;
	}
	
	@Override
    public void handle(ActionEvent actionEvent) {

		this.unMazo.devolverCartas(this.unJugador.devolverCartas());
    }
}
