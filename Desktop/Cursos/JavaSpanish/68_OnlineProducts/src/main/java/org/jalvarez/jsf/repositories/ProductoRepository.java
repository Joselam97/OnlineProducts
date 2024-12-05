package org.jalvarez.jsf.repositories;

import org.jalvarez.jsf.entities.Producto;

import java.util.List;

public interface ProductoRepository extends CrudRepository<Producto> {

    List<Producto> buscarPorNombre(String nombre);
}
