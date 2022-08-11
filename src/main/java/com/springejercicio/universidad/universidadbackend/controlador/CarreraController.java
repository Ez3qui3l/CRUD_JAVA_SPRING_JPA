package com.springejercicio.universidad.universidadbackend.controlador;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springejercicio.universidad.universidadbackend.exception.BadRequestException;
import com.springejercicio.universidad.universidadbackend.modelo.entidades.Carrera;
import com.springejercicio.universidad.universidadbackend.servicios.contratos.CarreraDAO;

@RestController
@RequestMapping("/Carreras")
public class CarreraController {
    private final CarreraDAO carreraDAO;

    @Autowired
    public CarreraController(CarreraDAO carreraDAO) {
        this.carreraDAO = carreraDAO;
    }

    @GetMapping
    public List<Carrera> obtenerTodos() {
        List<Carrera> carreras = (List<Carrera>) carreraDAO.findAll();
        if (carreras.isEmpty()) {
            throw new BadRequestException("No existen carreras");
        }
        return carreras;
    }

    @GetMapping("/{codigo}")
    public Carrera obtenerPorId(@PathVariable(value = "codigo", required = false) Integer id) {
        Optional<Carrera> oCarrera = carreraDAO.findById(id);
        if (!oCarrera.isPresent()) {
            throw new BadRequestException(String.format("la carrera id no existe", id));
        }
        return oCarrera.get();
    }

    @PostMapping
    public Carrera altaCarrera(@RequestBody Carrera carrera) {
        if (carrera.getCantidadAnios() < 0) {
            throw new BadRequestException("el campo cantidad de anios no puede ser negativo");
        }
        if (carrera.getCantidadMaterias() < 0) {
            throw new BadRequestException("el campo cantidad de materias no puede ser negativo");
        }
        return carreraDAO.save(carrera);
    }

    @PutMapping("/{id}")
    public Carrera actulizarCarrera(@PathVariable Integer id, @RequestBody Carrera carrera) {
        Carrera carreraUpdate = null;
        Optional<Carrera> oCarrera = carreraDAO.findById(id);
        if (!oCarrera.isPresent()) {
            throw new BadRequestException(String.format("la carrera id no existe", id));

        }
        carreraUpdate = oCarrera.get();
        carreraUpdate.setCantidadAnios((carrera.getCantidadAnios()));
        carreraUpdate.setCantidadMaterias(carrera.getCantidadMaterias());
        return carreraDAO.save(carreraUpdate);
    }

    @DeleteMapping("/{id}")
    public void eliminarCarrera(@PathVariable Integer id) {
        carreraDAO.deleteById(id);
    }
}
