package com.parcialSpring.electiva.controller;

import com.parcialSpring.electiva.entities.Venta;
import com.parcialSpring.electiva.service.VentaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ventas")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    // Crear una nueva venta
    @Operation(summary = "Crear una nueva venta",
            description = "Este endpoint permite crear una nueva venta en el sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Venta creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Error en los datos de la venta")
    })
    @PostMapping
    public ResponseEntity<Venta> guardarVenta(@RequestBody Venta venta) {
        Venta ventaGuardada = ventaService.guardarVenta(venta);
        return ResponseEntity.status(201).body(ventaGuardada);
    }

    // Listar todas las ventas
    @Operation(summary = "Listar todas las ventas",
            description = "Este endpoint devuelve todas las ventas registradas en el sistema.")
    @ApiResponse(responseCode = "200", description = "Ventas obtenidas exitosamente")
    @GetMapping
    public List<Venta> listarVentas() {
        return ventaService.listarVentas();
    }

    // Buscar una venta por ID
    @Operation(summary = "Buscar una venta por ID",
            description = "Este endpoint busca una venta en el sistema utilizando su ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Venta encontrada"),
                    @ApiResponse(responseCode = "404", description = "Venta no encontrada")
            })
    @GetMapping("/{id}")
    public ResponseEntity<Venta> buscarVentaPorId(
            @Parameter(description = "ID de la venta a buscar") @PathVariable Long id) {
        Venta venta = ventaService.buscarVentaPorId(id);
        if (venta != null) {
            return ResponseEntity.ok(venta);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar una venta
    @Operation(summary = "Eliminar una venta por ID",
            description = "Este endpoint elimina una venta en el sistema utilizando su ID.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Venta eliminada exitosamente"),
                    @ApiResponse(responseCode = "404", description = "Venta no encontrada")
            })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarVenta(@PathVariable Long id) {
        try {
            ventaService.eliminarVenta(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
