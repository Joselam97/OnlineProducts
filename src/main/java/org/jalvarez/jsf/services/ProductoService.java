package org.jalvarez.jsf.services;

import jakarta.ejb.Local;
import org.jalvarez.jsf.entities.Categoria;
import org.jalvarez.jsf.entities.Producto;

import java.util.List;
import java.util.Optional;

// Marks this interface as a local EJB, making it available only within the same application context
@Local
public interface ProductoService {

    // Returns a list of all products stored in the database
    List<Producto> listar();

    // Retrieves a product by its ID, wrapped in Optional to handle null values
    Optional<Producto> porId(Long id);

    // Saves a new product or updates an existing one based on its ID
    void guardar(Producto producto);

    // Deletes a product by its ID after checking if it exists
    void eliminar(Long id);

    // Retrieves a list of all categories available in the database
    List<Categoria> listarCategorias();

    // Finds a category by its ID, wrapped in Optional to handle null values
    Optional<Categoria> porIdCategoria(Long id);

    // Searches for products by name or partial match and returns the results
    List<Producto> buscarPorNombre(String nombre);
}
