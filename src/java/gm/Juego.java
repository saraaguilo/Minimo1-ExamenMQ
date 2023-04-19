package gm;

import java.util.List;

public class Juego{
    private String idJuego;
    private String descJuego;
    private int numNivell;
    private int puntosPorNivel;
    private List<Usuario> listaUsuario;

    public Juego (String idJuego, String descJuego, int numNivell, int puntosPorNivel){
        this.idJuego = idJuego;
        this.descJuego = descJuego;
        this.numNivell = numNivell;
        this.puntosPorNivel = puntosPorNivel;
    }
    public Juego (String juego){
    }

    public Integer getPuntosPorNivel() {
        return puntosPorNivel;
    }

    public void setPuntosPorNivel(Integer puntosPorNivel) {
        this.puntosPorNivel = puntosPorNivel;
    }

    public String getIdJuego() {
        return idJuego;
    }

    public void setIdJuego(String idJuego) {
        this.idJuego = idJuego;
    }

    public String getDescJuego() {
        return descJuego;
    }

    public void setDescJuego(String descJuego) {
        this.descJuego = descJuego;
    }

    public Integer getNumNivell() {
        return numNivell;
    }

    public void setNumNivell(Integer numNivell) {
        this.numNivell = numNivell;
    }

}
