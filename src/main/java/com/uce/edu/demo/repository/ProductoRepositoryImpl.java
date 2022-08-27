package com.uce.edu.demo.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.stereotype.Repository;

import com.uce.edu.demo.repository.modelo.Producto;

@Repository
@Transactional
public class ProductoRepositoryImpl implements IProductoRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional(value = TxType.MANDATORY)
	public void insertar(Producto producto) {
		this.entityManager.persist(producto);
	}

	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)
	public Producto buscar(String codigoBarras) {
		TypedQuery<Producto> myQuery = this.entityManager
				.createQuery("SELECT p FROM Producto p WHERE p.codigoBarras = :codigo", Producto.class);
		myQuery.setParameter("codigo", codigoBarras);
		try {
			return myQuery.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	@Transactional(value = TxType.MANDATORY)
	public void actualizar(Producto producto) {
		this.entityManager.merge(producto);
	}

	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)
	public Producto buscarProducto(String codigoBarras) {
		CriteriaBuilder myCriteria = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<Producto> myQuery = myCriteria.createQuery(Producto.class);

		Root<Producto> myTabla = myQuery.from(Producto.class);

		Predicate predicado = myCriteria.equal(myTabla.get("codigoBarras"), codigoBarras);

		myQuery.select(myTabla).where(predicado);
		TypedQuery<Producto> myQueryFinal = this.entityManager.createQuery(myQuery);

		return myQueryFinal.getSingleResult();
	}

}
