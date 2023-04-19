package gm;

import java.util.List;

public interface GameManager {
  //  public void addJuego (Juego juego);
    public Juego crearJuego(String idJuego, String descJuego, int nivelActual, int puntosPorNivel);
    public Juego inicioPartida(String idJ, String idU);
    public int getNumNivellActual(String idU);
    public String getNumPuntos(String idU);
    public Usuario pasarNivel(String idU, int puntosAcumulados, String fechaInicio);
    public Usuario finalizarPartida(String user);
    // Consulta de usuarios que han participado en un juego ordenado por puntuación (descendente)
    public Usuario consultarUsuariosPorJuego(Juego juego);
    public List<Partida> partidaUsuario(String nomUsuario);
    public List<String> infoActividad(String nomUsuario, Juego juego);

    /////////////////////////////////////////////////////////////////

    public Juego buscarJuego(String identificadorJuego);
    public Usuario buscarUsuario(String identificadorUsuario);
    public Partida buscarPartida(String usuarioId);
    public Usuario getUser(String idUser);
    public Usuario addUsuario(String idUser);
    public int sizeUsuarios();
    public int sizeJuegos();
    public int getListaPartidas();
    public void clear();
}