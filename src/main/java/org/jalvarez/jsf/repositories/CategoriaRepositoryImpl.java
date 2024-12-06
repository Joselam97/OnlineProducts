package org.jalvarez.jsf.repositories;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.jalvarez.jsf.entities.Categoria;

import java.util.List;

// Specifies that this bean is request-scoped, meaning a new instance will be created for each HTTP request.
@RequestScoped
// Implements CRUD operations for the Categoria entity
public class CategoriaRepositoryImpl implements CrudRepository<Categoria> {

    // Injects an instance of EntityManager to interact with the database
    @Inject
    private EntityManager em;

    @Override
    // Retrieves all Categoria entities from the database
    public List<Categoria> listar() {
        // The JPQL query selects all records from the Categoria table
        return em.createQuery("SELECT c FROM Categoria c", Categoria.class).getResultList();
    }

    @Override
    public Categoria porId(Long id) {
        // Finds a Categoria entity by its primary key (ID)
        return em.find(Categoria.class, id);
    }

    @Override
    // Saves or updates a Categoria entity in the database
    public void guardar(Categoria categoria) {
        // If the Categoria has an ID, it already exists, so update it using `merge`
        if (categoria.getId() != null && categoria.getId() > 0){
            em.merge(categoria);
        } else {
            // If the Categoria has no ID, it is new, so save it using `persist`
            em.persist(categoria);
        }
    }

    @Override
    public void eliminar(Long id) {
        // Deletes a Categoria entity by its ID
        em.remove(porId(id));
        // First, fetches the Categoria entity using `porId` and then removes it
    }
}
