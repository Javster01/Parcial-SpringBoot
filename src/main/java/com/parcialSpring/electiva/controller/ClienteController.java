package com.parcialSpring.electiva.controller;

import com.parcialSpring.electiva.entities.Cliente;
import com.parcialSpring.electiva.service.ClienteService;
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
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    // Listar todos los clientes
    @Operation(summary = "Obtener todos los clientes",
            description = "Este endpoint devuelve todos los clientes disponibles en la base de datos.")
    @ApiResponse(responseCode = "200", description = "Clientes obtenidos exitosamente")
    @GetMapping
    public List<Cliente> listarClientes() {
        return clienteService.listarClientes();
    }

    // Crear o actualizar un cliente
    @Operation(summary = "Crear o actualizar un cliente",
            description = "Este endpoint permite crear un nuevo cliente o actualizar uno existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente creado exitosamente"),
            @ApiResponse(responseCode = "200", description = "Cliente actualizado exitosamente")
    })
    @PostMapping
    public Cliente guardarCliente(@RequestBody Cliente cliente) {
        return clienteService.guardarCliente(cliente);
    }

    // Obtener un cliente por ID
    @Operation(summary = "Obtener un cliente por ID",
            description = "Este endpoint busca un cliente en la base de datos usando su ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Cliente encontrado"),
                    @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
            })
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarClientePorId(
            @Parameter(description = "ID del cliente a buscar") @PathVariable Long id) {
        Optional<Cliente> cliente = clienteService.buscarClientePorId(id);
        return cliente.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Eliminar un cliente por ID
    @Operation(summary = "Eliminar un cliente por ID",
            description = "Este endpoint elimina un cliente de la base de datos usando su ID.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Cliente eliminado exitosamente"),
                    @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
            })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Long id) {
        clienteService.eliminarCliente(id);
        return ResponseEntity.noContent().build();
    }
}
