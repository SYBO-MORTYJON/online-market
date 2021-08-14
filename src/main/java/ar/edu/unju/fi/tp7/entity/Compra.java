/**
 * 
 */
package ar.edu.unju.fi.tp7.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

/**
 * It uses MySQL
 * 
 * @author Team Fernet
 *
 */
@Entity
@Component
@Table(name = "COMPRAS")
public class Compra implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "com_id")
	private long id;

	@Column(name = "com_cantidad")
	private int cantidad;

	@Column(name = "com_total")
	private double total;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "prod_codigo")
	private Producto producto;

	public Compra() {
	}

	/**
	 * @param cantidad
	 * @param total
	 * @param cliente
	 * @param producto
	 */
	public Compra(int cantidad, double total, Producto producto) {
		this.cantidad = cantidad;
		this.total = total;
		this.producto = producto;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the cantidad
	 */
	public int getCantidad() {
		return cantidad;
	}

	/**
	 * @param cantidad the cantidad to set
	 */
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	/**
	 * @return the total
	 */
	public double getTotal() {
		return total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(double total) {
		this.total = total;
	}

	/**
	 * @return the producto
	 */
	public Producto getProducto() {
		return producto;
	}

	/**
	 * @param producto the producto to set
	 */
	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	@Override
	public String toString() {
		return "Compra [id=" + id + ", cantidad=" + cantidad + ", total=" + total + ", producto=" + producto + "]";
	}

}
