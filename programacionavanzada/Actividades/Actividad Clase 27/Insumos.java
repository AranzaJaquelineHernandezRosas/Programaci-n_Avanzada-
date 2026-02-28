package PA_a2243330346_practica1;

public class Insumos {

	public class Insumo {
	    private String idProducto;
	    private String producto;

	    public Insumo(String idProducto, String producto) {
	        this.idProducto = idProducto;
	        this.producto = producto;
	    }

	    public String getIdProducto() {
	        return idProducto;
	    }

	    public String getProducto() {
	        return producto;
	    }

	    @Override
	    public String toString() {
	        return "ID: " + idProducto + " - Producto: " + producto;
	    }
	}

	public Insumos(String id, String insumo, String idcategoria) {
		// TODO Auto-generated constructor stub
	}

	public String getIdProducto() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getId() {
		// TODO Auto-generated method stub
		return null;
	}
}
