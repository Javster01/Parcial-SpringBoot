package com.parcialSpring.electiva.controller;

import com.parcialSpring.electiva.entities.Producto;
import com.parcialSpring.electiva.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    // Listar todos los productos
    @Operation(summary = "Obtener todos los productos",
            description = "Este endpoint devuelve todos los productos disponibles en la base de datos.")
    @ApiResponse(responseCode = "200", description = "Productos obtenidos exitosamente")
    @GetMapping
    public List<Producto> listarProductos() {
        return productoService.listarProductos();
    }

    // Crear o actualizar un producto
    @Operation(summary = "Crear o actualizar un producto",
            description = "Este endpoint permite crear un nuevo producto o actualizar uno existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Producto creado exitosamente"),
            @ApiResponse(responseCode = "200", description = "Producto actualizado exitosamente")
    })
    @PostMapping
    public ResponseEntity<Producto> guardarProducto(@RequestBody Producto producto) {
        Producto productoGuardado = productoService.guardarProducto(producto);
        return ResponseEntity.status(producto.getId() == null ? 201 : 200).body(productoGuardado);
    }

    // Obtener un producto por ID
    @Operation(summary = "Obtener un producto por ID",
            description = "Este endpoint busca un producto en la base de datos usando su ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Producto encontrado"),
                    @ApiResponse(responseCode = "404", description = "Producto no encontrado")
            })
    @GetMapping("/{id}")
    public ResponseEntity<Producto> buscarProductoPorId(
            @Parameter(description = "ID del producto a buscar") @PathVariable Long id) {
        Optional<Producto> producto = productoService.buscarProductoPorId(id);
        return producto.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Eliminar un producto por ID
    @Operation(summary = "Eliminar un producto por ID",
            description = "Este endpoint elimina un producto de la base de datos usando su ID.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Producto eliminado exitosamente"),
                    @ApiResponse(responseCode = "404", description = "Producto no encontrado")
            })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        try {
            productoService.eliminarProducto(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
