package PA_a2243330346_practica1;
import java.util.ArrayList;

public class ListaInsumos {
    private ArrayList<Insumos> insumos; 

    public ListaInsumos() {
        this.insumos = new ArrayList<>(); 
    }

    public boolean agregarInsumo(Insumos nodo) {
        for (Insumos i : insumos) {
            if (i.getId().equals(nodo.getId())) return false;
        }
        insumos.add(nodo);
        return true;
    }

    public Object[] idinsumos() {
        if (insumos == null || insumos.isEmpty()) {
            return new Object[]{};
        }
        ArrayList<String> ids = new ArrayList<>();
        for (Insumos i : insumos) {
            ids.add((String) i.getId());
        }
        return ids.toArray();
    }

    public boolean eliminarInsumoPorId(String id) {
        return insumos.removeIf(i -> i.getId().equals(id));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Insumos i : insumos) {
            sb.append(i.toString());
        }
        return sb.toString();
    }

	public String buscarInsumos(String id) {
		// TODO Auto-generated method stub
		return null;
	}
}