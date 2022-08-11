package com.springejercicio.universidad.universidadbackend.servicios.contratos;

import java.util.Optional;

import com.springejercicio.universidad.universidadbackend.modelo.entidades.Persona;

public interface PersonaDAO extends GenericoDAO<Persona> {

    Optional<Persona> buscarPorNombreYApellido(String nombre, String apellido);

    Optional<Persona> buscarPorDni(String dni);

    Iterable<Persona> buscarPersonaPorApellido(String apellido);

}
