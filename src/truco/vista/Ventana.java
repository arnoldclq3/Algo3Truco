package truco.vista;

import truco.modelo.*;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public abstract class Ventana {
	
	public abstract void crearVentana(Stage principal);
	
	protected ImageView conseguirImagen(Carta carta) {
		
		Image imagen = new Image("file:Imagenes/nada.jpg");
		
		if ( carta != null ) {
			imagen = new Image("file:Imagenes/"+carta.getValor()+carta.getPalo().toString()+".jpg");
		}
		
		return ( new ImageView(imagen) );
	}
}
