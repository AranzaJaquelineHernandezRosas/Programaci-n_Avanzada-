package ModeloExamen;
import java.util.ArrayList;
import java.util.Iterator;

public class GestorProductos {

    private ArrayList<Producto> lista = new ArrayList<>();

    public ArrayList<Producto> getLista() { return lista; }

    public boolean existe(int id) {
        Iterator<Producto> it = lista.iterator();
        while (it.hasNext()) {
            if (it.next().getId() == id) return true;
        }
        return false;
    }

    public void insertar(Producto p) {
        lista.add(p);
    }

    public Producto buscar(int id) {
        Iterator<Producto> it = lista.iterator();
        while (it.hasNext()) {
            Producto p = it.next();
            if (p.getId() == id) return p;
        }
        return null;
    }

    public boolean actualizar(Producto nuevo) {
        Iterator<Producto> it = lista.iterator();
        while (it.hasNext()) {
            Producto p = it.next();
            if (p.getId() == nuevo.getId()) {
                p.setCodigo(nuevo.getCodigo());
                p.setNombre(nuevo.getNombre());
                p.setCategoria(nuevo.getCategoria());
                p.setStock(nuevo.getStock());
                p.setPrecio(nuevo.getPrecio());
                p.setEstado(nuevo.getEstado());
                return true;
            }
        }
        return false;
    }

    public boolean eliminar(int id) {
        Iterator<Producto> it = lista.iterator();
        while (it.hasNext()) {
            if (it.next().getId() == id) {
                it.remove();
                return true;
            }
        }
        return false;
    }

    public Producto buscarPorCodigo(String codigo) {
        for (Producto p : lista) {
            if (p.getCodigo() != null && p.getCodigo().equals(codigo)) return p;
        }
        return null;
    }
}