package pe.egcc.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Map;



import org.springframework.stereotype.Service;

import pe.egcc.app.dao.especificacion.EurekaDaoEspec;
import pe.egcc.app.domain.Cliente;

@Service
public class EurekaService {
	@Autowired
	private EurekaDaoEspec eurekaDao;
	
     public double getSaldoCuenta(String cuenta){
       return eurekaDao.getSaldoCuenta(cuenta);	 
     }

     public Cliente getCliente(String codigo){
       return eurekaDao.getCliente2(codigo);	 //PARA HACER LA PRUEBA DEL ROWMAPPING AUTOGENERADO LE DI A getCliente2
     }
     
     public List<Map<String, Object>> getMovimientos(String cuenta){
    	    return eurekaDao.getMovimientos(cuenta);
    	  }
     public void procDeposito(String cuenta, double importe, String codEmp) {
    	    eurekaDao.procDeposito2(cuenta, importe, codEmp);  //PARA TESTEAR USE PROCDEP2
    	  }
     public void procRetiro(String cuenta, double importe, String codEmp, String clave){
    	    eurekaDao.procRetiro(cuenta, importe, codEmp, clave);
    	  }
    
}
