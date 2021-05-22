package Repository;

import Domain.Car;

import java.util.ArrayList;

public class Repository<T> {

    private final ArrayList<T> entities;

    public Repository() {
        entities = new ArrayList<>();
    }

    public void add(T entity) throws UnsupportedOperationException {
        if (entities.contains(entity)) {
            throw new UnsupportedOperationException();
        } else {
            entities.add(entity);
        }
    }

    public void delete(T entity) throws UnsupportedOperationException {
        if (!entities.contains(entity)) {
            throw new UnsupportedOperationException();
        } else {
            entities.remove(entity);
        }
    }

    public void update(T entity) throws UnsupportedOperationException {
        if (!entities.contains(entity)) {
            throw new UnsupportedOperationException();
        } else {
            ((Car) entities.get(entities.indexOf(entity))).setLink(((Car) entity).getLink());
        }
    }

    public ArrayList<T> getEntities() {
        return this.entities;
    }
}
