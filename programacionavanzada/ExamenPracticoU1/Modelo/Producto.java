package ModeloExamen;

public class Producto {
	private int    id;
    private String codigo;
    private String nombre;
    private String categoria;
    private int    stock;
    private double precio;
    private String estado;

    public Producto(int id, String codigo, String nombre,
                    String categoria, int stock,
                    double precio, String estado) {
        this.id        = id;
        this.codigo    = codigo;
        this.nombre    = nombre;
        this.categoria = categoria;
        this.stock     = stock;
        this.precio    = precio;
        this.estado    = estado;
    }

    public int    getId()        { return id; }
    public String getCodigo()    { return codigo; }
    public String getNombre()    { return nombre; }
    public String getCategoria() { return categoria; }
    public int    getStock()     { return stock; }
    public double getPrecio()    { return precio; }
    public String getEstado()    { return estado; }

    public void setCodigo(String v)    { this.codigo    = v; }
    public void setNombre(String v)    { this.nombre    = v; }
    public void setCategoria(String v) { this.categoria = v; }
    public void setStock(int v)        { this.stock     = v; }
    public void setPrecio(double v)    { this.precio    = v; }
    public void setEstado(String v)    { this.estado    = v; }

    @Override
    public String toString() {
        return id + " | " + codigo + " | " + nombre +
               " | " + categoria + " | Stock: " + stock +
               " | $" + precio + " | " + estado;
    }
}

