package pe.egcc.app.service;

import org.springframework.stereotype.Service;

@Service
public class MateService {
 
	public int sumar(int n1, int n2){
		return (n1 + n2);
	}
	public int importe(int n1, int n2){
		return (n1 * n2);
		
	}
	public double impuesto(int n1, int n2){
		double imp = (0.18*(n1*n2));
		return imp ;
		
	}
	public double total(int n1, int n2){
		double total = ((n1*n2)- (0.18*(n1*n2)));
				return total;
	}
	
	public double promedio(int n1, int n2, int n3, int n4){
		double prom = ((n1+n2+n3+n4)/4);
		return prom;
	}
	
}
