package com.uce.edu.demo.repository;

import com.uce.edu.demo.repository.modelo.Producto;

public interface IProductoRepository {
	public void insertar(Producto producto);

	public Producto buscar(String codigoBarras);

	public Producto buscarProducto(String codigoBarras);

	public void actualizar(Producto producto);
}
