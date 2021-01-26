
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    private Connection miConexion;
    private static final String URL = "jdbc:mysql://localhost:3306/tienda";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public Connection getMiConexion() {
        return miConexion;
    }

    public void setMiConexion(Connection miConexion) {
        this.miConexion = miConexion;
    }
    
    public void Conectar(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            miConexion = (Connection) DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
                System.out.println("Error de Conexion" + e.getMessage());
        }
    }
    public void CerrarConexion(){
        try {
            if (miConexion!=null) {
                if (miConexion.isClosed()==false) {
                    miConexion.close();
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    
}
