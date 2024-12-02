package org.jalvarez.jsf.services;

import jakarta.annotation.Resource;
import jakarta.annotation.security.DeclareRoles;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.SessionContext;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.jalvarez.jsf.entities.Categoria;
import org.jalvarez.jsf.entities.Producto;
import org.jalvarez.jsf.repositories.CrudRepository;
import org.jalvarez.jsf.repositories.ProductoRepository;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Stateless
@DeclareRoles({"USER", "ADMIN"})
public class ProductoServiceImpl implements ProductoService {

    @Inject
    private ProductoRepository repository;

    @Inject
    private CrudRepository<Categoria> categoriaRepository;

    @Resource
    private SessionContext ctx;

    @Override
    @PermitAll
    public List<Producto> listar() {
        Principal usuario = ctx.getCallerPrincipal();
        String username = usuario.getName();
        System.out.println("username: " + username);
        if(ctx.isCallerInRole("ADMIN")) {
            System.out.println("Soy un ADMIN");
        } else if (ctx.isCallerInRole("USER")) {
            System.out.println("Soy un USER");
        } else {
            System.out.println("Soy ANONYMOUS");
        }
        return repository.listar();
    }

    @Override
    @RolesAllowed({"USER", "ADMIN"})
    public Optional<Producto> porId(Long id) {
            return Optional.ofNullable(repository.porId(id));
    }

    @Override
    @RolesAllowed("ADMIN")
    public void guardar(Producto producto) {
        repository.guardar(producto);
    }

    @Override
    @RolesAllowed("ADMIN")
    public void eliminar(Long id) {
        repository.eliminar(id);
    }

    @Override
    @RolesAllowed({"USER", "ADMIN"})
    public List<Categoria> listarCategorias() {
        return categoriaRepository.listar();
    }

    @Override
    @RolesAllowed({"USER", "ADMIN"})
    public Optional<Categoria> porIdCategoria(Long id) {
        return Optional.ofNullable(categoriaRepository.porId(id));
    }

    @Override
    @RolesAllowed({"USER", "ADMIN"})
    public List<Producto> buscarPorNombre(String nombre) {
        return repository.buscarPorNombre(nombre);
    }
}