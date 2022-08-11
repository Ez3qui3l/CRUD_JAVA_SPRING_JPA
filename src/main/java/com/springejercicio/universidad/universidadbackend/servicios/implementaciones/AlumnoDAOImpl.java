package com.springejercicio.universidad.universidadbackend.servicios.implementaciones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springejercicio.universidad.universidadbackend.modelo.entidades.Persona;
import com.springejercicio.universidad.universidadbackend.repositorios.AlumnoRepository;
import com.springejercicio.universidad.universidadbackend.repositorios.PersonaRepository;
import com.springejercicio.universidad.universidadbackend.servicios.contratos.AlumnoDAO;

@Service
public class AlumnoDAOImpl extends PersonaDAOImpl implements AlumnoDAO {

    @Autowired
    public AlumnoDAOImpl(@Qualifier("repositorioAlumnos") PersonaRepository repository) {
        super(repository);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Persona> buscarAlumnosPorNombreCarrera(String nombre) {
        return ((AlumnoRepository) repository).buscarAlumnosPorNombreCarrera(nombre);
    }
}
