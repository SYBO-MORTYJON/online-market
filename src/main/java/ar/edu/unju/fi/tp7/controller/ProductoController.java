/**
 * 
 */
package ar.edu.unju.fi.tp7.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ar.edu.unju.fi.tp7.entity.Producto;
import ar.edu.unju.fi.tp7.service.IProductoService;

/**
 * 
 * @author Team Fernet
 *
 */
@Controller
public class ProductoController {

	@Autowired
	private Producto producto;

	@Autowired
	private IProductoService productoService;

	/**
	 * 
	 * @return La página del formulario para el alta de nuevo Producto
	 */
	@GetMapping("/producto")
	public String getPage(Model model) {
		model.addAttribute(producto);
		return "nuevo";
	}

	/**
	 * 
	 * @param params
	 * @param model
	 * @return Tabla CRUD Productos
	 */
	@GetMapping("/productos")
	public String findAll(@RequestParam Map<String, Object> params, Model model) {
		model.addAttribute("producto", producto);
		int page = params.get("page") != null ? Integer.valueOf(params.get("page").toString()) - 1 : 0;
		PageRequest pageRequest = PageRequest.of(page, 5);
		Page<Producto> pageProducto = productoService.findAll(pageRequest);
		int totalPage = pageProducto.getTotalPages();
		if (totalPage > 0) {
			List<Integer> pages = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
			model.addAttribute("pages", pages);
		}
		model.addAttribute("productos", pageProducto.getContent());
		model.addAttribute("current", page + 1);
		model.addAttribute("next", page + 2);
		model.addAttribute("prev", page);
		model.addAttribute("last", totalPage);

		return "productos";
	}

	/**
	 * 
	 * @param file
	 * @param attributes
	 * @param producto
	 * @return
	 * @throws IOException
	 */
	@PostMapping(value = "/producto/guardar", consumes = "multipart/form-data")
	public String getResultado(@RequestParam("file") MultipartFile file, RedirectAttributes attributes,
			@Valid @ModelAttribute("producto") Producto producto, BindingResult result, Model model)
			throws IOException {
		if (result.hasErrors()) {
			System.out.println("Error!");
			model.addAttribute("producto", producto);
			return "productos";

		} else {
			System.out.println("Else");
			byte[] content = file.getBytes();
			String base64 = Base64.getEncoder().encodeToString(content);
			producto.setImagen(base64);
			productoService.guardar(producto);

			return "redirect:/productos";
		}

	}

	/**
	 * 
	 * @param file
	 * @param producto
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/update", consumes = "multipart/form-data")
	public String getUpdatePage(@RequestParam("file") MultipartFile file, @ModelAttribute("producto") Producto producto)
			throws Exception {
		if (!file.isEmpty()) {
			byte[] content = file.getBytes();
			String base64 = Base64.getEncoder().encodeToString(content);
			producto.setImagen(base64);
		}
		productoService.guardar(producto);
		return "redirect:/productos";
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/delete/{id}")
	public String delete(@PathVariable(value = "id") int id) {
		productoService.borrar(id);
		return "redirect:/productos";
	}

	/**
	 * 
	 * @return La página que muestra el último producto agregado
	 * @throws Exception una pequeña maña pero no me gusta...
	 */
	@GetMapping("/producto/ultimo")
	public String getUltimoProducto(Model map) throws Exception {
//		List<Producto> productos = new ArrayList<>();
//		productos = productoService.obtenerProductos();
//		if (productos.size() > 0) {
//			map.addAttribute("producto", productoService.obtenerUltimo());
//		}
//		map.addAttribute("productos", productos);
//		producto = null;
//		if(productoService.obtenerUltimo() != null) {
//			producto = productoService.obtenerUltimo();
//		}
		map.addAttribute("producto", productoService.obtenerUltimo());
		return "ultimoproducto";
	}

}
