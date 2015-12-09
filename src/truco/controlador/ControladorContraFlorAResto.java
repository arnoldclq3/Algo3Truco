package truco.controlador;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import truco.modelo.Jugador;

public class ControladorContraFlorAResto implements EventHandler<ActionEvent>{

	private Jugador jugador;


	public ControladorContraFlorAResto(Jugador jugador){
		this.jugador = jugador;
	}
	
	
	@Override
	public void handle(ActionEvent event) {
		this.jugador.contraFlorAResto();
	}

}
