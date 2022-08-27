package com.uce.edu.demo.service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.uce.edu.demo.repository.IDetalleVentaRepository;
import com.uce.edu.demo.repository.IProductoRepository;
import com.uce.edu.demo.repository.modelo.DetalleVenta;
import com.uce.edu.demo.repository.modelo.Producto;
import com.uce.edu.demo.repository.modelo.ProductoVenta;
import com.uce.edu.demo.service.funcional.IProductoFunction;

@Repository
@Transactional
public class GestorVentaServiceImpl implements IGestorVentaService {
	@Autowired
	private IProductoRepository productoRepository;

	@Autowired
	private IVentaService ventaService;

	@Autowired
	private IDetalleVentaRepository detalleVentaRepository;

	@Override
	@Transactional(value = TxType.REQUIRES_NEW)
	public void realizarVenta(List<ProductoVenta> listaProductos, String cedulaCliente, String numeroVenta)
			throws SQLException {
		Producto producto = new Producto();
		DetalleVenta detalle = new DetalleVenta();
		List<DetalleVenta> detalles = new ArrayList<DetalleVenta>();
		BigDecimal subtotal = BigDecimal.ZERO;
		BigDecimal totalVenta = BigDecimal.ZERO;
		Integer cantidad = 0;

		for (ProductoVenta p : listaProductos) {
			producto = this.productoRepository.buscar(p.getCodigoBarras());
			cantidad = p.getCantidad();

			if (producto == null || producto.getStock() == 0) {
				throw new RuntimeException();
			}

			if (cantidad > producto.getStock()) {
				cantidad = producto.getStock();
			}
			detalle.setProducto(producto);
			detalle.setCantidad(cantidad);

			producto.setStock(producto.getStock() - cantidad);
			this.productoRepository.actualizar(producto);

			detalle.setPrecioUnitario(producto.getPrecio());
			subtotal = producto.getPrecio().multiply(new BigDecimal(cantidad));
			detalle.setSubtotal(subtotal);
			totalVenta.add(subtotal);
			detalles.add(detalle);
		}

		this.ventaService.crearVenta(detalles, cedulaCliente, numeroVenta, totalVenta);
	}

	@Override
	@Transactional(value = TxType.REQUIRED)
	public void reporteVenta(LocalDateTime fechaVenta, String categoria, Integer cantidad) {

		List<DetalleVenta> detalles = this.detalleVentaRepository.buscar(fechaVenta, categoria, cantidad);
		functionLambda.apply(detalles);

	}

	IProductoFunction<String, List<DetalleVenta>> functionLambda = infoProducto -> {
		List<DetalleVenta> detalles = new ArrayList<DetalleVenta>();
		StringBuilder info = new StringBuilder();

		for (DetalleVenta d : detalles) {
			info.append(d.getProducto().getCodigoBarras() + "\n");
			info.append(d.getProducto().getCategoria() + "\n");
			info.append(d.getCantidad() + "\n");
			info.append(d.getPrecioUnitario() + "\n");
			info.append(d.getSubtotal());
		}

		return info.toString();
	};

}
