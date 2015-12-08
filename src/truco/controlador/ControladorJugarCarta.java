package truco.controlador;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import truco.modelo.Jugador;

public class ControladorJugarCarta implements EventHandler<ActionEvent> {

	private Jugador miJugador;
	
	public ControladorJugarCarta(Jugador jugador) {

		this.miJugador = jugador;
	}
	
	@Override
    public void handle(ActionEvent actionEvent) {

		
    }
}
