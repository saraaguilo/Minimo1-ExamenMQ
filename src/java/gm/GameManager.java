package gm;

import java.util.Date;
import java.util.List;

public interface GameManager {
    public void addJuego (Juego juego);
    public void crearJuego(Integer idJuego, String descJuego, int nivelActual, int puntosPorNivel);
    public void inicioPartida(String idJ, String idU);
    public void getNumNivellActual(String idU);
    public void getNumPuntos(String idU);
    public void pasarNivel(String idU, int puntosAcumulados, Date fechaInicio);
    public void finalizarPartida(String user);
    // Consulta de usuarios que han participado en un juego ordenado por puntuación (descendente)
    public List<Usuario> consultarUsuariosPorJuego(Juego juego);
    public List<Partida> partidaUsuario(String nomUsuario);
    public List<String> infoActividad(String nomUsuario, Juego juego);

    /////////////////////////////////////////////////////////////////

    public Juego buscarJuego(String identificadorJuego);
    public Usuario buscarUsuario(String identificadorUsuario);
    public Partida buscarPartida(String usuarioId);
    public Usuario getUser(String idUser);
    public Usuario addUsuario(String idUser);
    public int getListaPartidas();
    public void clear();
}
