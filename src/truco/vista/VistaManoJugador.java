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
	private Jugador miJugador;
	
	public VistaManoJugador(Jugador miJugador){
		this.miJugador = miJugador;
		this.miProveedor = new ProveedorDeImagenDeCarta();
		this.iniciarManoJugador();	
	}

	private void iniciarManoJugador() {
		this.manoJugador = new HBox();
		this.manoJugador.setSpacing(-10);
		
		this.agregarCartas();
	}

	private void agregarCartas() {
		List<Carta> cartas = this.miJugador.obtenerMano();
		if (cartas == null)
			return;
		for (Carta carta : cartas)
			this.manoJugador.getChildren().add( this.iniciarBotonCarta( carta ) );
	}

	private Button iniciarBotonCarta(Carta carta) {
		Button botonCarta = new Button();
		ImageView imagen = new ImageView(this.miProveedor.obtenerImagenParaCarta(carta));
		botonCarta.setGraphic(imagen);
		botonCarta.setOnAction( new ControladorJugarCarta(this.miJugador ,carta) );
		return botonCarta;
	}

	public Node obtenerVista() {
		return manoJugador;
	}

	@Override
	public void update(Observable o, Object arg) {
		
		if ( this.miJugador.getMesa().getRondaActual().trucoOEnvidoEnProceso() )
			this.manoJugador.setDisable(true);
		else 
			this.manoJugador.setDisable(false);
		
		List<Carta> cartas = this.miJugador.obtenerMano();
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
			boton.setOnAction( new ControladorJugarCarta(this.miJugador,carta) );
		}
	}
	
	
	
	

}
