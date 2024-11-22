package org.jalvarez.jsf.repositories;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.jalvarez.jsf.entities.Producto;

import java.util.List;

@RequestScoped
public class ProductoRepositoryImpl implements CrudRepository<Producto> {

    @Inject
    private EntityManager em;

    @Override
    public List<Producto> listar() {
        return em.createQuery("SELECT p FROM Producto p", Producto.class).getResultList();
    }

    @Override
    public Producto porId(Long id) {
        return em.find(Producto.class, id);
    }
}
