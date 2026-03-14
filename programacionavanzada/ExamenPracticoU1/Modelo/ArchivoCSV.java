package ModeloExamen;
import java.io.*;
import java.util.ArrayList;

public class ArchivoCSV {

    private static final String RUTA = "productos.csv";

    public static void exportarCSV(ArrayList<Producto> lista) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(RUTA))) {
            for (Producto p : lista) {
                pw.println(
                    p.getId()        + "," +
                    p.getCodigo()    + "," +
                    p.getNombre()    + "," +
                    p.getCategoria() + "," +
                    p.getStock()     + "," +
                    p.getPrecio()    + "," +
                    p.getEstado()
                );
            }
        } catch (IOException e) {
            System.err.println("Error al guardar CSV: " + e.getMessage());
        }
    }

    public static ArrayList<Producto> importarCSV() {
        ArrayList<Producto> lista = new ArrayList<>();
        File archivo = new File(RUTA);
        if (!archivo.exists()) return lista;

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                String[] c = linea.split(",");
                if (c.length < 7) continue;
                try {
                    lista.add(new Producto(
                        Integer.parseInt(c[0].trim()),
                        c[1].trim(),
                        c[2].trim(),
                        c[3].trim(),
                        Integer.parseInt(c[4].trim()),
                        Double.parseDouble(c[5].trim()),
                        c[6].trim()
                    ));
                } catch (NumberFormatException ex) {
                    System.err.println("Línea inválida: " + linea);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer CSV: " + e.getMessage());
        }
        return lista;
    }
}