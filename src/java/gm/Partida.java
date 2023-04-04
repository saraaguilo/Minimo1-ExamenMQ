package gm;
import java.util.Date;

public class Partida {
    private String id;
    private Juego juego;
    private Usuario usuario;
    private int nivelActual;
    private int puntosAcumulados;
    private Date fechaInicio;

    public Partida(String id, Juego juego, Usuario usuario, int nivelActual, int puntosAcumulados, Date fechaInicio) {
        this.id = id;
        this.juego = juego;
        this.usuario = usuario;
        this.nivelActual = 1;
        this.puntosAcumulados = 50;
        this.fechaInicio = new Date();
    }

    public Partida(Juego juego, Usuario usuario){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Juego getJuego() {
        return juego;
    }

    public void setJuego(Juego juego) {
        this.juego = juego;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getNivelActual() {
        return nivelActual;
    }

    public void setNivelActual(int nivelActual) {
        this.nivelActual = nivelActual;
    }

    public int getPuntosAcumulados() {
        return puntosAcumulados;
    }

    public void setPuntosAcumulados(int puntosAcumulados) {
        this.puntosAcumulados = puntosAcumulados;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
}
