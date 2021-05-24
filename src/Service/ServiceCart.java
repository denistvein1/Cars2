package Service;

import Repository.RepositoryFile;

import java.io.IOException;
import java.util.ArrayList;

public class ServiceCart<T> extends Service<T> {

    private final RepositoryFile<T> cart;

    public ServiceCart(RepositoryFile<T> repositoryFile, RepositoryFile<T> cart) {
        super(repositoryFile);
        this.cart = cart;
    }

    @Override
    public void add(T entity) throws UnsupportedOperationException, IOException {
        cart.add(entity);
        super.repositoryFile.delete(entity);
    }

    @Override
    public ArrayList<T> getAll() {
        return cart.getEntities();
    }

    public void clear() throws IOException {
        cart.clear();
    }

    public void open() throws IOException {
        cart.open();
    }
}
