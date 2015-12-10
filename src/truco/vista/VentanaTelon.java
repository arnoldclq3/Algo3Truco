package truco.vista;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class VentanaTelon {

	private Stage stage;
	private Scene miEscena;
	private VentanaJugador ventanaJugador;
	private String nombreJugador;
	private VBox vistaTelon;
	
	public VentanaTelon(Stage stage, VentanaJugador ventanaJugador, String nombreJugador) {
		
		this.stage = stage;
		this.ventanaJugador = ventanaJugador;
		this.nombreJugador = nombreJugador;
		this.iniciarVistaTelon();
	}
	
	private void iniciarVistaTelon() {

		vistaTelon = new VBox();
		vistaTelon.setAlignment(Pos.CENTER);
		vistaTelon.setSpacing(10);
		vistaTelon.setPadding( new Insets(25, 25, 25, 25) );
		
        BackgroundSize backgroundSize2 = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage backgroundImage2 = new BackgroundImage(new Image("file:Imagenes/FONDO2.jpg"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize2);
        Background background2 = new Background(backgroundImage2);
		
        vistaTelon.setBackground(background2);
        vistaTelon.getChildren().add(this.nuevoTitulo("TELON"));
        vistaTelon.getChildren().add(this.nuevoTitulo("Se van a mostrar las cartas de:\n"+this.nombreJugador));
        vistaTelon.getChildren().add(this.nuevoBotonMostrar());
		this.miEscena = new Scene(vistaTelon, 1200, 750, Color.BLACK);
	}
	
	private Text nuevoTitulo(String string) {
		
		Text texto = new Text(string);
		texto.setFont(Font.font("Tahoma", FontWeight.BOLD, 40));
		texto.setTextAlignment(TextAlignment.CENTER);
		texto.setFill(Color.WHITE);
        
        return texto;
	}
	
	private Button nuevoBotonMostrar() {
		
		Button botonMostrar = new Button("MOSTRAR");
		botonMostrar.setOnAction(e->{
			this.ventanaJugador.mostrar();
		});
		
		return botonMostrar;		
	}
	
	public void mostrar() {

		this.stage.setScene(this.miEscena);
	}
}
