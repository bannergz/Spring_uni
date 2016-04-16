package demo01;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PruebaA {
  @SuppressWarnings("resource")
public static void main(String[] args) {
	  String fileXML = "demo01/demo01.xml";
	BeanFactory bf; //Bean Factory
	bf = new ClassPathXmlApplicationContext(fileXML);
	// Acceso de obj 
	
	Mate bean;
	//bean = bf.getBean(Mate.class);
	bean = bf.getBean("Mate", Mate.class);
	
	//Prueba
	System.out.println("7+4 = " + bean.Sumar(7, 4));

}
}
