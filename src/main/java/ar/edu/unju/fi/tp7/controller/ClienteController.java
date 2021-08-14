/**
 * 
 */
package ar.edu.unju.fi.tp7.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ar.edu.unju.fi.tp7.entity.Cliente;
import ar.edu.unju.fi.tp7.service.IClienteService;

/**
 * @author Team Fernet
 *
 */
@Controller
public class ClienteController {

	@Autowired
	@Qualifier("clienteServiceImp")
	private IClienteService clienteService;

	@Autowired
	private Cliente cliente;

	/**
	 * 
	 * @param model
	 * @return La página para el alta de nuevo Cliente
	 */
	@GetMapping("/cliente/nuevo")
	public String getClientePage(Model model) {
		/**
		 * El attribute "cliente" es el objeto que esta en el form mientras que cliente
		 * es el atributo de tipo Cliente de esta clase
		 */
		model.addAttribute("cliente", cliente);
		return "nuevocliente.html";
	}

	/**
	 * 
	 * @param cliente
	 * @return Un modelo y vista "clientes.html" que muestra la lista de clientes
	 */
	@PostMapping("/cliente/guardar")
	public String getModelPageClientes(@ModelAttribute("cliente") Cliente cliente) {
		
		/**
		 * Se agrega el objeto que vino de la vista a la listaClientes de la clase que
		 * implementa IServiceCliente
		 */
		clienteService.guardar(cliente);
		/**
		 * Se agrega la listaClientes de la clase que implementa IServiceCliente al
		 * modelAndView para luego ser recorrida y mostrada en la vista("clientes")
		 */
		return "redirect:/cliente/lista";
	}

	/**
	 * 
	 * @param model
	 * @return La página "clientes.html" con los clientes existentes
	 */
	@GetMapping("/cliente/lista")
	public String getListadoPage(@RequestParam Map<String, Object> params, Model model) {
		model.addAttribute("cliente", cliente);
		int page = params.get("page") != null ? Integer.valueOf(params.get("page").toString()) - 1 : 0;
		PageRequest pageRequest = PageRequest.of(page, 5);
		Page<Cliente> pageCliente = clienteService.findAll(pageRequest);
		int totalPage = pageCliente.getTotalPages();
		if (totalPage > 0) {
			List<Integer> pages = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
			model.addAttribute("pages", pages);
		}
		model.addAttribute("clientes", pageCliente.getContent());
		model.addAttribute("current", page + 1);
		model.addAttribute("next", page + 2);
		model.addAttribute("prev", page);
		model.addAttribute("last", totalPage);
		return "clientes";
	}
	
	@PostMapping(value = "/cliente/update")
	public String getUpdatePage(@ModelAttribute("producto") Cliente cliente) throws Exception {
		clienteService.guardar(cliente);
		return "redirect:/cliente/lista";
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/cliente/delete/{id}")
	public String delete(@PathVariable(value = "id") int id) {
		clienteService.borrar(id);
		return "redirect:/cliente/lista";
	}

}
