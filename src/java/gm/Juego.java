package gm;

public class Juego{
    private Integer idJuego;
    private String descJuego;
    private Integer numNivell;
    private Integer puntosPorNivel;

    public Juego (Integer idJuego, String descJuego, Integer numNivell, Integer puntosPorNivel){
        this.idJuego = idJuego;
        this.descJuego = descJuego;
        this.numNivell = numNivell;
        this.puntosPorNivel = puntosPorNivel;
    }
    public Juego (){

    }

    public Integer getPuntosPorNivel() {
        return puntosPorNivel;
    }

    public void setPuntosPorNivel(Integer puntosPorNivel) {
        this.puntosPorNivel = puntosPorNivel;
    }

    public Integer getIdJuego() {
        return idJuego;
    }

    public void setIdJuego(Integer idJuego) {
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
