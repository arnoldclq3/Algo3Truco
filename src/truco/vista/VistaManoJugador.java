package truco.vista;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import truco.controlador.ControladorJugarCarta;
import truco.modelo.Carta;
import truco.modelo.Jugador;

public class VistaManoJugador implements Observer {
	
	private HBox manoJugador;
	private ProveedorDeImagenDeCarta miProveedor;
	
	public VistaManoJugador(){
		this.iniciarManoJugador();
		this.miProveedor = new ProveedorDeImagenDeCarta();
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

	@Override
	public void update(Observable o, Object arg) {
		
		Jugador jugador = (Jugador)o;
		List<Carta> cartas = jugador.obtenerMano();
		ObservableList<Node> botones = this.manoJugador.getChildren();
		
		if ( cartas.size() < botones.size() ) {
			for ( int i=botones.size()-1; i>=cartas.size(); i--) {
			
				Button boton = (Button)botones.get(i);
				boton.setVisible(false);
			}
		}
		
		for ( int i=0; i<cartas.size(); i++ ) {
			
			Button boton = (Button)botones.get(i);
			Carta carta = cartas.get(i);
			ImageView imagen = new ImageView(this.miProveedor.obtenerImagenParaCarta(carta));
			boton.setVisible(true);
			boton.setGraphic(imagen);
			boton.setOnAction( new ControladorJugarCarta(jugador,carta) );
		}
	}
	
	
	
	

}
