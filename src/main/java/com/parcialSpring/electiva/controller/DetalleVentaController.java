package com.parcialSpring.electiva.controller;

import com.parcialSpring.electiva.entities.DetalleVenta;
import com.parcialSpring.electiva.service.DetalleVentaService;
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
@RequestMapping("/detalles")
public class DetalleVentaController {

    @Autowired
    private DetalleVentaService detalleVentaService;

    // Listar todos los detalles
    @Operation(summary = "Obtener todos los detalles de venta",
            description = "Este endpoint devuelve todos los detalles de venta registrados en el sistema.")
    @ApiResponse(responseCode = "200", description = "Detalles de venta obtenidos exitosamente")
    @GetMapping
    public List<DetalleVenta> listarDetalles() {
        return detalleVentaService.listarDetalles();
    }

    // Crear o actualizar un detalle de venta
    @Operation(summary = "Crear o actualizar un detalle de venta",
            description = "Este endpoint permite crear un nuevo detalle de venta o actualizar uno existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Detalle de venta creado exitosamente"),
            @ApiResponse(responseCode = "200", description = "Detalle de venta actualizado exitosamente")
    })
    @PostMapping
    public ResponseEntity<DetalleVenta> guardarDetalle(@RequestBody DetalleVenta detalleVenta) {
        DetalleVenta detalleGuardado = detalleVentaService.guardarDetalle(detalleVenta);
        return ResponseEntity.status(detalleVenta.getId() == null ? 201 : 200).body(detalleGuardado);
    }

    // Obtener un detalle de venta por ID
    @Operation(summary = "Obtener un detalle de venta por ID",
            description = "Este endpoint busca un detalle de venta en el sistema utilizando su ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Detalle de venta encontrado"),
                    @ApiResponse(responseCode = "404", description = "Detalle de venta no encontrado")
            })
    @GetMapping("/{id}")
    public ResponseEntity<DetalleVenta> buscarDetallePorId(
            @Parameter(description = "ID del detalle de venta a buscar") @PathVariable Long id) {
        Optional<DetalleVenta> detalle = detalleVentaService.buscarDetallePorId(id);
        return detalle.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Eliminar un detalle de venta por ID
    @Operation(summary = "Eliminar un detalle de venta por ID",
            description = "Este endpoint elimina un detalle de venta en el sistema utilizando su ID.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Detalle de venta eliminado exitosamente"),
                    @ApiResponse(responseCode = "404", description = "Detalle de venta no encontrado")
            })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarDetalle(@PathVariable Long id) {
        try {
            detalleVentaService.eliminarDetalle(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
