package HolaMundo;

public class HolaMundoModelo {
	private String mensaje;

    public HolaMundoModelo() {
        this.mensaje = "¡Hola Mundo con Swing!";
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String generarSaludo(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            return mensaje;
        }
        return "¡Hola " + nombre + "!";
    }
}
