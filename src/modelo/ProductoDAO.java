package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ProductoDAO extends Conexion {

    private String sql;
    private PreparedStatement ejecutar;
    private ResultSet resultado;
    private String respuesta;

    public String registrarProducto(Productos producto) {
        respuesta = null;
        try {
            Conectar();
            sql = "insert into productos (nombre, pais_origen, proveedor, cantidad) values (?,?,?,?)";
            ejecutar = this.getMiConexion().prepareStatement(sql);
            ejecutar.setString(1, producto.getNombre());
            ejecutar.setString(2, producto.getPais_origen());
            ejecutar.setString(3, producto.getProveedor());
            ejecutar.setInt(4, producto.getCantidad());
            ejecutar.executeUpdate();
            respuesta = "Producto registrado correctamente!!!";
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            CerrarConexion();
        }
        return respuesta;
    }

    public String modificarProducto(Productos producto) {
        respuesta = null;
        try {
            Conectar();
            sql = "update productos set nombre=?, pais_origen=?, proveedor=?, cantidad=? where codigo=?";
            ejecutar = getMiConexion().prepareStatement(sql);
            ejecutar.setString(1, producto.getNombre());
            ejecutar.setString(2, producto.getPais_origen());
            ejecutar.setString(3, producto.getProveedor());
            ejecutar.setInt(4, producto.getCantidad());
            ejecutar.executeUpdate();
            respuesta = "Producto modificado correctamente";

        } catch (Exception e) {
            System.out.println(e.getMessage());
            respuesta = "No se puede modificar el registro debido a un error";
        } finally {
            CerrarConexion();
        }
        return respuesta;
    }
    
    public String eliminarProducto (int codigo){
        try {
            Conectar();
            sql = "delete from productos where codigo=?";
            ejecutar = getMiConexion().prepareStatement(sql);
            ejecutar.setInt(1, codigo);
            ejecutar.executeUpdate();
            respuesta = "Registro Eliminado";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            respuesta = "Error, se no se puede eliminar el registro!!!";
        }finally{
            CerrarConexion();
        }
        return respuesta;
    }
    
    public Productos mostrarProducto(int codigo){
        Productos producto = new Productos();
        try {
            Conectar();
            sql = "select * from productos where codigo=?";
            ejecutar = getMiConexion().prepareStatement(sql);
            ejecutar.setInt(1, codigo);
            resultado = ejecutar.executeQuery();
            if (resultado.next()) {
                producto.setCodigo(resultado.getInt("codigo"));
                producto.setNombre(resultado.getString("nombre"));
                producto.setPais_origen (resultado.getString("pais_origen"));
                producto.setProveedor(resultado.getString("proveedor"));
                producto.setCantidad(resultado.getInt("cantidad"));
                
                
            }  
        } catch (Exception e) {
            System.out.println(e.getMessage());
           
        }finally{
            CerrarConexion();
        }
    
       return producto;
    }
    
    public ArrayList<Productos> listaProductos(){
    ArrayList<Productos> lista = null;
        try {
            Conectar();
            sql = "select * from productos";
            ejecutar = getMiConexion().prepareStatement(sql);
            resultado = ejecutar.executeQuery();
            lista = new ArrayList();
            while (resultado.next()) {
                Productos prod = new Productos();
                prod.setCodigo(resultado.getInt("codigo"));
                prod.setNombre(resultado.getString("nombre"));
                prod.setPais_origen(resultado.getString("pais_origen"));
                prod.setProveedor(resultado.getString("proveedor"));
                prod.setCantidad(resultado.getInt("cantidad"));
                lista.add(prod);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally{
            CerrarConexion();
        }
    
    return lista;
    }

}
