package demo05;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PruebaA {
  @SuppressWarnings("resource")
public static void main(String[] args) {
	  String fileXML = "demo05/demo01.xml";
	BeanFactory bf; //Bean Factory
	bf = new ClassPathXmlApplicationContext(fileXML);
	// Acceso de obj 
	
	Control bean;
	bean = bf.getBean(Control.class);
	
	//Prueba
	System.out.println("7+4 = " + bean.sumar(7, 4));
	System.out.println("Nombre = " + bean.getNombre());
}
}
