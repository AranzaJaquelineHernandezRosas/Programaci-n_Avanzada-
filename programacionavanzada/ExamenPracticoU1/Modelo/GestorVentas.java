package ModeloExamen;

import java.util.ArrayList;

public class GestorVentas {
    private ArrayList<Venta> lista = new ArrayList<>();

    public ArrayList<Venta> getLista() { return lista; }

    public void agregarVenta(Venta v) { lista.add(v); }

    public void agregarVentas(ArrayList<Venta> ventas) { lista.addAll(ventas); }

    public String obtenerUltimoId() {
        if (lista.isEmpty()) return "000";
        return lista.get(lista.size()-1).getIdTicket();
    }

    public String generarSiguienteId() {
        String ultimo = obtenerUltimoId();
        try {
            int num = Integer.parseInt(ultimo);
            num++;
            if (num < 10) return "00" + num;
            else if (num < 100) return "0" + num;
            else return String.valueOf(num);
        } catch (NumberFormatException e) {
            return "001";
        }
    }
}
