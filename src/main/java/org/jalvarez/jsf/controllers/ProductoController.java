package org.jalvarez.jsf.controllers;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.inject.Model;
import jakarta.enterprise.inject.Produces;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import org.jalvarez.jsf.entities.Categoria;
import org.jalvarez.jsf.entities.Producto;
import org.jalvarez.jsf.qualifiers.CustomFacesContext;
import org.jalvarez.jsf.services.ProductoService;

import java.util.List;
import java.util.ResourceBundle;

// CDI annotation that combines @Named and @RequestScoped for use in JSF views
@Model
public class ProductoController {

    // Holds the current product being managed
    private Producto producto;

    // Identifier for the product being edited
    private Long id;

    // Service layer for managing Producto operations
    @Inject
    private ProductoService service;

    // Context for managing JSF messages and navigation
    @Inject
    @CustomFacesContext
    private FacesContext facesContext;

    // Resource bundle for localized messages
    @Inject
    private ResourceBundle bundle;

    // List of products to display in the view
    private List<Producto> listado;

    // Text used for searching products by name
    private String textoBuscar;

    // Initializes the list of products and creates a new Producto instance
    @PostConstruct
    public void init() {
        this.listado = service.listar();
        producto = new Producto();
    }

    @Produces
    @Model
    public String titulo(){
        // Returns the localized title for the page
        return bundle.getString("producto.texto.titulo");
    }


    // Method to initialize or fetch a Producto based on its ID
    public Producto producto(){
        this.producto = new Producto();
        // Fetches the product by ID if it exists
        if (id != null && id > 0) {
            service.porId(id).ifPresent(p -> {
                this.producto = p;
            });
        }
        return producto;
    }

    @Produces
    @Model
    // Fetches the list of categories to display in a dropdown or selection menu
    public List<Categoria> categorias() {
        return service.listarCategorias();
    }

    // Prepares the controller for editing a product by its ID
    public void editar(Long id) {
        this.id = id;
        producto(); // Fetches the product details
    }

    // Saves the product and displays a success message based on the action (create/edit)
    public void guardar() {
        System.out.println(producto);

        if (producto.getId() != null && producto.getId() > 0) {
            facesContext.addMessage(null, new FacesMessage(String.format(bundle.getString("producto.mensaje.editar"), producto.getNombre())));
        } else {
            facesContext.addMessage(null, new FacesMessage(String.format(bundle.getString("producto.mensaje.crear"), producto.getNombre())));
        }
        service.guardar(producto); // Calls the service to save the product
        listado = service.listar(); // Refreshes the list of products
        producto = new Producto(); // Resets the product instance
    }

    // Deletes the product and refreshes the product list
    public void eliminar(Producto producto) {
        service.eliminar(producto.getId());
        facesContext.addMessage(null, new FacesMessage(String.format(bundle.getString("producto.mensaje.eliminar"), producto.getNombre())));
        listado = service.listar(); // Refreshes the list after deletion
    }

    public void buscar() {
        // Filters the product list based on the search text
        this.listado = service.buscarPorNombre(this.textoBuscar);
    }

    public void cerrarDialogo() {
        // Closes a dialog and resets the product instance
        System.out.println("Cerrando la ventana de dialogo! .....................");
        producto = new Producto();
    }


    // Getters and setters for the properties, used for JSF binding

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Producto> getListado() {
        return listado;
    }

    public void setListado(List<Producto> listado) {
        this.listado = listado;
    }

    public String getTextoBuscar() {
        return textoBuscar;
    }

    public void setTextoBuscar(String textoBuscar) {
        this.textoBuscar = textoBuscar;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}