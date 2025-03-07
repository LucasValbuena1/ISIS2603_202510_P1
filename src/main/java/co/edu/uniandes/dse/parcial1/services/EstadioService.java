package co.edu.uniandes.dse.parcial1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.parcial1.entities.EstadioEntity;
import co.edu.uniandes.dse.parcial1.exceptions.IllegalOperationException;

import co.edu.uniandes.dse.parcial1.repositories.EstadioRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EstadioService {

    @Autowired
    EstadioRepository estadioRepository;

    @Transactional
    public EstadioEntity crearEstadio(EstadioEntity estadio) throws IllegalOperationException{

        if(estadio.getNombre().length()<3){
             throw new IllegalOperationException("nombre invalido");
        }
        if(estadio.getCapacidadMaxima()<=10){
            throw new IllegalOperationException("capacidad invalida");
        }
        if(estadio.getPrecioAlquiler()<=100000 ){
            throw new IllegalOperationException("precio invalido");
        }

        return estadioRepository.save(estadio);
    }

}
