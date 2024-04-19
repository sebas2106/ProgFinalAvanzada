package co.edu.uniquindio.ProyectoFinal.services.interfaces;

import co.edu.uniquindio.ProyectoFinal.DTO.*;
import co.edu.uniquindio.ProyectoFinal.model.Negocio;
import co.edu.uniquindio.ProyectoFinal.model.enums.EstadoRegistro;

import java.util.List;

public interface INegocioServicio {
    // un interface por cada documento
    String crearNegocio(CrearLugarDTO cL)throws Exception;
    String actualizarNegocio(ActualizarLugarDTO aL)throws Exception;
    String eliminarNegocio(EliminarLugarDTO eL)throws Exception;
    Negocio obtenerNegocio(ObtenerNegocioDTO oN)throws Exception;
    void listarNegociosUsuario(String idUsuario)throws Exception;
    List<Negocio> buscarNegociosNomb(BuscarLugarNomTipDistDTO bL)throws Exception;
    void filtrarPorEstado(EstadoRegistro estado)throws Exception;

    void listarNegociosPropietario(ListarLugaresPropietarioDTO listar)throws Exception;
    void solicitarRuta(SolicitarRutaDTO s)throws Exception;





}
