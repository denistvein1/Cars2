/*
package Service;

import Domain.Car;
import Repository.Repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {

    private Service<Car> service;

    @BeforeEach
    void setUp() {
        Repository<Car> repository = new Repository<Car>();
        service = new Service(repository);
        service.initialize();
    }

    @Test
    void delete() {
        Car entity1 = new Car("Porsche", "911 Turbo S", 2021);
        Car entity2 = new Car("asfsaff", "adgdasg", 2000);
        Car entity3 = new Car("asfsaff", "adgdasg", 3000);
        service.delete(entity1);
        assertEquals(9, service.getAll().size());
        try{
            service.delete(entity2);
            fail("Should have thrown UnsupportedOperationException");
        }catch (UnsupportedOperationException e){
            assertTrue(true);
        }
        try{
            service.delete(entity3);
            fail("Should have thrown IllegalArgumentException");
        }catch (IllegalArgumentException e){
            assertTrue(true);
        }
    }

    @Test
    void getAll() {
        ArrayList<Car> all = service.getAll();
        assertEquals(10, all.size());
    }

    @Test
    void initialize() {
        assertEquals(10, service.getAll().size());
    }
}*/
