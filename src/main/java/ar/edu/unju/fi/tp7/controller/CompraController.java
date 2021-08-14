/**
 * 
 */
package ar.edu.unju.fi.tp7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.tp7.entity.Compra;
import ar.edu.unju.fi.tp7.entity.Producto;
import ar.edu.unju.fi.tp7.service.ICompraService;
import ar.edu.unju.fi.tp7.service.IProductoService;

/**
 * @author Asus
 *
 */

@Controller
public class CompraController {

	@Autowired
	private Compra compra;

	@Autowired
	private Producto producto;

	@Autowired
	private ICompraService compraService;

	@Autowired
	private IProductoService productoService;
	
	@GetMapping(value = "/comprar/{id}")
	public String comprar(@PathVariable(value = "id") long id, Model model) throws Exception {
		compra.setProducto(productoService.buscarProducto(id));
		model.addAttribute("compra", compra);
		return "compra";
	}

	@PostMapping("/compra/guardar")
	public ModelAndView saveCompra(@ModelAttribute("compra") Compra compra) throws Exception {

		ModelAndView mav = new ModelAndView("tablacompras");
		
		producto = productoService.buscarProducto(compra.getProducto().getCodigo());
		producto.setStock(producto.getStock() - compra.getCantidad());
		
		compra.setTotal(producto.getPrecio() * compra.getCantidad());
		
		productoService.guardar(producto);
		compraService.guardarCompra(compra);
		
		mav.addObject("compras", compraService.obtenerCompras());
		return mav;
	}

	@GetMapping("/compras")
	public String getCompras(Model model) {
		model.addAttribute("compras", compraService.obtenerCompras());
		return "tablacompras";
	}

}
