package com.parcialSpring.electiva.service;

import com.parcialSpring.electiva.entities.DetalleVenta;
import com.parcialSpring.electiva.entities.Producto;
import com.parcialSpring.electiva.entities.Venta;
import com.parcialSpring.electiva.repository.VentaRepository;
import com.parcialSpring.electiva.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    // Guardar una venta y actualizar el stock
    @Transactional
    public Venta guardarVenta(Venta venta) {
        // Guardar la venta
        Venta nuevaVenta = ventaRepository.save(venta);

        // Actualizar el stock de los productos vendidos
        for (DetalleVenta detalle : venta.getDetalles()) {
            Producto producto = detalle.getProducto();
            int cantidadVendida = detalle.getCantidad();

            // Actualizar el stock del producto
            if (producto.getStock() >= cantidadVendida) {
                producto.setStock(producto.getStock() - cantidadVendida);
                productoRepository.save(producto); // Guardar el producto con el nuevo stock
            } else {
                throw new RuntimeException("Stock insuficiente para el producto: " + producto.getNombre());
            }
        }

        return nuevaVenta;
    }

    // Listar todas las ventas
    public List<Venta> listarVentas() {
        return ventaRepository.findAll();
    }

    // Buscar una venta por ID
    public Venta buscarVentaPorId(Long id) {
        return ventaRepository.findById(id).orElse(null);
    }

    // Eliminar una venta
    public void eliminarVenta(Long id) {
        ventaRepository.deleteById(id);
    }
}
