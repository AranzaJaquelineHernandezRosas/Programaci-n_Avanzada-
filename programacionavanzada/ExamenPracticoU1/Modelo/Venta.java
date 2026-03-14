package ModeloExamen;

public class Venta {
	 private String idTicket;
	    private String codigo;
	    private String nombre;
	    private double precio;
	    private int cantidad;

	    public Venta(String idTicket, String codigo, String nombre, double precio, int cantidad) {
	        this.idTicket = idTicket;
	        this.codigo = codigo;
	        this.nombre = nombre;
	        this.precio = precio;
	        this.cantidad = cantidad;
	    }

	    public String getIdTicket() { return idTicket; }
	    public String getCodigo() { return codigo; }
	    public String getNombre() { return nombre; }
	    public double getPrecio() { return precio; }
	    public int getCantidad() { return cantidad; }
	}
