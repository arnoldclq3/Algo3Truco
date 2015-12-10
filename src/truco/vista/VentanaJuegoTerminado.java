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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class VentanaJuegoTerminado {

	private Scene miEscena;
	private VBox vistaJuegoTerminado;
	
	public VentanaJuegoTerminado() {
		
		this.iniciarVistaJuegoTerminado();
	}

	private void iniciarVistaJuegoTerminado() {

		vistaJuegoTerminado = new VBox();
		vistaJuegoTerminado.setAlignment(Pos.CENTER);
		vistaJuegoTerminado.setSpacing(10);
		vistaJuegoTerminado.setPadding( new Insets(25, 25, 25, 25) );
		
        BackgroundSize backgroundSize2 = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage backgroundImage2 = new BackgroundImage(new Image("file:Imagenes/FONDO2.jpg"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize2);
        Background background2 = new Background(backgroundImage2);
		
        vistaJuegoTerminado.setBackground(background2);
        vistaJuegoTerminado.getChildren().add(this.nuevoTitulo("JUEGO FINALIZADO"));
		this.miEscena = new Scene(vistaJuegoTerminado, 1200, 750, Color.BLACK);
	}
	
	private Text nuevoTitulo(String string) {
		
		Text texto = new Text(string);
		texto.setFont(Font.font("Tahoma", FontWeight.BOLD, 70));
		texto.setTextAlignment(TextAlignment.CENTER);
		texto.setFill(Color.WHITE);
        
        return texto;
	}
	
	private Text nuevoSubTitulo(String string) {
		
		Text texto = new Text(string);
		texto.setFont(Font.font("Tahoma", FontWeight.NORMAL, 40));
		texto.setTextAlignment(TextAlignment.CENTER);
		texto.setFill(Color.WHITE);
        
        return texto;
	}

	public void agregarNombreGanador(String nombre) {

		this.vistaJuegoTerminado.getChildren().add(this.nuevoSubTitulo("Ganadores"));	
		this.vistaJuegoTerminado.getChildren().add(this.nuevoSubTitulo(nombre));
	}
	
	public void mostrar(Stage stage) {

		stage.setScene(this.miEscena);
	}
}
