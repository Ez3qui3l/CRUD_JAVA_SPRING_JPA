package com.springejercicio.universidad.universidadbackend.servicios.contratos;

import com.springejercicio.universidad.universidadbackend.modelo.entidades.Persona;

public interface AlumnoDAO extends PersonaDAO {

    Iterable<Persona> buscarAlumnosPorNombreCarrera(String nombre);

}
