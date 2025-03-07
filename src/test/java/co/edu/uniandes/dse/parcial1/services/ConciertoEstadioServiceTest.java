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
@Import(ConciertoEstadioService.class)
public class ConciertoEstadioServiceTest {
    


    @Autowired
    private ConciertoEstadioService conciertoEstadioService;

    @Autowired
    private TestEntityManager entityManager;

    private PodamFactory factory = new PodamFactoryImpl();

    private ConciertoEntity conciertoEntity;
    private EstadioEntity estadioEntity;


     @BeforeEach
    void setUp() {
        clearData();
        insertData();
    }

    private void clearData() {
        entityManager.getEntityManager().createQuery("delete from EstadioEntity");
        entityManager.getEntityManager().createQuery("delete from EstadioEntity");

    }

    private void insertData() {

        conciertoEntity = factory.manufacturePojo(ConciertoEntity.class);
        estadioEntity = factory.manufacturePojo(EstadioEntity.class);
        conciertoEntity.setNombre("A");
        entityManager.persist(conciertoEntity);
        entityManager.persist(estadioEntity);

    }


    @Test
    void crearConcierto()
        {
            assertThrows(IllegalOperationException.class, () -> {
            EstadioEntity newEntity = factory.manufacturePojo(EstadioEntity.class);
           
            ConciertoEntity newEntity2 = factory.manufacturePojo(ConciertoEntity.class);

            newEntity.setCapacidadMaxima(1);
            newEntity2.setCapacidad(2000000);});
            conciertoEstadioService.crearEstadio(newEntity,newEntity2); 

        }
    
}
