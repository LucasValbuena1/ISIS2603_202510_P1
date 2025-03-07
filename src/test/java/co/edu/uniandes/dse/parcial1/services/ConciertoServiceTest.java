package co.edu.uniandes.dse.parcial1.services;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import co.edu.uniandes.dse.parcial1.entities.ConciertoEntity;
import co.edu.uniandes.dse.parcial1.entities.EstadioEntity;
import co.edu.uniandes.dse.parcial1.exceptions.IllegalOperationException;
import jakarta.transaction.Transactional;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@DataJpaTest
@Transactional
@Import(ConciertoService.class)
public class ConciertoServiceTest {

    @Autowired
    private ConciertoService conciertoService;

    @Autowired
    private TestEntityManager entityManager;

    private PodamFactory factory = new PodamFactoryImpl();

    private ConciertoEntity conciertoEntity;

    @BeforeEach
    void setUp() {
        clearData();
        insertData();
    }

    private void clearData() {
        entityManager.getEntityManager().createQuery("delete from ConciertoEntity");

    }

    private void insertData() {

        conciertoEntity = factory.manufacturePojo(ConciertoEntity.class);
        conciertoEntity.setNombre("A");
        entityManager.persist(conciertoEntity);

    }

     @Test
    void testCrearConcierto(){
         assertThrows(IllegalOperationException.class, () -> {
			ConciertoEntity newEntity = factory.manufacturePojo(ConciertoEntity.class);
        
            newEntity.setCapacidad(1);
            conciertoService.crearConcierto(newEntity); });

    }


    @Test
    void testCrearConciertoNoFallido() throws IllegalOperationException{
      
			ConciertoEntity newEntity = factory.manufacturePojo(ConciertoEntity.class);
            newEntity.setFecha(LocalDateTime.now());
            newEntity.setCapacidad(1000000);
            conciertoService.crearConcierto(newEntity); 

    }


}
