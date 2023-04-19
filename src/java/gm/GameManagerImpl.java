package gm;

import org.apache.log4j.Logger;
import java.util.*;

public class GameManagerImpl implements GameManager {
    private List<Juego> juegosList;
    private HashMap<String, Usuario> usuarioHashMap;
    private List<Partida> partidasList;
    public static GameManagerImpl instance = null;
    public static final Logger logger = Logger.getLogger(GameManagerImpl.class);

    private GameManagerImpl() {
        juegosList = new ArrayList<>();
        usuarioHashMap = new HashMap<>();
        partidasList = new ArrayList<>();
    }

    public static GameManagerImpl getInstance() {
        if (instance == null) {
            instance = new GameManagerImpl();
        }
        return instance;
    }

    public static void setInstance(GameManagerImpl instance) {
        GameManagerImpl.instance = instance;
    }

    //Creación de un juego
    public Juego crearJuego(String idJuego, String descJuego, int nivelActual, int puntosPorNivel) {
        Juego j = buscarJuego(idJuego);
        if(j==null){ //=null es que no lo hemos encontrado, asi que se puede crear
            j = new Juego(idJuego,descJuego,nivelActual,puntosPorNivel);
            juegosList.add(j);
            logger.info("Juego " + idJuego + "añadido");
            return j;
        }
        logger.warn("Ya existe un juego con el mismo id.");
        return null;
    }

    /*@Override
    public void addJuego(Juego juego) {
        juegosList.add(Integer.parseInt(juego.getIdJuego()), juego);
        logger.info("Añadiendo juego: " + juego.getIdJuego() + "descripcion: " + juego.getIdJuego() + "niveles: " + juego.getNumNivell());
    }*/

    //Inicio de una partida de un juego por parte de un usuario
    @Override
    public Juego inicioPartida(String idJ, String idU) {
        Juego juego = buscarJuego(idJ);
        if (juego == null) {
            /*logger.info("No existe este juego");
            return juego;*/
            logger.warn("No se ha encontrado el juego " + idJ);
            return null;
        }
        Usuario usuario = buscarUsuario(idU);
        if (usuario == null) {
            /*logger.info("No existe este usuario");
            return juego;*/
            logger.warn("No se ha encontrado el usuario " + idU);
            return null;
        }

        for (Partida partida : usuario.getPartidas()) {
            if (partida.getJuego().getIdJuego().equals(idJ)) {
                logger.warn("El usuario " + usuario.getIdUsuario() + " ya tiene una partida en curso para este juego.");
                return null;
            }
        }

        Partida partida = new Partida(juego, usuario);
        partida.setPuntosAcumulados(50);// comença amb 50 punts
        partida.setNivelActual(1); // comença al nivell 1
        partidasList.add(partida);
        usuario.addPartida(partida);
        logger.info("Se ha añadido una partida para el usuario " + usuario.getIdUsuario() + " en el juego " + juego.getIdJuego());
        return juego;
        /*logger.info("Añadiendo partida para el usuario: "+ usuario.getIdUsuario());
        this.usuarioHashMap.get(idU).addPartida(partida);
        logger.info("La partida ha comenzado para el usuario " + idU + " en el juego " + idJ);
        return juego;*/
    }
    public int getListaPartidas(){
        return partidasList.size();
    }

    public Juego buscarJuego(String identificadorJuego) {
        for (Juego juego : juegosList) {
            if (juego.getIdJuego().equals(identificadorJuego)) {
                return juego;
            }
        }
        return null; // Si no se encuentra el juego, se devuelve null
    }

    public Usuario buscarUsuario(String identificadorUsuario) { //PREGUNTAR TONI
        //En aquest codig hauriem de fer un:
        //public String buscarUsuario(String identificadorUsuario) {
        //pero a la funcio INICIOPARTIDA, la linia 53, Usuario usuario = buscarUsuario(idU);
        //Hauria de ser String usuario = ... pero no ens serviria perq a la linia 66:
        //Partida partida = new Partida(juego, usuario); el usuario que el treiem de partida no es un String
        /*for (Map.Entry<String, Usuario> usuario : usuarioHashMap.entrySet()) {
            String idUsuario = usuario.getKey();
            if (usuario.getKey().equals(idUsuario)) {
                return idUsuario;
            }
            return null;
        }
        return identificadorUsuario;*/
        //fiquem això millor per a que no ens saltin errors:
        return usuarioHashMap.get(identificadorUsuario);
    }

    public Partida buscarPartida(String usuarioId) {
        for (Partida partida : partidasList) {
            if (partida.getUsuario().equals(usuarioId)) {
                return partida;
            }
        }
        return null;
    }
    public Usuario getUser(String idUser) {
        logger.info("Buscando un jugador con el siguiente id: " + idUser);
        if(this.usuarioHashMap.get(idUser)==null){
            logger.warn("Jugador no encontrado");
            return null;
        }
        return this.usuarioHashMap.get(idUser);
    }
    public Usuario addUsuario(String idUser){
        if(getUser(idUser)==null){
            logger.warn("El usuario no existe, lo añadimos");
            return getUser(idUser);
        }
        return null;
    }

    @Override
    public int getNumNivellActual(String idU) {
        Partida partida = buscarPartida(idU);
        if (partida == null) {
            logger.warn("No existe la partida");
            return 0;
        }
        logger.info("Usuario " + idU + " en partida " + partida.getId() + " en nivel " + partida.getNivelActual());
        //Si no es passesin parametres seria de la seguent manera:
        /*public int getNumNivellActual() {
            int numNivell = juegosList.size();
            logger.info("Numero de niveles:" + numNivell);
        return numNivell;*/
        return partida.getNivelActual();
    }

    @Override
    public String getNumPuntos(String idU) {
        Partida partida = buscarPartida(idU);
        if (partida == null) {
            logger.warn("No existe la partida");
            return idU;
        }
        logger.info("Usuario " + idU + " en partida " + partida.getId() + " con los puntos " + partida.getPuntosAcumulados());
        //return idU;
        return Integer.toString(partida.getPuntosAcumulados());
    }
    //Si no es passesin parametres seria de la seguent manera:
    /*public int getNumPuntos() {
        int numPuntos = usuarioHashMap.size();
        logger.info("Numero de niveles:" + numPuntos);
        return numPuntos;
    }*/

    @Override
    public Usuario pasarNivel(String idU, int puntosAcumulados, String fechaInicio) {
        //avanzar al siguiente nivel de una partida en curso para un usuario dado
        //busca una partida en curso para el usuario con el identificador 'idU'
        Partida partida = buscarPartida(idU);
        if (partida == null) {
            logger.warn("No se encontró ninguna partida en curso");
            return null;
        }

        //se recuperan algunos valores importantes para la partida, como el nivel actual,
        // el número total de niveles para el juego y los puntos acumulados hasta el momento
        int nivelActual = partida.getNivelActual();
        int numNiveles = partida.getJuego().getNumNivell();
        int puntosAnteriores = partida.getPuntosAcumulados();
        int nuevosPuntos = puntosAnteriores + puntosAcumulados;

        //se verifica si el usuario ha completado todos los niveles. Si es así, se agregan
        // 100 puntos adicionales a la puntuación acumulada y se finaliza la partida
        if (nivelActual == numNiveles) {
            logger.info("El usuario ha completado todos los niveles y se han sumado 100 puntos adicionales");
            partida.setPuntosAcumulados(nuevosPuntos + 100);
            finalizarPartida(idU);
        } else {

            //Si el usuario no ha completado todos los niveles, se calcula la cantidad de puntos
            // necesarios para avanzar al siguiente nivel
            int puntosParaSiguienteNivel = partida.getJuego().getPuntosPorNivel() * nivelActual; //PODRIEM FER UN COMPARETO????
            if (nuevosPuntos >= puntosParaSiguienteNivel) {

                //Si la cantidad de puntos acumulados por el usuario es igual o mayor que la cantidad necesaria para avanzar, el nivel actual se incrementa en uno, se actualizan
                // los puntos acumulados y se registra un mensaje informando al usuario que ha pasado al siguiente nivel
                int nuevosNivel = nivelActual + 1;
                partida.setNivelActual(nuevosNivel);
                partida.setPuntosAcumulados(nuevosPuntos);
                logger.info("El usuario ha pasado al nivel " + nuevosNivel);
            } else {
                //Si no se han acumulado suficientes puntos para avanzar, se actualizan los puntos acumulados y se informa
                // al usuario cuántos puntos faltan para pasar al siguiente nivel.
                partida.setPuntosAcumulados(nuevosPuntos);
                logger.warn("El usuario ha acumulado " + nuevosPuntos + " puntos, " + (puntosParaSiguienteNivel - nuevosPuntos) + " puntos faltan para pasar al siguiente nivel.");
            }
        }
        return null;
    }

    @Override
    public Usuario finalizarPartida(String user) {
        Partida partida = buscarPartida(user);
        if (partida == null) {
            logger.warn("No se encontró una partida en curso para el usuario especificado");
             return null;
        }
        Usuario usuario = partida.getUsuario();
        Juego juego = partida.getJuego();
        int puntuacionActual = partida.getPuntosAcumulados();
        partidasList.remove(partida);
        logger.info("Partida finalizada para el usuario " + user + "con la siguiente puntuacion: " + puntuacionActual);
        return null;
    }

    // Consulta de usuarios que han participado en un juego ordenado por puntuación (descendente)
    public Usuario consultarUsuariosPorJuego(Juego juego){
        List<Usuario> usuarios = new ArrayList<>();
        if (!usuarioHashMap.containsKey(juego)) {
            logger.warn("El juego no existe");
            return null;
        }
        List<Partida> partidas = new ArrayList<>();
        for (Partida partida : partidasList) {
            if (partida.getJuego().equals(juego)) {
                partidas.add(partida);
            }
        }
        // Ordenar las partidas por puntuación descendente utilizando el comparador
        //Collections.sort(partidas, (p1, p2) -> p2.getPuntosAcumulados().compareTo(p1.getPuntosAcumulados()));
        Collections.sort(partidas, new Comparator<Partida>() {
            @Override
            public int compare(Partida p1, Partida p2) {
                return Integer.compare(p2.getPuntosAcumulados(), p1.getPuntosAcumulados());
            }
        });
        return null;
    }

    //devuelve una lista de todas las partidas registradas en el sistema para un usuario dado
    @Override
    public List<Partida> partidaUsuario(String nomUsuario) {
        List<Partida> partidasUsuario = new ArrayList<>();
        //busca el objeto Usuario en la HashMap usando el nombre de usuario proporcionado como clave
        if (!usuarioHashMap.containsKey(nomUsuario)) {
            logger.warn("El usuario no existe");
            return null;
        }
        return usuarioHashMap.get(nomUsuario).getPartidas();
    }

    //proporcionar información sobre la actividad de un usuario en un juego en particular, incluyendo detalles
    // como el nivel actual, los puntos acumulados y la fecha de inicio de las partidas
    @Override
    public List<String> infoActividad(String nomUsuario, Juego juego) {
        List<String> infoActividad = new ArrayList<>();
        //Se verifica si el usuario y el juego existen en el sistema, y si es así, se recorren todas
        // las partidas del usuario en busca de las que corresponden al juego deseado
        if (!usuarioHashMap.containsKey(nomUsuario)) {
            logger.warn("El usuario no existe");
            return null;
        }
        if (!juegosList.contains(juego)) {
            logger.warn("El juego no existe");
            return null;
        }
        //Para cada partida encontrada, se crea una cadena de información en el
        // formato especificado y se agrega a la lista de información
        List<Partida> partidas = usuarioHashMap.get(nomUsuario).getPartidas();
        for (Partida partida : partidas) {
            if (partida.getJuego().equals(juego)) {
                String infoPartida = "{nivel: " + partida.getNivelActual() + ",puntos: " + partida.getPuntosAcumulados() + ",fecha: " + partida.getFechaInicio()+ "}";
                infoActividad.add(infoPartida);
            }
        }
        return infoActividad;
    }

    public void clear(){
        partidasList.clear();
        usuarioHashMap.clear();
        juegosList.clear();
    }
    public int sizeUsuarios(){
        int ret = this.usuarioHashMap.size();
        logger.info(ret + " usuario en la lista");
        return ret;
    }

    public int sizeJuegos(){
        int ret = this.juegosList.size();
        logger.info(ret + " juegos en la lista");
        return ret;
    }
}