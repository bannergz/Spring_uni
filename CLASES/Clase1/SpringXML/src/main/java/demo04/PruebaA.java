package demo04;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import demo01.Mate;

public class PruebaA {
  @SuppressWarnings("resource")
public static void main(String[] args) {
	  String fileXML = "demo04/demo01.xml";
	BeanFactory bf; //Bean Factory
	bf = new ClassPathXmlApplicationContext(fileXML);
	// Acceso de obj 
	
	IMate bean;
	bean = bf.getBean(Mate2.class);
	
	//Prueba
	System.out.println("7+4 = " + bean.sumar(7, 4));
			
}
}
