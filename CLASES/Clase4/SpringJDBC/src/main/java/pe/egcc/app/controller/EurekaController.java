package pe.egcc.app.controller;

import org.junit.internal.runners.ErrorReportingRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import pe.egcc.app.service.EurekaService;

@Controller
public class EurekaController {
	@Autowired
private EurekaService eurekaService;

	
	@RequestMapping(value="/saldoCuenta.htm", method=RequestMethod.GET)
	public String verSaldoCuenta(
			@RequestParam("cuenta") String cuenta, Model model){
		model.addAttribute("cuenta", cuenta);
		
		try {
			model.addAttribute("saldo", eurekaService.getSaldoCuenta(cuenta));
			
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		
		return "saldoCuenta";
	}
	
	@RequestMapping(value="/verCliente.htm", method=RequestMethod.GET)
	public String verCliente(
			@RequestParam("codigo") String codigo, Model model){
		
		
		try {
			model.addAttribute("bean", eurekaService.getCliente(codigo)); //EL BEAN, PARA QUE BUSQUE LOS DATOS
			
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		
		return "verCliente";
	}
	
	@RequestMapping(value="/verMovimientos.htm", method=RequestMethod.GET)
	  public String verMovimientos(){
	    return "verMovimientos";
	  }
	
	@RequestMapping(value="/verMovimientos.htm", method=RequestMethod.POST)
	public String verMovimientos(
			@RequestParam("cuenta") String cuenta, Model model){
		
		
		try {
			
			model.addAttribute("lista", eurekaService.getMovimientos(cuenta)); //EL BEAN, PARA QUE BUSQUE LOS DATOS
			
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		
		return "verMovimientos";
	}
	
	@RequestMapping(value="/procDeposito.htm", method=RequestMethod.GET)
	  public String procDeposito(){
	    return "procDeposito";
	  }
	  
	  @RequestMapping(value="/procDeposito.htm", method=RequestMethod.POST)
	  public String procDeposito(
	      @RequestParam("cuenta") String cuenta, 
	      @RequestParam("importe") double importe, 
	      Model model){
	    try {
	      eurekaService.procDeposito(cuenta, importe, "0003");
	      model.addAttribute("mensaje", "Proceso ejecutado correctamente.");
	    } catch (Exception e) {
	      model.addAttribute("error", e.getMessage());
	    }
	    return "procDeposito";
	  }
	  
	  
	  @RequestMapping(value="/procRetiro.htm", method=RequestMethod.GET)
	  public String procRetiro(){
	    return "procRetiro";
	  }
	  
	  @RequestMapping(value="/procRetiro.htm", method=RequestMethod.POST)
	  public String procDeposito(
	      @RequestParam("cuenta") String cuenta, 
	      @RequestParam("importe") double importe, 
	      @RequestParam("clave") String clave, 
	      Model model){
	    try {
	      eurekaService.procRetiro(cuenta, importe,"0003",clave);
	      model.addAttribute("mensaje", "Proceso ejecutado correctamente.");
	    } catch (Exception e) {
	      model.addAttribute("error", e.getMessage());
	    }
	    return "procRetiro";
	  }
	 
	
}
