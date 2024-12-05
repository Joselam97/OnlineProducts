package org.jalvarez.jsf.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String nombre;

    @NotNull
    @Min(5)
    private Integer precio;

    @NotEmpty
    @Size(min = 3, max = 10)
    private String sku;

    @NotNull
    @Column(name = "fecha_registro")
    private LocalDate fechaRegistro;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Categoria categoria;

    public Producto() {
    }

    public Producto(String nombre) {
        this.nombre = nombre;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    //@PrePersist
    public void prePersist() {
        fechaRegistro = LocalDate.now();
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "{" +
                "id: " + id +
                ", nombre: '" + nombre + '\'' +
                ", precio: " + precio +
                ", sku: '" + sku + '\'' +
                ", fechaRegistro: " + fechaRegistro +
                '}';
    }
}