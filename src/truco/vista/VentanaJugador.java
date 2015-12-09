package truco.vista;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import truco.modelo.Jugador;

public class VentanaJugador {
	
	private GridPane vistas;
	private Scene escenaJugador;
	private Jugador miJugador;

	public VentanaJugador(Jugador jugador){
		
		this.miJugador = jugador;
		this.iniciarGridVistas();
		this.iniciarBotonera();
		this.iniciarManoJugador();
		//this.iniciarMesa();
	}
	
	public void mostrar(Stage stage) {

		stage.setScene(this.escenaJugador);
	}
	
	private void iniciarGridVistas() {
		this.vistas = new GridPane();
		this.vistas.setAlignment(Pos.CENTER);
		this.vistas.setHgap(10);
		this.vistas.setVgap(10);
		this.vistas.setPadding( new Insets(25, 25, 25, 25) );
		
        BackgroundSize backgroundSize2 = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage backgroundImage2 = new BackgroundImage(new Image("file:Imagenes/FONDO.jpg"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize2);
        Background background2 = new Background(backgroundImage2);
		
        this.vistas.setBackground(background2);
		
        Text tituloPrincipal = new Text(this.miJugador.getNombre());
        tituloPrincipal.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        tituloPrincipal.setTextAlignment(TextAlignment.CENTER);
        tituloPrincipal.setFill(Color.WHITE);
        this.vistas.add(tituloPrincipal, 1, 0);
        
		this.escenaJugador = new Scene(vistas, 1050, 650, Color.BLACK);
	}

	private void iniciarBotonera() {
		VistaBotonera botonera = new VistaBotonera();
		this.vistas.add(botonera.obtenerVista(), 1, 1);
	}
	
	private void iniciarManoJugador(){
		VistaManoJugador vistaManoJugador = new VistaManoJugador(miJugador);
		miJugador.addObserver(vistaManoJugador);
		this.vistas.add(vistaManoJugador.obtenerVista(), 0, 1);
	}
	
	public void iniciarMesa(){
		VistaMesa vistaMesa = new VistaMesa(miJugador);
		this.miJugador.getMesa().addObserver(vistaMesa);
		this.vistas.add(vistaMesa.obtenerVista(), 0, 0);
	}
}
