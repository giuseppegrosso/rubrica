package it.plansoft.rubrica.service;

import java.util.List;
import java.util.Optional;

public interface IServiceCrud<T, ID> {

  /**
   * recupero tutti gli oggetti
   * 
   * @return
   */
  public List<T> getAll();

  /**
   * recupro un singolo oggetto
   * 
   * @return
   */
  public Optional<T> getById(ID id);

  /**
   * salvataggio entity
   * 
   * @param entity
   * @return
   */
  public T save(T entity);

  /**
   * aggiornamento entity
   * 
   * @param entity
   * @return
   */
  public T update(T entity);

  /**
   * cancellazione entity
   * 
   * @param entity
   */
  public void delete(T entity);

  /**
   * cancellazione entity
   * 
   * @param entity
   */
  public void deleteById(ID id);
}