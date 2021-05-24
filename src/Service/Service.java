package Service;

import Domain.Car;
import Domain.Validator;
import Repository.RepositoryFile;

import java.io.IOException;
import java.util.ArrayList;

public class Service<T> {

    protected RepositoryFile<T> repositoryFile;

    public Service(RepositoryFile<T> repositoryFile) {
        this.repositoryFile = repositoryFile;
    }

    public void add(T entity) throws IllegalArgumentException, UnsupportedOperationException, IOException {
        Validator.validate((Car) entity);
        repositoryFile.add(entity);
    }

    public void delete(T entity) throws IllegalArgumentException, UnsupportedOperationException, IOException {
        Validator.validate((Car) entity);
        repositoryFile.delete(entity);
    }

    public void update(T entity) throws IllegalArgumentException, UnsupportedOperationException, IOException {
        Validator.validate((Car) entity);
        repositoryFile.update(entity);
    }

    public ArrayList<T> getAll() {
        return repositoryFile.getEntities();
    }

    public boolean areElements() {
        return getAll().size() != 0;
    }
}
