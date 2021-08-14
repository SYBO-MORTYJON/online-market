/**
 * 
 */
package ar.edu.unju.fi.tp7.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.fi.tp7.entity.Producto;

/**
 * @author Team Fernet
 *
 */
@Repository
public interface ProductoRepository extends JpaRepository <Producto, Long>{

	public Producto findTopByOrderByCodigoDesc();
	
}
