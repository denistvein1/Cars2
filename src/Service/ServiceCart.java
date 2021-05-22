package Service;

import Repository.Repository;

import java.util.ArrayList;

public class ServiceCart<T> extends Service<T> {

    private final Repository<T> cart;

    public ServiceCart(Repository<T> repository) {
        super(repository);
        cart = new Repository<T>();
    }

    @Override
    public void add(T entity) throws UnsupportedOperationException {
        cart.add(entity);
        super.repository.delete(entity);
    }

    @Override
    public ArrayList<T> getAll() {
        return cart.getEntities();
    }
}
