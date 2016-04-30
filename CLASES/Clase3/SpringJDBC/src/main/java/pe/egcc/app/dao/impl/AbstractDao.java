package pe.egcc.app.dao.impl;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public abstract class AbstractDao {
	
	
	protected JdbcTemplate jdbctemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource){
		
		jdbctemplate = new JdbcTemplate(dataSource);
	}
}
