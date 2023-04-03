public class Usuari {
    private String idUsuari;
    private String fecha;
    private Integer numPuntos;


    public Usuari (String idUsuari, String fecha, Integer numPuntos){
        this.idUsuari = idUsuari;
        this.fecha = fecha;
        this.numPuntos = numPuntos;
    }
    public Usuari (){
    }

    public String getIdUsuari() {
        return idUsuari;
    }

    public void setIdUsuari(String idUsuari) {
        this.idUsuari = idUsuari;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Integer getNumPuntos() {
        return numPuntos;
    }

    public void setNumPuntos(Integer numPuntos) {
        this.numPuntos = numPuntos;
    }
}
