package demo07;

public class Control {
	
	private Mate mate;
	  private String nombre;
	  private String ciudad;
	  
	  
	  public Control(Mate mate, String nombre, String ciudad) {
		super();
		this.mate = mate;
		this.nombre = nombre;
		this.ciudad = ciudad;
	}

	public void setMate(Mate mate) {
	    this.mate = mate;
	  }

	  public void setNombre(String nombre) {
	    this.nombre = nombre;
	  }
	  
	  public int sumar(int n1, int n2){
	    return mate.Sumar(n1, n2);
	  }
	  
	  public String getNombre() {
	    return nombre;
	  }
	  public String getCiudad() {
		    return ciudad;
		  }

}
