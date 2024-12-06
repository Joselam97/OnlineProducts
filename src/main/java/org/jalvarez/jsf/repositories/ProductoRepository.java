package org.jalvarez.jsf.repositories;

import org.jalvarez.jsf.entities.Producto;

import java.util.List;

// Extends CrudRepository to add additional database operations specific to Producto
public interface ProductoRepository extends CrudRepository<Producto> {

    // Searches for products in the list whose names match the given string
    List<Producto> buscarPorNombre(String nombre);
}
