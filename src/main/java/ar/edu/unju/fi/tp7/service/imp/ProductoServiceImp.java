/**
 * 
 */
package ar.edu.unju.fi.tp7.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unju.fi.tp7.entity.Producto;
import ar.edu.unju.fi.tp7.repository.ProductoRepository;
import ar.edu.unju.fi.tp7.service.IProductoService;

/**
 * @author Team Fernet
 *
 */
@Service
public class ProductoServiceImp implements IProductoService {

	private static final Log LOGGER = LogFactory.getLog(ProductoServiceImp.class);

	@Autowired
	private ProductoRepository productoRepository;
	
	@Override
	public void guardar(Producto producto) {
		LOGGER.info("SERVICE: ProductoService");
		LOGGER.info("METHOD: guardar()");
		productoRepository.save(producto);
		LOGGER.info("RESULT: Se guardó el cliente " + producto.getNombre());;
	}

	@Override
	public Producto obtenerUltimo()throws Exception {
		LOGGER.info("SERVICE: ProductoService");
		LOGGER.info("METHOD: obtenerUltimo()");
		Producto producto = null;
		producto = (Producto) productoRepository.findTopByOrderByCodigoDesc();
		LOGGER.info("RESULT: Ultimo producto");
		return producto;
	}

	@Override
	public List<Producto> obtenerProductos() {
		LOGGER.info("SERVICE: ProductoService");
		LOGGER.info("METHOD: obtenerProductos()");
		List<Producto> productos = new ArrayList<>();
		productoRepository.findAll().forEach(productos::add);
		LOGGER.info("METHOD: Lista tamaño: " +  productos.size());
		return productos;
	}

	@Override
	public Producto buscarProducto(long id) throws Exception {
		LOGGER.info("SERVICE: ProductoService");
		LOGGER.info("METHOD: buscarProducto()");
		Optional<Producto> optional = productoRepository.findById(id);
		Producto producto = null;
		if (optional.isPresent()) {
			producto = optional.get();
			LOGGER.info("METHOD: Producto: " + producto.getNombre());
		} else {
			throw new Exception("Producto no encontrado");
		}
		return producto;
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Producto> findAll(Pageable pagable) {
		LOGGER.info("SERVICE: ProductoService");
		LOGGER.info("METHOD: findAll()");
		LOGGER.info("RESULT: Página de productos");
		return productoRepository.findAll(pagable);
	}

	@Override
	public void borrar(long id) {
		LOGGER.info("SERVICE: ProductoService");
		LOGGER.info("METHOD: borrar()");
		LOGGER.info("RESULT: " + id);
		productoRepository.deleteById(id);
	}

	

}
