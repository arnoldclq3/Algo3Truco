package truco.controlador;

import truco.modelo.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ControladorJugadorTirarCarta implements EventHandler<ActionEvent> {

	private Jugador unJugador;
	private Carta carta;
	
	public ControladorJugadorTirarCarta(Jugador unJugador,Carta carta){
		this.unJugador = unJugador;
		this.carta = carta;
	}
	
	@Override
    public void handle(ActionEvent actionEvent) {
		try{
			this.unJugador.tirarCarta(this.carta);
			Button boton = (Button)actionEvent.getSource();
			boton.setGraphic(new ImageView(new Image("file:Imagenes/nada.jpg")));
		}catch(Exception e){
			System.out.println(this.carta);
		}
		
    }
}
