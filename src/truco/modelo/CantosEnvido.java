package truco.modelo;

public interface CantosEnvido {
	
	public void envido(Jugador jugadorQueCanta);
	public void realEnvido(Jugador jugadorQueCanta);
	public void faltaEnvido(Jugador jugadorQueCanta);
	public void cantarTantoDelEnvido(Jugador jugadorQueCanta);
	public void sonBuenas(Jugador jugadorQueCanta);
}
