package it.plansoft.rubrica.controller;

import java.util.List;
import java.util.Optional;


/**
 * gestione comune dei controller per catalogo standard
 */
public interface ICrudController<MODEL, ID> extends IController<MODEL> {


    /**
     * retrives all item (not paginated)
     *
     * @return
     */
    public List<MODEL> getAllItems();

    /**
     * return element by the id
     *
     * @param id
     * @return
     */
    public Optional<MODEL> getById(ID id);


    /**
     * Add single item
     *
     * @param model
     * @return
     */
    public MODEL add(MODEL model);

    /**
     * update single items
     *
     * @param model
     * @param id
     * @return
     */
    public MODEL update(MODEL model, ID id);

    /**
     * delete single items
     *
     * @param id
     */
    public void deleteById(ID id);

    /**
     * delete single items
     *
     * @param model
     */
    public void deleteItem(MODEL model);

}
