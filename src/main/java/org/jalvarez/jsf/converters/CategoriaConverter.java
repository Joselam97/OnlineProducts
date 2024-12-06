package org.jalvarez.jsf.converters;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.jalvarez.jsf.entities.Categoria;
import org.jalvarez.jsf.services.ProductoService;

import java.util.Optional;


// Defines the bean's lifecycle to match a single HTTP request
@RequestScoped
// Makes this converter accessible in JSF with the name "categoriaConverter"
@Named("categoriaConverter")
public class CategoriaConverter implements Converter<Categoria> {

    // Injects the service used to fetch Categoria entities from the database
    @Inject
    private ProductoService service;

    @Override
    // Converts a String ID from the view into a Categoria object
    public Categoria getAsObject(FacesContext context, UIComponent component, String id) {
        if (id == null){
            // Returns null if the ID is not provided
            return null;
        }
        // Fetches the Categoria by its ID
        Optional<Categoria> categoriaOptional = service.porIdCategoria(Long.valueOf(id));
        if (categoriaOptional.isPresent()) {
            // Returns the Categoria if found
            return categoriaOptional.get();
        }
        // Returns null if the Categoria is not found
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Categoria categoria) {
        // Converts a Categoria object into a String ID for use in the view
        if (categoria == null) {
            // Returns "0" if the Categoria object is null
            return "0";
        }
        // Returns the ID of the Categoria as a String
        return categoria.getId().toString();
    }
}
