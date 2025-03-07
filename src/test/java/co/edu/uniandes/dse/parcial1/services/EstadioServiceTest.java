package co.edu.uniandes.dse.parcial1.services;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import co.edu.uniandes.dse.parcial1.entities.EstadioEntity;
import co.edu.uniandes.dse.parcial1.exceptions.IllegalOperationException;
import jakarta.transaction.Transactional;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@DataJpaTest
@Transactional
@Import(EstadioService.class)
public class EstadioServiceTest {
    

    @Autowired
    private EstadioService estadioService;

    @Autowired
	private TestEntityManager entityManager;

	private PodamFactory factory = new PodamFactoryImpl();

    private EstadioEntity estadioEntity;

    @BeforeEach
	void setUp() {
		clearData();
		insertData();
	}

    private void clearData() {
		entityManager.getEntityManager().createQuery("delete from EstadioEntity");
		
	}

    private void insertData() {
		
		estadioEntity = factory.manufacturePojo(EstadioEntity.class);
        estadioEntity.setNombre("A");
		entityManager.persist(estadioEntity);
		
	}

    @Test
    void testCrearEstadio() throws IllegalOperationException{

        assertThrows(IllegalOperationException.class, () -> {
			EstadioEntity newEntity = factory.manufacturePojo(EstadioEntity.class);
        
            newEntity.setNombre("a");
            estadioService.crearEstadio(newEntity); });

    }

    @Test
    void testCrearEstadioNoFallido() throws IllegalOperationException {

        
			EstadioEntity newEntity = factory.manufacturePojo(EstadioEntity.class);
        
            newEntity.setNombre("aeee");
            estadioService.crearEstadio(newEntity); 

    }




}
