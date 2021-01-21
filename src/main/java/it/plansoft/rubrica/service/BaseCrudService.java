package it.plansoft.rubrica.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * classe di implementazione delle operazioni di base.
 */
public class BaseCrudService<REPO extends JpaRepository<T, ID>, T, ID> implements IServiceCrud<T, ID> {

  private JpaRepository<T, ID> repo;

  private final static Logger log = LoggerFactory.getLogger(BaseCrudService.class);

  public BaseCrudService(JpaRepository<T, ID> repo) {
    this.repo = repo;
  }

  @Override
  public List<T> getAll() {
    List<T> t = repo.findAll();
    log.info("fetch data {} ", t);

    return t;
  }

  @Override
  public Optional<T> getById(ID id) {
    Optional<T> t = repo.findById(id);
    log.info("fetch data {} ", t);

    return t;
  }

  @Override
  public T save(T entity) {
    return repo.save(entity);
  }

  @Override
  public T update(T entity) {
    return repo.save(entity);
  }

  @Override
  public void delete(T entity) {
    repo.delete(entity);
  }

  @Override
  public void deleteById(ID id) {
    repo.deleteById(id);
  }
}