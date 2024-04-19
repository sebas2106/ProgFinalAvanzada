package co.edu.uniquindio.ProyectoFinal.test;


import co.edu.uniquindio.ProyectoFinal.Repositories.NegocioRepo;
import co.edu.uniquindio.ProyectoFinal.model.*;
import co.edu.uniquindio.ProyectoFinal.model.enums.EstadoActual;
import co.edu.uniquindio.ProyectoFinal.model.enums.EstadoRegistro;
import co.edu.uniquindio.ProyectoFinal.model.enums.EstadoRevision;
import co.edu.uniquindio.ProyectoFinal.model.enums.TipoNegocio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class NegocioTest {
    @Autowired
    private NegocioRepo negocioRepo;

    @Test
    public void registrarNegocioTest() {

        Departamento nuevoDep = new Departamento();
        nuevoDep.setCodDepartamento("1");
        nuevoDep.setNombre("Quindio");
        Ciudad ciudad = new Ciudad();
        ciudad.setCodDepartamento("1");
        ciudad.setCodCiudad("1");
        ciudad.setNombre("Armenia");


        Ubicacion nueva = new Ubicacion();
        nueva.setCodDepartamento(nuevoDep.getCodDepartamento());
        nueva.setCodCiudad(ciudad.getCodCiudad());
        Direccion direccion = new Direccion();
        direccion.setBarrio("La milagrosa");
        direccion.setDireccion("M6#3");
        direccion.setCodigoPostal("630001");
        nueva.setDirecion(direccion);

        List<Horario> listHorario = new ArrayList<>();

        Horario nuevo = new Horario();
        nuevo.setDia("Lunes");
        LocalTime horaInicial = LocalTime.of(7, 30, 45);
        LocalTime horaFinal = LocalTime.of(19, 30, 45);
        nuevo.setHoraInicial(horaInicial);
        nuevo.setHoraFinal(horaFinal);
        listHorario.add(nuevo);
        nuevo.setDia("Martes");
        horaInicial = LocalTime.of(8, 30, 45);
        horaFinal = LocalTime.of(20, 30, 45);
        nuevo.setHoraInicial(horaInicial);
        nuevo.setHoraFinal(horaFinal);
        listHorario.add(nuevo);

        List<String> listImages = new ArrayList<>();
        listImages.add("ruta1");
        listImages.add("ruta3");
        listImages.add("ruta2");

        //Creamos el negocio con sus propiedades
        Negocio negocio = Negocio.builder()
                .codCreador("1005094005")
                .nombre("El viñedo-1")
                .descripcion("Hermoso paraiso de lanaturaleza y las delicias")
                .ubicacion(nueva)
                .tipoNegocio(TipoNegocio.CAFETERIA)
                .listHorario(listHorario)
                .estado(EstadoActual.CERRADO)
                .estadoReg(EstadoRegistro.INACTIVO)
                .estadoRevision(EstadoRevision.PENDIENTE)
                .listImagenes(listImages)
                .listTelefonos(List.of("12121", "232323")).build();
        //Guardamos el negocio
        Negocio registro = negocioRepo.save(negocio);
        //Verificamos que se haya guardado validando que no sea null
        Assertions.assertNotNull(registro);
    }

    @Test
    public void actualizarDatosBasicosNegocioTest() {
        //Obtenemos el negocio con el id XXXXXXX
        Negocio negocio = negocioRepo.findById("66212934d7082e2a6b8c2163").orElseThrow();
        //Modificar estos datos
        negocio.setNombre("ElViñedo-Nuevo");
        negocio.setDescripcion("Esta descripción se actualizó");
        negocio.setTipoNegocio(TipoNegocio.RESTAURANTE);
        //Guardamos el cliente
        negocioRepo.save(negocio);
        //Obtenemos el Negocio con el id XXXXXXX nuevamente
        Negocio negocioActualizado = negocioRepo.findById("66212934d7082e2a6b8c2163").orElseThrow();;
        //Verificamos que el nombre se haya actualizado
        Assertions.assertEquals("ElViñedo-Nuevo", negocioActualizado.getNombre());
    }

    @Test
    public void eliminarNegocioTest() {
        //Borramos el Negocio con el id XXXXXXX
        negocioRepo.deleteById("662136eb85ef7534d1b00943");
        //Obtenemos el Negocio con el id XXXXXXX
        Negocio negocio = negocioRepo.findById("662136eb85ef7534d1b00943").orElse(null);
        //Verificamos que el Negocio no exista
        Assertions.assertNull(negocio);
    }

    @Test
    public void verEstados() {
        Negocio negocio = negocioRepo.findById("662136eb85ef7534d1b00943").orElseThrow();

        Assertions.assertEquals(EstadoActual.CERRADO, negocio.getEstado());
    }


    @Test
    public void listarNegocios_Nombre_Test(){
        String nombre="El viñedo-1";
        //Obtenemos LOS NEGOCIOS CON EL TIPO ASIGNADO
        Optional<List<Negocio>> negocios = negocioRepo.findByNombre(nombre);
        //lo ponemos en una lista
        List<Negocio>j=negocios.get();
        //Verificamos que si exista solo uno por que solo agregamos 1 negocios por el momento
        Assertions.assertEquals(1, j.size());
    }

    @Test
    public void listarNegocios_TipoNegocios_Test(){
        TipoNegocio tipo=TipoNegocio.CAFETERIA;
        //Obtenemos LOS NEGOCIOS CON EL TIPO ASIGNADO
        Optional<List<Negocio>> negocios = negocioRepo.findByTipoNegocio(tipo);
        //lo ponemos en una lista
        List<Negocio>j=negocios.get();
        //Verificamos que si exista solo uno por que solo agregamos 1 negocios por el momento
        Assertions.assertEquals(1, j.size());
    }
}
