package pe.egcc.app.dao.impl;

import java.util.List;

import java.util.Map;
import org.springframework.dao.EmptyResultDataAccessException;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pe.egcc.app.dao.especificacion.EurekaDaoEspec;
import pe.egcc.app.dao.mapper.ClienteMapper;
import pe.egcc.app.domain.Cliente;

@Repository
public class EurekaDaoImpl extends AbstractDao implements EurekaDaoEspec {

	

	@Override
	public double getSaldoCuenta(String cuenta) {
		
		double saldo = 0;
		try {
			String sql = "Select dec_cuensaldo from cuenta where chr_cuencodigo = ?";
			
			Object[] parms = {cuenta};
			
			saldo = jdbctemplate.queryForObject(sql, parms, Double.class);
			
		} catch (Exception e) {
			throw new RuntimeException("Error cuenta no existe 7u7");
		}
		
		return saldo;
	}
	@Override
	public Cliente getCliente(String codigo) {
     Cliente bean = null;
		try {
			String sql = "select chr_cliecodigo, vch_cliepaterno, "
			          + "vch_cliematerno, vch_clienombre, "
			          + "chr_cliedni, vch_clieciudad, vch_cliedireccion, "
			          + "vch_clietelefono, vch_clieemail "
			          + "from cliente "
			          + "where chr_cliecodigo = ?";
			
			Object[] parms = {codigo};
			
			bean = jdbctemplate.queryForObject(sql, parms, new ClienteMapper());
			
		} catch (Exception e) {
			throw new RuntimeException("Error cuenta no existe 7u7");
		}
		return bean;
	}
	@Override
	public Cliente getCliente2(String codigo) {  //CLIENTE 2 7u7
		Cliente bean = null;
		try {
			String sql = "select chr_cliecodigo codigo, vch_cliepaterno paterno, "
			          + "vch_cliematerno materno, vch_clienombre nombre, "
			          + "chr_cliedni dni, vch_clieciudad ciudad, vch_cliedireccion direccion, "
			          + "vch_clietelefono telefono, vch_clieemail email "
			          + "from cliente "
			          + "where chr_cliecodigo = ?";
			
			Object[] parms = {codigo};
			
			bean = jdbctemplate.queryForObject(sql, parms, new BeanPropertyRowMapper<Cliente>(Cliente.class)); //Usando el beanproperty ya no hay necesidad ed crear un mapper
			
		} catch (Exception e) {
			throw new RuntimeException("Error cuenta no existe 7u7");
		}
		return bean;
	}
	@Override
	
		public List<Map<String, Object>> getMovimientos(String cuenta) {
		    List<Map<String, Object>> lista = null;
		    try {
		    	 String sql = "select cuencodigo, monecodigo, monenombre, "
		 		        + "cuensaldo, cuenestado, movinumero, movifecha, "
		 		        + "tipocodigo, tiponombre, tipoaccion, moviimporte "
		 		        + "from v_movimiento where cuencodigo = ? ";
		 		    lista = jdbctemplate.queryForList(sql, cuenta);
				
			} catch (Exception e) {
				throw new RuntimeException("Error cuenta no existe 7u7");
			}
		   
		    
		    return lista;
	}
	
	@Transactional(rollbackFor=Exception.class,
			isolation=Isolation.DEFAULT )//sirve para determinar donde participara la transaccion
	@Override
	public void procDeposito(String cuenta, double importe, String codEmp) {
		String sql;
		Map<String, Object> map;
		double saldo;
		int cont;
		//Paso 1: Leer datos de la cuenta
		sql = "select dec_cuensaldo, int_cuencontmov  "
		        + "from cuenta where chr_cuencodigo = ? "
		        + "for update ";
		map = jdbctemplate.queryForMap(sql, cuenta);
		saldo = Double.parseDouble(map.get("dec_cuensaldo").toString());
		cont = Integer.parseInt(map.get("int_cuencontmov").toString());
		
		// Retardo
	    try{
	      Thread.currentThread().sleep(1000);
	    } catch(Exception e){}
	    
		// Paso 2: Actualizar cuenta
	    saldo += importe;
	    cont++;
	    sql = "update cuenta set dec_cuensaldo = ?, "
	        + "int_cuencontmov = ? "
	        + "where chr_cuencodigo = ? ";
	    jdbctemplate.update(sql, saldo, cont, cuenta);
	    
	    // Paso 3: Registrar movimiento
	    sql = "insert into movimiento(chr_cuencodigo,int_movinumero,"
	        + "dtt_movifecha,chr_emplcodigo,chr_tipocodigo,dec_moviimporte) "
	        + "values(?,?,SYSDATE,?,'003',?)";
	    jdbctemplate.update(sql,cuenta,cont,codEmp,importe);
		
	}
	
	@Transactional(rollbackFor=Exception.class,
			isolation=Isolation.DEFAULT )//sirve para determinar donde participara la transaccion
	@Override
	public void procDeposito2(String cuenta, double importe, String codEmp) {
		String sql;
		int cont;
		//Paso 1: Leer datos de la cuenta

	    sql = "update cuenta set dec_cuensaldo = dec_cuensaldo + ?, "
	        + "int_cuencontmov = int_cuencontmov + 1 "
	        + "where chr_cuencodigo = ? ";
	    jdbctemplate.update(sql, importe, cuenta);
	
		// Retardo
	    try{
	      Thread.currentThread().sleep(1000);
	    } catch(Exception e){}
	    
		// Paso 2: Actualizar cuenta
       sql = "select int_cuencontmov from cuenta where chr_cuencodigo = ?";
		
		cont = jdbctemplate.queryForInt(sql, cuenta);
	  
	    
	    // Paso 3: Registrar movimiento
	    sql = "insert into movimiento(chr_cuencodigo,int_movinumero,"
	        + "dtt_movifecha,chr_emplcodigo,chr_tipocodigo,dec_moviimporte) "
	        + "values(?,?,SYSDATE,?,'003',?)";
	    jdbctemplate.update(sql,cuenta,cont,codEmp,importe);
		
	}
	@Override
	public void procRetiro(String cuenta, double importe, String codEmp, String clave) {
		 String sql = "{call usp_egcc_retiro( ?, ?, ?, ? )}";
		    jdbctemplate.update(sql, cuenta, importe, codEmp, clave);
		
	}

	

}
