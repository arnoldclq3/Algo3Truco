package truco.controlador;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import truco.modelo.Jugador;

public class ControladorRetruco implements EventHandler<ActionEvent>{

	private Jugador jugador;


	public ControladorRetruco(Jugador jugador){
		this.jugador = jugador;
	}
	
	
	@Override
	public void handle(ActionEvent event) {
		this.jugador.retruco();
	}

}
