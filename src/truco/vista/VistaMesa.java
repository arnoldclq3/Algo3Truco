package truco.vista;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import truco.modelo.Carta;
import truco.modelo.Jugador;
import truco.modelo.Mano;
import truco.modelo.Mesa;
import truco.modelo.Ronda;

public class VistaMesa implements Observer {
	
	private HBox cartasJugadorUno;
	private HBox cartasJugadorDos;
	private GridPane vistaMesa;
	private ProveedorDeImagenDeCarta miProveedor;
	private Jugador miJugador;
	private Ronda miRonda;
	private Mesa miMesa;
	private List<Carta> cartasMostradas;

	public VistaMesa(Jugador miJugador){
		
		this.iniciarCajas();
		this.miProveedor = new ProveedorDeImagenDeCarta();
		this.miJugador = miJugador;
		this.miMesa = this.miJugador.getMesa();
		this.cartasMostradas = new LinkedList<Carta>();
	}
	
	private void iniciarCajas() {
		this.cartasJugadorUno = this.iniciarCajaHorizontal();
		this.cartasJugadorDos = this.iniciarCajaHorizontal();
		this.iniciarGridPrincipal();
	}

	private HBox iniciarCajaHorizontal(){
		HBox cajaARetornar = new HBox();
		cajaARetornar.setAlignment(Pos.CENTER);
		cajaARetornar.setMaxSize(100, 60);
		cajaARetornar.setSpacing(-30);
		return cajaARetornar;
	}
	
	private void iniciarGridPrincipal(){
			
		this.vistaMesa = new GridPane();
		this.vistaMesa.setAlignment(Pos.CENTER);
		this.vistaMesa.setHgap(10);
		this.vistaMesa.setVgap(10);
		this.vistaMesa.setPadding(new Insets(25, 25, 25, 25));

		this.vistaMesa.getRowConstraints().add(new RowConstraints(80));
		this.vistaMesa.getRowConstraints().add(new RowConstraints(80));
		this.vistaMesa.getRowConstraints().add(new RowConstraints(100));
    
		BackgroundSize backgroundSize = new BackgroundSize(1000, 1000, true, true, true, false);
		BackgroundImage backgroundImage = new BackgroundImage(new Image("file:Imagenes/MESA.png"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
		Background background = new Background(backgroundImage);
    
		this.vistaMesa.setBackground(background);
	
		this.vistaMesa.add(this.cartasJugadorUno, 0, 1);
		this.vistaMesa.add(this.cartasJugadorDos, 0, 0);
	}
	
	private ImageView iniciarImageView(Carta carta){
		ImageView cartaJugada = new ImageView(this.miProveedor.obtenerImagenParaCarta(carta));
		cartaJugada.setFitWidth(50);
		cartaJugada.setPreserveRatio(true);
		return cartaJugada;
	}
	
	public Node obtenerVista() {
		return this.vistaMesa;
	}

	@Override
	public void update(Observable o, Object arg) {
	
		if ( this.miMesa == null || this.miRonda == null ) {
			this.miMesa = this.miJugador.getMesa();
			this.miRonda = this.miMesa.getRondaActual();
		}
		
		if ( this.miRonda != null && this.miRonda.estaTerminada() ) {
			
			this.cartasMostradas.clear();
			this.cartasJugadorUno.getChildren().clear();
			this.cartasJugadorDos.getChildren().clear();
			this.miRonda = this.miMesa.getRondaActual();
		}
		
		Mesa miMesa = (Mesa)o;
		List<Mano> manos = miMesa.getRondaActual().obtenerManos();
		for ( Mano mano : manos ) {
			Collection<Carta> cartas = mano.devolverCartas();
			for ( Carta carta : cartas ) {
				if ( !cartasMostradas.contains(carta) ) {
					Carta miCarta = mano.mostrarCartaDelJugador(miJugador);
					if ( carta == miCarta ) 
						this.cartasJugadorUno.getChildren().add(this.iniciarImageView(carta));
					else
						this.cartasJugadorDos.getChildren().add(this.iniciarImageView(carta));
					this.cartasMostradas.add(carta);
				}
			}
		}
	}
	
	
  
	

}
