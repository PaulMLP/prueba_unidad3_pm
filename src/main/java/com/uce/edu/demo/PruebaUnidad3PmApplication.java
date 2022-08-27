package com.uce.edu.demo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.uce.edu.demo.repository.modelo.Producto;
import com.uce.edu.demo.repository.modelo.ProductoVenta;
import com.uce.edu.demo.service.IGestorVentaService;
import com.uce.edu.demo.service.IProductoService;

@SpringBootApplication
public class PruebaUnidad3PmApplication implements CommandLineRunner {

	private static Logger LOG = Logger.getLogger(PruebaUnidad3PmApplication.class);

	@Autowired
	private IProductoService productoService;
	
	@Autowired
	private IGestorVentaService gestorVentaService;
	
	public static void main(String[] args) {
		SpringApplication.run(PruebaUnidad3PmApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Producto producto = new Producto();
		
		producto.setCodigoBarras("1111");
		producto.setNombre("CocaCola");
		producto.setPrecio(new BigDecimal(1.50));
		producto.setStock(30);
		producto.setCategoria("bebidas");
		this.productoService.ingresarProducto(producto);
		
		producto.setCodigoBarras("1111");
		producto.setNombre("CocaCola");
		producto.setPrecio(new BigDecimal(1.50));
		producto.setStock(40);
		producto.setCategoria("bebidas");
		this.productoService.ingresarProducto(producto);
		
		
		ProductoVenta pv = new ProductoVenta();
		pv.setCodigoBarras("1111");
		pv.setCantidad(3);
		List<ProductoVenta> listaProductos = new ArrayList<ProductoVenta>();
		listaProductos.add(pv);
		
		
		this.gestorVentaService.realizarVenta(listaProductos, "101010110", "1234");
		
		LOG.info(this.productoService.consultarStock("1111"));
		
		//this.gestorVentaService.reporteVenta(LocalDateTime.of(2022, 8, 26, 0, 0, 0), "bebidas", 1);
	}

}
