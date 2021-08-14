package ar.edu.unju.fi.tp7.service;

import java.util.List;

import ar.edu.unju.fi.tp7.entity.Compra;

/**
 * @author Team Fernet
 *
 */
public interface ICompraService {

	public void guardarCompra(Compra compra);

	public List<Compra> obtenerCompras();

}
