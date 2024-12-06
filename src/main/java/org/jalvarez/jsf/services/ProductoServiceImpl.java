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

// Marks this class as a stateless EJB for transaction management and dependency injection
@Stateless
// Declares security roles used for method-level access control
@DeclareRoles({"USER", "ADMIN"})
public class ProductoServiceImpl implements ProductoService {

    @Inject
    // Repository for product-related database operations
    private ProductoRepository repository;

    @Inject
    // Generic repository for category-related operations
    private CrudRepository<Categoria> categoriaRepository;

    @Resource
    // Provides access to the caller's security context and other session-related info
    private SessionContext ctx;

    @Override
    @PermitAll // Allows access to all roles and anonymous users for this method
    public List<Producto> listar() {

        // Retrieves the logged-in user's information
        Principal usuario = ctx.getCallerPrincipal();

        String username = usuario.getName();  // Gets the username of the current caller
        System.out.println("username: " + username);

        // Checks if the caller has the ADMIN role
        if(ctx.isCallerInRole("ADMIN")) {
            System.out.println("Soy un ADMIN");

            // Checks if the caller has the USER role
        } else if (ctx.isCallerInRole("USER")) {
            System.out.println("Soy un USER");

            // Handles cases where the caller has no specific role
        } else {
            System.out.println("Soy ANONYMOUS");
        }
        // Returns the list of all products
        return repository.listar();
    }

    @Override
    @RolesAllowed({"USER", "ADMIN"}) // Restricts access to users with USER or ADMIN roles
    public Optional<Producto> porId(Long id) {
        // Fetches a product by ID, wrapped in Optional
            return Optional.ofNullable(repository.porId(id));
    }

    @Override
    // Restricts access to ADMIN role only
    @RolesAllowed("ADMIN")
    public void guardar(Producto producto) {
        // Saves or updates a product in the database
        repository.guardar(producto);
    }

    @Override
    // Restricts access to ADMIN role only
    @RolesAllowed("ADMIN")
    public void eliminar(Long id) {
        // Deletes a product by its ID
        repository.eliminar(id);
    }

    @Override
    // Restricts access to USER and ADMIN roles
    @RolesAllowed({"USER", "ADMIN"})
    public List<Categoria> listarCategorias() {
        // Fetches the list of all categories
        return categoriaRepository.listar();
    }

    @Override
    // Restricts access to USER and ADMIN roles
    @RolesAllowed({"USER", "ADMIN"})
    public Optional<Categoria> porIdCategoria(Long id) {
        // Fetches a category by ID, wrapped in Optional
        return Optional.ofNullable(categoriaRepository.porId(id));
    }

    @Override
    // Restricts access to USER and ADMIN roles
    @RolesAllowed({"USER", "ADMIN"})
    public List<Producto> buscarPorNombre(String nombre) {
        // Searches for products by name or partial name match
        return repository.buscarPorNombre(nombre);
    }
}