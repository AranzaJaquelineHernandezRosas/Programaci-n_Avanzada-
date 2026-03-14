package ModeloExamen;

import java.io.*;
import java.util.ArrayList;

public class Archivos {
    private static final String RUTA = "productos.csv";

    public static ArrayList<Producto> importarCSV() {
        ArrayList<Producto> lista = new ArrayList<>();
        File f = new File(RUTA);
        if (!f.exists()) return lista;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                linea = linea.trim();
                if (linea.isEmpty()) continue;
                String[] parts = linea.split(",");
                if (parts.length < 7) continue;
                try {
                    int id = Integer.parseInt(parts[0].trim());
                    String codigo = parts[1].trim();
                    String nombre = parts[2].trim();
                    String categoria = parts[3].trim();
                    int stock = Integer.parseInt(parts[4].trim());
                    double precio = Double.parseDouble(parts[5].trim());
                    String estado = parts[6].trim();
                    Producto p = new Producto(id, codigo, nombre, categoria, stock, precio, estado);
                    lista.add(p);
                } catch (NumberFormatException ex) {
                    // skip malformed row
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public static boolean exportarCSV(ArrayList<Producto> lista) {
        File f = new File(RUTA);
        try (PrintWriter pw = new PrintWriter(new FileWriter(f, false))) {
            for (Producto p : lista) {
                pw.println(p.getId() + "," + escape(p.getCodigo()) + "," + escape(p.getNombre()) + "," +
                        escape(p.getCategoria()) + "," + p.getStock() + "," + p.getPrecio() + "," + p.getEstado());
            }
            pw.flush();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static String escape(String s) {
        if (s == null) return "";
        return s.replace(',', ' ');
    }
}