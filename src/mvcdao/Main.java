package mvcdao;

import controlador.Controlador;
import modelo.Productos;
import modelo.ProductoDAO;
import vista.Formulario;

public class Main {
  
    public static void main(String[] args) {
        Formulario vistaProd = new Formulario();
        ProductoDAO modeloProd = new ProductoDAO();
        Productos producto = new Productos();
        Controlador controlProd = new Controlador(vistaProd, modeloProd, producto);
        vistaProd.setVisible(true);
    }
    
}
