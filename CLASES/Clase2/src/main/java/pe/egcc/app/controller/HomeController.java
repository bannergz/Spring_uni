package pe.egcc.app.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pe.egcc.app.service.MateService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	 @Autowired //COn el autowired se le inyecta automaticamente la instancia de su valor en service
	private MateService mateService; 
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping(value="suma.htm", method=RequestMethod.GET)
	 public String  calcSuma() {
		return "Suma";
	}
	
	@RequestMapping(value="suma.htm", method=RequestMethod.POST)
	 public String  calcSuma(HttpServletRequest request, Model model) {
		//Datos 
		 int n1 = Integer.parseInt(request.getParameter("n1"));

		 int n2 = Integer.parseInt(request.getParameter("n2"));
		 //Proceso
		 int suma = mateService.sumar(n1, n2);
		 //Reporte
		 model.addAttribute("n1", n1);
		 model.addAttribute("n2", n2);
		 model.addAttribute("Suma", suma); //Dado a que es Suma.jsp, tambien el att debe ser Suma
		 //FORWARD
		 return "Suma";
	}
	
	@RequestMapping(value="ventas.htm", method=RequestMethod.GET)
	 public String  calventas() {
		return "ventas";
	}
	
	@RequestMapping(value="ventas.htm", method=RequestMethod.POST)
	public String calventas(HttpServletRequest request, Model model){

		//Datos
		int n1 = Integer.parseInt(request.getParameter("precio"));
		int n2 = Integer.parseInt(request.getParameter("cantidad"));
		int importe = mateService.importe(n1, n2);
		double impuesto = mateService.impuesto(n1, n2);
		double total = mateService.total(n1, n2);
		
		model.addAttribute("precio", n1);
		 model.addAttribute("cantidad", n2);
		 model.addAttribute("importe", importe);
		 model.addAttribute("impuesto", impuesto);
		 model.addAttribute("total", total);
		 return "ventas";
		
	}
	
	@RequestMapping(value="suma2.htm", method=RequestMethod.POST)
	 public String  calcSuma2(
			 @RequestParam("n1") int num1,
			 @RequestParam("n2") int num2, Model model) {
		//Datos 
		
		 //Proceso
		 int suma = mateService.sumar(num1, num2);
		 //Reporte
		 model.addAttribute("n1", num1);
		 model.addAttribute("n2", num2);
		 model.addAttribute("Suma", suma); //Dado a que es Suma.jsp, tambien el att debe ser Suma
		 //FORWARD
		 return "Suma";
	}
	
	@RequestMapping(value="promedio.htm", method=RequestMethod.GET)
	 public String  calcPromedio() {
		return "Notas";
	}
	
	@RequestMapping(value="promedio.htm", method=RequestMethod.POST)
	 public String  calcPromedio(HttpServletRequest request, Model model) throws Exception {
		//Datos 
		 int n1 = Integer.parseInt(request.getParameter("n1"));
		 int n2 = Integer.parseInt(request.getParameter("n2"));
		 int n3 = Integer.parseInt(request.getParameter("n3"));
		 int n4 = Integer.parseInt(request.getParameter("n4"));
		 //Proceso
		 if (n1>20 || n2>20 || n3>20 || n4>20 ) {
			 throw new RuntimeException("Algun numero es mayor de 20");
			 
			 
		}
		 double promedio = mateService.promedio(n1, n2, n3, n4);
		 //Reporte
		 model.addAttribute("n1", n1);
		 model.addAttribute("n2", n2);
		 model.addAttribute("n3", n3);
		 model.addAttribute("n4", n4);
		 model.addAttribute("Promedio", promedio); //Dado a que es Suma.jsp, tambien el att debe ser Suma
		 //FORWARD
		 return "Notas";
	}
	
}
