package com.parcialSpring.electiva.service;

import com.parcialSpring.electiva.entities.DetalleVenta;
import com.parcialSpring.electiva.repository.DetalleVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetalleVentaService {

    @Autowired
    private DetalleVentaRepository detalleVentaRepository;

    // Listar todos los detalles de ventas
    public List<DetalleVenta> listarDetalles() {
        return detalleVentaRepository.findAll();
    }

    // Guardar un detalle de venta
    public DetalleVenta guardarDetalle(DetalleVenta detalleVenta) {
        return detalleVentaRepository.save(detalleVenta);
    }

    // Buscar un detalle por ID
    public Optional<DetalleVenta> buscarDetallePorId(Long id) {
        return detalleVentaRepository.findById(id);
    }

    // Eliminar un detalle
    public void eliminarDetalle(Long id) {
        detalleVentaRepository.deleteById(id);
    }
}
