package pe.egcc.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pe.egcc.app.model.ProductoBean;

@Controller
public class ProductoController {
	
	@RequestMapping(value="producto.htm", method=RequestMethod.GET)
	public String proProducto() {
		return "productoInput";
		
	}
	@RequestMapping(value="producto.htm", method=RequestMethod.POST)
	public String proProducto(
			@ModelAttribute ProductoBean bean, Model model) {
		model.addAttribute("bean", bean);
		return "productoInput";
		
	}
	

}
