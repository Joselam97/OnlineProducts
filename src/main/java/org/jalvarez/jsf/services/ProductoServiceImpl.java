package org.jalvarez.jsf.services;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.jalvarez.jsf.entities.Categoria;
import org.jalvarez.jsf.entities.Producto;
import org.jalvarez.jsf.repositories.CrudRepository;

import java.util.List;
import java.util.Optional;

@Stateless
public class ProductoServiceImpl implements ProductoService {

    @Inject
    private CrudRepository<Producto> repository;

    @Inject
    private CrudRepository<Categoria> categoriaRepository;

    @Override
    public List<Producto> listar() {
        return repository.listar();
    }

    @Override
    public Optional<Producto> porId(Long id) {
            return Optional.ofNullable(repository.porId(id));
    }

    @Override
    public void guardar(Producto producto) {
        repository.guardar(producto);
    }

    @Override
    public void eliminar(Long id) {
        repository.eliminar(id);
    }

    @Override
    public List<Categoria> listarCategorias() {
        return categoriaRepository.listar();
    }

    @Override
    public Optional<Categoria> porIdCategorias(Long id) {
        return Optional.ofNullable(categoriaRepository.porId(id));
    }
}
