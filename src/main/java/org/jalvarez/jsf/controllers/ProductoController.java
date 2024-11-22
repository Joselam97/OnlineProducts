package org.jalvarez.jsf.controllers;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Model;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.jalvarez.jsf.entities.Producto;
import org.jalvarez.jsf.services.ProductoService;

import java.util.List;

@Model
public class ProductoController {

    @Inject
    private ProductoService service;

    @Produces
    @Model
    public String titulo(){
        return "Productos Online";
    }

    @Produces
    @RequestScoped
    @Named("listado")
    public List<Producto> findAll() {
        //return Arrays.asList(new Producto("peras"), new Producto("manzanas"), new Producto("mandarinas"));
        return service.listar();
    }
}
