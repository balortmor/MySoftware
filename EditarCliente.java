package es.studium.MySoftware;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EditarCliente implements WindowListener,ActionListener {

	Frame ventana = new Frame("Edición CLiente");
	Label lblId = new Label("id:");
	Label lblNombre = new Label("Nombre:");
	Label lblCif = new Label("Cif:");
	TextField txtId = new TextField(20);
	TextField txtNombre = new TextField(20);
	TextField txtCif = new TextField(20);
	Button btnAceptar = new Button("Aceptar");
	Button btnCancelar = new Button("Cancelar");

	Dialog dlgEditarCliente = new Dialog(ventana,"Editar",true);
	Label lblMensajeEditarCliente =  new Label("Modificación correcta");


	String sentencia = "";
	Connection connection = null;
	Statement statement = null;
	BaseDatos bd = new BaseDatos();


	public EditarCliente(String elementoEnviado) {
		String[] elemento = elementoEnviado.split("-");
		ventana.setLayout(new FlowLayout());
		ventana.add(lblId);
		txtId.setEditable(false);
		txtId.setText(elemento[0]);
		ventana.add(txtId);
		ventana.add(lblNombre);
		txtNombre.setText(elemento[1]);
		ventana.add(txtNombre);
		ventana.add(lblCif);
		txtCif.setText(elemento[2]);
		ventana.add(txtCif);
		btnAceptar.addActionListener(this);
		btnCancelar.addActionListener(this);
		ventana.add(btnAceptar);
		ventana.add(btnCancelar);

		ventana.setSize(280, 160);
		ventana.setResizable(false);
		ventana.setLocationRelativeTo(null);
		ventana.addWindowListener(this);
		ventana.setVisible(true);
	}


	public void windowActivated(WindowEvent e) {}
	public void windowClosed(WindowEvent e) {}
	public void windowClosing(WindowEvent e) {
		ventana.setVisible(false);
	}
	public void windowDeactivated(WindowEvent e) {}
	public void windowDeiconified(WindowEvent e) {}
	public void windowIconified(WindowEvent e) {}
	public void windowOpened(WindowEvent e) {}

	public void actionPerformed(ActionEvent evento) {
		if(evento.getSource().equals(btnAceptar)) {
			connection = bd.conectar();
			try {
				// Crear una sentencia
				statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
				// Crear un objeto ResultSet para guardar lo obtenido
				// y ejecutar la sentencia SQL
				if (((txtNombre.getText().length()) != 0) &&
						((txtCif.getText().length()) != 0)) {
					sentencia = "INSERT INTO clientes VALUES (null, '" + txtNombre.getText() +
							"', '" + txtCif.getText() + "')";
					statement.executeUpdate(sentencia);
					lblMensajeEditarCliente.setText("Alta de Cliente Correcta");
				} else {
					lblMensajeEditarCliente.setText("Faltan datos");
				}
			} catch (SQLException sqle) {
				lblMensajeEditarCliente.setText("Error en ALTA");
			} finally {
				dlgEditarCliente.setLayout(new FlowLayout());
				dlgEditarCliente.addWindowListener(this);
				dlgEditarCliente.setSize(150, 100);
				dlgEditarCliente.setResizable(false);
				dlgEditarCliente.setLocationRelativeTo(null);
				dlgEditarCliente.add(lblMensajeEditarCliente);
				dlgEditarCliente.setVisible(true);
			}
		}else {

		}

	}

}
