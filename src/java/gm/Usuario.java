package gm;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String idUsuario;
    private String nombreUsuario;
    private Partida partidaActual;
    private Integer numPuntos;
    private List<Partida> partidasJugadasList;
    private List<Usuario> listaUsuarios;
    private Juego juego;

    public Usuario(String idUsuario, String nombreUsuario, Partida partidaActual, Integer numPuntos, Juego juego){
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.partidaActual = partidaActual;
        this.numPuntos = numPuntos;
        partidasJugadasList = new ArrayList<>();
        listaUsuarios = new ArrayList<>();
        this.juego = juego;
    }
    public Usuario(){
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public Juego getJuego() {
        return juego;
    }

    public void setJuego(Juego juego) {
        this.juego = juego;
    }

    public Partida getPartidaActual() {
        return partidaActual;
    }

    public void setPartidaActual(Partida partidaActual) {
        this.partidaActual = partidaActual;
    }

    public List<Partida> getPartidasJugadasList() {
        return partidasJugadasList;
    }

    public void setPartidasJugadasList(List<Partida> partidasJugadasList) {
        this.partidasJugadasList = partidasJugadasList;
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getNumPuntos() {
        return numPuntos;
    }

    public void setNumPuntos(Integer numPuntos) {
        this.numPuntos = numPuntos;
    }

    public List<Usuario> getListaUsuarios(Integer idJuego) {
        List<Usuario> usuarios = new ArrayList<>();
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }
    public List<Partida> getPartidas() {
        List<Partida> partidasUsuario = new ArrayList<>();
        for (Partida partida : partidasJugadasList) {
            if (partida.getUsuario().equals(this)) {
                partidasUsuario.add(partida);
            }
        }
        return partidasUsuario;
    }
}
