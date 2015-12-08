package truco.interfaz;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class VistaManoJugador {
	
	private HBox manoJugador;

	public VistaManoJugador(){
		this.iniciarManoJugador();	
	}

	private void iniciarManoJugador() {
		this.manoJugador = new HBox();
		this.manoJugador.setSpacing(10);
		this.manoJugador.getChildren().add( this.iniciarBotonCarta() );
		this.manoJugador.getChildren().add( this.iniciarBotonCarta() );
		this.manoJugador.getChildren().add( this.iniciarBotonCarta() );
	}

	private Button iniciarBotonCarta() {
		Button botonCarta = new Button();
		botonCarta.setMinSize(100, 200);
		botonCarta.setMaxSize(100, 200);
		return botonCarta;
	}

	public Node obtenerVista() {
		return manoJugador;
	}
	
	

}
