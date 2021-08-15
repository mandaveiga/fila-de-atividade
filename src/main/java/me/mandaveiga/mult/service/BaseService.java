package me.mandaveiga.mult.service;

import me.mandaveiga.mult.model.AbstractModel;
import me.mandaveiga.mult.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public abstract class BaseService<T extends AbstractModel> implements CrudService<T> {

    protected BaseRepository<T> repository;

    @Autowired
    public BaseService(BaseRepository<T> repository) {
        this.repository = repository;
    }

    @Override
    public Optional<T> save(T body) {
        T entity = repository.save(body);

        return Optional.of(entity);
    }

    @Override
    public List<T> findAll() {
        return (List<T>) repository.findAll();
    }

    @Override
    public Optional<T> findById(Long id) {
        return repository.findById(id);
    }

	@Override
    public Optional<T> update(T body) {
    	return save(body);
    }

    @Override
    public Optional<Boolean> deleteById(Long id) {
        try {
            repository.deleteById(id);

            return Optional.of(true);
        } catch(Exception ignore) {
            return Optional.of(false);
        }
    }

    @Override
    public Optional<Boolean> deleteAll() {
        try {
            repository.deleteAll();

            return Optional.of(true);
        } catch(Exception ignore) {
            return Optional.of(false);
        }
    }
}
