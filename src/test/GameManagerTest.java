import gm.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


public class GameManagerTest {
    private GameManager gm;

    @Before
    public void setUp() {
        //Inicialización de variables antes de cada Test
        gm = GameManagerImpl.getInstance();
        gm.clear();
    }
    @After
    public void tearDown() {
        //Tareas a realizar después de cada test
        this.gm = null;
    }
    @Test
    public void testAddJuego(){
        Juego juego1 = new Juego(1, "parchis", 1, 5);
        Juego juego2 = new Juego(2, "uno", 2, 3);
        gm.addJuego(juego1);
        gm.addJuego(juego2);
    }
    @Test
    public void testAddUsuario(){ //PREGUNTAR TONI
        Usuario usuario1 = new Usuario("1", "Sara", null, 15, null);
        Usuario usuario2 = new Usuario("2", "Anna", null, 10, null);
        gm.addUsuario(String.valueOf(usuario1));
        gm.addUsuario(String.valueOf(usuario2));
    }
    @Test
    public void testInicioPartida(){
        // Crear un juego nuevo
        Juego juego3 = new Juego(3, "ajedrez", 1, 20);
        //String j = String.valueOf(juego3);
        StringBuilder sb = new StringBuilder();
        sb.append(juego3).append(",");
        String cadenaJ3 = sb.toString();

        // Crear un usuario nuevo
        Usuario usuario3 = new Usuario("3", "Toni", null, 0, null);
        StringBuilder sp = new StringBuilder();
        sp.append(juego3).append(",");
        String cadenaU3 = sp.toString();

        // Crear una partida para el usuario en el juego
        gm.inicioPartida(cadenaJ3, cadenaU3);

        // Verificar que la partida ha sido creada
        assertEquals(1, gm.getListaPartidas());

        // Crear otra partida para el mismo usuario en el mismo juego
        gm.inicioPartida(cadenaJ3, cadenaU3);

        // Verificar que no se ha creado otra partida
        assertEquals(1, gm.getListaPartidas());

        // Crear una partida para un usuario inexistente
        gm.inicioPartida(cadenaU3, "2");

        // Verificar que no se ha creado otra partida
        assertEquals(1, gm.getListaPartidas());

        // Crear una partida para un juego inexistente
        gm.inicioPartida("2", cadenaJ3);

        // Verificar que no se ha creado otra partida
        assertEquals(1, gm.getListaPartidas());
    }
    @Test
    public void testGetNumNivellActual() {
        Juego juego1 = new Juego(1, "parchis", 1, 5);
        gm.addJuego(juego1);
        Usuario usuario1 = new Usuario("1", "Sara", null, 15, null);
        gm.addUsuario(String.valueOf(usuario1));

        gm.inicioPartida("parchis", "Sara");
        gm.getNumNivellActual("Sara");
    }


    @Test
    public void testGetNumPuntos() {
        Juego juego1 = new Juego(1, "parchis", 1, 5);
        gm.addJuego(juego1);
        Usuario usuario1 = new Usuario("1", "Sara", null, 15, null);
        gm.addUsuario(String.valueOf(usuario1));

        gm.inicioPartida("parchis", "Sara");
        gm.getNumPuntos("Sara");
    }
    @Test
    public void testPasarNivel() {
        Partida partida = new Partida("1", new Juego(1, "parchis", 1, 5), new Usuario("1", "Sara", null, 15, null), 1, 10, null);
        partida.setPuntosAcumulados(5);
        partida.setNivelActual(1);
       //FALTA BASTANTA IMPLEMENTACIÓ
    }

    @Test
    public void testFinalizarPartida() { //PREGUNTAR TONI
        // Crear un juego nuevo
        Juego juego3 = new Juego(3, "ajedrez", 1, 20);
        StringBuilder sb = new StringBuilder();
        sb.append(juego3).append(",");
        String cadenaJ3 = sb.toString();

        // Crear un usuario nuevo
        Usuario usuario3 = new Usuario("3", "Toni", null, 0, null);
        StringBuilder sp = new StringBuilder();
        sp.append(juego3).append(",");
        String cadenaU3 = sp.toString();

        // Crear una partida para el usuario en el juego
        gm.inicioPartida(cadenaJ3, cadenaU3);
    }
    @Test
    public void testInfoActividadWithInvalidUser() {
        String nomUsuario = "Carles";
        Juego juego3 = new Juego(3, "ajedrez", 1, 20);
        gm.addJuego(juego3);

        List<String> infoActividad = gm.infoActividad(nomUsuario, juego3);
        assertNull(infoActividad);
    }
}

