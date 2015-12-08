package truco.controlador;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import truco.modelo.Carta;
import truco.modelo.Jugador;

public class ControladorJugarCarta implements EventHandler<ActionEvent> {

	private Jugador miJugador;
	private Carta cartaQueRepresento;
	
	public ControladorJugarCarta(Jugador jugador, Carta cartaQueRepresento) {

		this.miJugador = jugador;
		this.cartaQueRepresento = cartaQueRepresento;
	}
	
	@Override
    public void handle(ActionEvent actionEvent) {

		this.miJugador.jugarCarta(cartaQueRepresento);
    }
}
