/**
 * 
 */
package ar.edu.unju.fi.tp7.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ar.edu.unju.fi.tp7.entity.Producto;

/**
 * @author Team Fernet
 *
 */
public interface IProductoService {

	public void guardar(Producto producto);

	public Producto obtenerUltimo() throws Exception;

	public List<Producto> obtenerProductos();

	public Producto buscarProducto(long id) throws Exception;

	public Page<Producto> findAll(Pageable pagable);
	
	public void borrar(long id);
}
