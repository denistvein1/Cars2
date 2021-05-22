package Service;

import Domain.Car;
import Domain.Validator;
import Repository.Repository;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Service<T> {

    protected Repository<T> repository;

    public Service(Repository<T> repository) {
        this.repository = repository;
    }

    public void add(T entity) throws IllegalArgumentException, UnsupportedOperationException {
        Validator.validate((Car) entity);
        repository.add(entity);
    }

    /* Deletes "entity" from the repository if it exists and
     * is valid
     * @param entity an object of type T
     * @throws IllegalArgumentException if the attributes
     * of the "entity" are invalid
     * @throws UnsupportedOperationException if the "entity" doesn't
     * exist in the repository
     * @return - */
    public void delete(T entity) throws IllegalArgumentException, UnsupportedOperationException {
        Validator.validate((Car) entity);
        repository.delete(entity);
    }

    public void update(T entity) throws IllegalArgumentException, UnsupportedOperationException {
        Validator.validate((Car) entity);
        repository.update(entity);
    }

    public ArrayList<T> getAll() {
        return repository.getEntities();
    }

    public boolean areElements() {
        return getAll().size() != 0;
    }

    public T getNext(Iterator<T> it) {
        if (!areElements()) {
            throw new UnsupportedOperationException();
        }
        try {
            return it.next();
        } catch (NoSuchElementException | ConcurrentModificationException e) {
            it = getAll().iterator();
            return it.next();
        }
    }
}
