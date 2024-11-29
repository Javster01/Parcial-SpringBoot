package com.parcialSpring.electiva.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

@Entity
@Table(name = "detalles_venta")
public class DetalleVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único del detalle de la venta", example = "1", hidden = true)
    private Long id;

    @Schema(description = "Cantidad de productos vendidos", example = "2", required = true)
    private Integer cantidad;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "producto_id")
    @Schema(description = "Producto asociado a este detalle de venta", required = true)
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "venta_id")
    @Schema(description = "Venta a la que pertenece este detalle", required = true)
    private Venta venta;

    // Constructor vacío
    public DetalleVenta() {}

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }
}
