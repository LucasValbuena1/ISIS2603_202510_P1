package co.edu.uniandes.dse.parcial1.services;

import java.time.Duration;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.parcial1.entities.ConciertoEntity;
import co.edu.uniandes.dse.parcial1.entities.EstadioEntity;
import co.edu.uniandes.dse.parcial1.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcial1.repositories.ConciertoRepository;
import co.edu.uniandes.dse.parcial1.repositories.EstadioRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ConciertoEstadioService {

    @Autowired
    EstadioRepository estadioRepository;

    @Autowired
    ConciertoRepository conciertoRepository;

    @Transactional
    public ConciertoEntity añadirConcierto(Long idConcierto, long idEstadio) throws IllegalOperationException{
        Optional<ConciertoEntity> conciertoEntity = conciertoRepository.findById(idConcierto);
        Optional<EstadioEntity> estadioEntity = estadioRepository.findById(idEstadio);

        if(conciertoEntity.get().getCapacidad()>estadioEntity.get().getCapacidadMaxima()){
            throw new IllegalOperationException("capacidad invalida");

        }

        if(conciertoEntity.get().getPresupuesto()<estadioEntity.get().getPrecioAlquiler()){
            throw new IllegalOperationException("no podemos costearlo");

        }
        
        if(Duration.between(estadioEntity.get().getConciertos().getFirst().getFecha(), estadioEntity.get().getConciertos().getLast().getFecha()).toDays()<2){
            throw new IllegalOperationException("fecha invalida");

        }
        return conciertoEntity.get();
    }

}
