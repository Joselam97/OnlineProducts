package org.jalvarez.jsf.repositories;

import java.util.List;

/* Generic interface for CRUD (Create, Read, Update, Delete) operations
 <T> The type of entity this repository will manage */
public interface CrudRepository<T> {

    /* Lists all entities of type T
     a list containing all entities of type T */
    List<T> listar();

    // Finds an entity of type T by its ID
    T porId(Long id);

    /* Saves or updates an entity of type T
     t The entity to be saved or updated */
    void guardar(T t);


    // Deletes an entity of type T by its ID.
    void eliminar(Long id);
}
