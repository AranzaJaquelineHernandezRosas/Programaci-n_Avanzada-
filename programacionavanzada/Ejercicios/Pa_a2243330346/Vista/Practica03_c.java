package Vista;

import Controlador.AppService;
import Controlador.CategoriaController;

public class Practica03_c extends CategoriaInternalFrame {
    public Practica03_c(AppService service) {
        super();
        new CategoriaController(this, service);
    }
}