package demo05;

public class Control {
	
	private Mate mate;
	  private String nombre;
	  
	  
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

}
