package com.parcialSpring.electiva.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único del producto", example = "1", hidden = true)
    private Long id;

    @Schema(description = "Nombre del producto", example = "Producto A", required = true)
    private String nombre;

    @Schema(description = "Precio unitario del producto", example = "100.0", required = true)
    private Double precio;

    @Schema(description = "Cantidad de productos en stock", example = "50", required = true)
    private Integer stock;

    // Constructor vacío (necesario para JPA)
    public Producto() {}

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
