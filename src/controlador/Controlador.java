package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Productos;
import modelo.ProductoDAO;
import vista.Formulario;

public class Controlador implements ActionListener, MouseListener {

    Formulario vistaProducto = new Formulario();
    ProductoDAO modeloProducto = new ProductoDAO();
    Productos producto = new Productos();

    public Controlador(Formulario vistaProducto, ProductoDAO modeloCliente, Productos producto) {
        this.vistaProducto = vistaProducto;
        this.modeloProducto = modeloCliente;
        this.producto = producto;
        this.vistaProducto.btnGuardar.addActionListener(this);
        this.vistaProducto.btnModificar.addActionListener(this);
        this.vistaProducto.btnEliminar.addActionListener(this);
        this.vistaProducto.btnMostrar.addActionListener(this);
        this.vistaProducto.miTabla.addMouseListener(this);
        LlenarTabla(vistaProducto.miTabla);
    }

    public void insertProducto() {
        producto.setNombre(vistaProducto.txtNombre.getText());
        producto.setPais_origen(vistaProducto.txtOrigen.getText());
        producto.setProveedor(vistaProducto.txtProveedor.getText());
        producto.setCantidad(Integer.parseInt(vistaProducto.txtCantidad.getText()));
        String respuesta = modeloProducto.registrarProducto(producto);
        if (respuesta != null) {
            JOptionPane.showMessageDialog(null, respuesta);
        }

    }

    public void modificarProducto() {
        producto.setCodigo(Integer.parseInt(vistaProducto.txtCodigo.getText()));
        producto.setNombre(vistaProducto.txtNombre.getText());
        producto.setPais_origen(vistaProducto.txtOrigen.getText());
        producto.setProveedor(vistaProducto.txtProveedor.getText());
        producto.setCantidad(Integer.parseInt(vistaProducto.txtCantidad.getText()));
        String respuesta = modeloProducto.modificarProducto(producto);
        if (respuesta != null) {
            JOptionPane.showMessageDialog(null, respuesta);
        } else {
            JOptionPane.showMessageDialog(null, respuesta);
        }
    }

    public void eliminarProducto() {
        producto.setCodigo(Integer.parseInt(vistaProducto.txtCodigo.getText()));
        String mensaje = modeloProducto.eliminarProducto(producto.getCodigo());
        JOptionPane.showMessageDialog(vistaProducto, mensaje);
    }

    public void mostrarProducto() {
        int valor = Integer.parseInt(vistaProducto.txtCodigo.getText());
        producto = modeloProducto.mostrarProducto(valor);
        vistaProducto.txtNombre.setText(producto.getNombre());
        vistaProducto.txtOrigen.setText(producto.getPais_origen());
        vistaProducto.txtProveedor.setText(producto.getProveedor());
        vistaProducto.txtCantidad.setText(String.valueOf(producto.getCantidad()));
        if (producto != null) {
            vistaProducto.txtCantidad.setText("");
            JOptionPane.showMessageDialog(null, "No se encontro registro!!!");
        }
    }

    public void LlenarTabla(JTable tablaDatos) {
        DefaultTableModel modeloTabla = new DefaultTableModel();
        tablaDatos.setModel(modeloTabla);
        modeloTabla.addColumn("Codigo");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Pais de origen");
        modeloTabla.addColumn("Proveedor");
        modeloTabla.addColumn("Cantidad");
        Object[] columna = new Object[5];
        int numRegistros = modeloProducto.listaProductos().size();
        for (int i = 0; i < numRegistros; i++) {
            columna[0] = modeloProducto.listaProductos().get(i).getCodigo();
            columna[1] = modeloProducto.listaProductos().get(i).getNombre();
            columna[2] = modeloProducto.listaProductos().get(i).getPais_origen();
            columna[3] = modeloProducto.listaProductos().get(i).getProveedor();
            columna[4] = modeloProducto.listaProductos().get(i).getCantidad();
            modeloTabla.addRow(columna);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaProducto.btnGuardar) {
            insertProducto();
        }
        if (e.getSource() == vistaProducto.btnModificar) {
            modificarProducto();
        }
        if (e.getSource() == vistaProducto.btnEliminar) {
            eliminarProducto();
        }
        if (e.getSource() == vistaProducto.btnMostrar) {
            mostrarProducto();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == vistaProducto.miTabla) {
            vistaProducto.txtCodigo.setText(vistaProducto.miTabla.getValueAt(vistaProducto.miTabla.getSelectedRow(), 0).toString());
            vistaProducto.txtNombre.setText(vistaProducto.miTabla.getValueAt(vistaProducto.miTabla.getSelectedRow(), 1).toString());
            vistaProducto.txtOrigen.setText(vistaProducto.miTabla.getValueAt(vistaProducto.miTabla.getSelectedRow(), 2).toString());
            vistaProducto.txtProveedor.setText(vistaProducto.miTabla.getValueAt(vistaProducto.miTabla.getSelectedRow(), 3).toString());
            vistaProducto.txtCantidad.setText(vistaProducto.miTabla.getValueAt(vistaProducto.miTabla.getSelectedRow(), 4).toString());
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
