package org.jalvarez.jsf.services;

import jakarta.ejb.Local;
import org.jalvarez.jsf.entities.Producto;

import java.util.List;
import java.util.Optional;

@Local
public interface ProductoService {

    List<Producto> listar();
    Optional<Producto> porId(Long id);

    void guardar(Producto producto);
    void eliminar(Long id);
}
