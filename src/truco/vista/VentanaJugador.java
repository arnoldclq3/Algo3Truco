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
	private Jugador miJugador;
	private Scene escenaJugador;
	private Stage miStage;
	private VistaMensajeDelUltimoCantoRealizado vistaMensajeDelUltimoCantoRealizado;

	public VentanaJugador(Stage stage, Jugador jugador){
		
		this.miStage = stage;
		this.miJugador = jugador;
		this.iniciarGridVistas();
		this.iniciarBotonera();
		this.iniciarManoJugador();
		this.iniciarMesa();
		this.iniciarVistaPuntaje();
		this.iniciarVistaMensajeDelUltimoCantoRealizado();
	}

	public void mostrar() {

		this.miStage.setScene(this.escenaJugador);
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
        this.agregarNombreJugador();
		this.escenaJugador = new Scene(vistas, 1200, 750, Color.BLACK);
	}
	
	public void iniciarVistaPuntaje() {
		
		VistaPuntaje vistaPuntaje = new VistaPuntaje(this.miJugador);
		this.miJugador.getMesa().addObserver(vistaPuntaje);
		this.vistas.add(vistaPuntaje.obtenerVista(), 1, 1);
	}
	
	private void iniciarBotonera() {
		VistaBotonera botonera = new VistaBotonera(this.miJugador);
		this.miJugador.getMesa().addObserver(botonera);
		this.vistas.add(botonera.obtenerVista(), 1, 2);
	}
	
	private void iniciarManoJugador(){
		VistaManoJugador vistaManoJugador = new VistaManoJugador(this.miJugador);
		this.miJugador.addObserver(vistaManoJugador);
		this.miJugador.getMesa().addObserver(vistaManoJugador);
		this.vistas.add(vistaManoJugador.obtenerVista(), 0, 2);
	}
	
	public void iniciarMesa(){
		VistaMesa vistaMesa = new VistaMesa(this.miJugador);
		this.miJugador.getMesa().addObserver(vistaMesa);
		this.vistas.add(vistaMesa.obtenerVista(), 0, 1);
	}
	
	private void agregarNombreJugador() {
		
		Text texto = new Text(this.miJugador.getNombre());
		texto.setFont(Font.font("Tahoma", FontWeight.BOLD, 30));
		texto.setTextAlignment(TextAlignment.CENTER);
		texto.setFill(Color.WHITE);
		
		this.vistas.add(texto, 0, 3);
	}
	
	private void iniciarVistaMensajeDelUltimoCantoRealizado() {
		this.vistaMensajeDelUltimoCantoRealizado = new VistaMensajeDelUltimoCantoRealizado();
		this.miJugador.getMesa().addObserver(vistaMensajeDelUltimoCantoRealizado);
		this.vistas.add(this.vistaMensajeDelUltimoCantoRealizado.obtenerCajaMuestroDeCantos(), 1, 3);
	}

}
