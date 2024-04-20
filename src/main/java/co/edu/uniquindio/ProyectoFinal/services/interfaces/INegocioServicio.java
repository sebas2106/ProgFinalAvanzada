package co.edu.uniquindio.ProyectoFinal.services.interfaces;

import co.edu.uniquindio.ProyectoFinal.DTO.*;
import co.edu.uniquindio.ProyectoFinal.model.Negocio;
import co.edu.uniquindio.ProyectoFinal.model.enums.EstadoRegistro;
import co.edu.uniquindio.ProyectoFinal.model.enums.TipoNegocio;

import java.util.List;

public interface INegocioServicio {
    // un interface por cada documento
    String crearNegocio(CrearLugarDTO cL)throws Exception;
    String actualizarNegocio(ActualizarLugarDTO aL)throws Exception;
    String eliminarNegocio(EliminarLugarDTO eL)throws Exception;
    Negocio obtenerNegocio(String id)throws Exception;
    List<Negocio> buscarNegociosNomb(String nombre)throws Exception;
    List<Negocio> filtrarPorEstado(EstadoRegistro estado)throws Exception;
    List<Negocio> filtrarPorTipoNegocio(TipoNegocio tipoNegocio)throws Exception;
    List<Negocio> listarNegociosPropietario(ListarLugaresPropietarioDTO listar)throws Exception;
    void solicitarRuta(SolicitarRutaDTO s)throws Exception;





}
