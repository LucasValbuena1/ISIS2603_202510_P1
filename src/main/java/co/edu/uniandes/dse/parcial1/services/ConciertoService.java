package co.edu.uniandes.dse.parcial1.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.parcial1.entities.ConciertoEntity;
import co.edu.uniandes.dse.parcial1.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcial1.repositories.ConciertoRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ConciertoService {

    @Autowired
    ConciertoRepository conciertoRepository;

    @Transactional
    public ConciertoEntity crearConcierto(ConciertoEntity concierto) throws IllegalOperationException{

        if(concierto.getFecha().isBefore(LocalDateTime.now())){
            throw new IllegalOperationException("fecha invalida");
        }
        if(concierto.getCapacidad()<=10){
            throw new IllegalOperationException("capacidad invalida");
        }
        if(concierto.getPresupuesto()<=1000){
            throw new IllegalOperationException("presupuesto invalido");
        }
        
        
        return conciertoRepository.save(concierto);
        
    }

}
