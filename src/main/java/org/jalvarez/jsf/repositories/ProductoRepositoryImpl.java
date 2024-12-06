package org.jalvarez.jsf.repositories;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.jalvarez.jsf.entities.Producto;

import java.util.List;

// This bean is instantiated once per HTTP request.
@RequestScoped
public class ProductoRepositoryImpl implements ProductoRepository {

    @Inject
    // EntityManager for interacting with the database
    private EntityManager em;

    @Override
    public List<Producto> listar() {
        // Fetches all products with their associated categories using a LEFT OUTER JOIN
        return em.createQuery("SELECT p FROM Producto p LEFT OUTER JOIN FETCH p.categoria", Producto.class).getResultList();
    }

    @Override
    public Producto porId(Long id) {
        // Retrieves a product by ID, including its category, using a JPQL query
        return em.createQuery("SELECT p FROM Producto p LEFT OUTER JOIN FETCH p.categoria WHERE p.id=:id", Producto.class)
                .setParameter("id", id) // Sets the ID parameter for the query
                .getSingleResult();
    }

    @Override
    public void guardar(Producto producto) {
        // Persists a new product or updates an existing one based on its ID
        if (producto.getId() != null && producto.getId() > 0) {
            // Updates the product if it already exists
            em.merge(producto);
        } else {
            em.persist(producto); // Saves the product if it is new
        }
    }

    @Override
    // Deletes a product by ID after fetching it from the database
    public void eliminar(Long id) {
        Producto producto = porId(id); // Retrieves the product to ensure it exists
        em.remove(producto); // Deletes the product from the database
    }

    @Override
    public List<Producto> buscarPorNombre(String nombre) {
        // Searches for products by name using a LIKE query, including their categories
        return em.createQuery("SELECT p FROM Producto p LEFT OUTER JOIN FETCH p.categoria WHERE p.nombre LIKE :nombre", Producto.class)
                .setParameter("nombre", "%" + nombre + "%") // Adds wildcards for coincidences
                .getResultList();
    }
}
