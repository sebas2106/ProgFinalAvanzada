package co.edu.uniquindio.proyecto.servicios.interfaces;

import co.edu.uniquindio.proyecto.modelo.documentos.Negocio;

public interface NegocioServicio {

    void crearNegocio();

    void actualizarNegocio();

    void eliminarNegocio(String idNegocio);

    void buscarNegocios();

    void filtrarPorEstado();

    void listarNegociosPropietario();

    void cambiarEstado();

    void registrarRevision();

}
