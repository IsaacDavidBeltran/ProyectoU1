package gui;

import java.awt.Insets;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.JFormattedTextField;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import Components.Banco;
import Components.Registros;
import Components.Servicios;
import Utility.Archivo;
import Utility.JPasswordFieldShowHide;

public class MainFrame extends JFrame implements ActionListener, KeyListener {

	private static final long serialVersionUID = 1L;

	private JPanel datos, cabecera;
	private JComboBox<String> cmbTipoCuenta;
	private JTextField txtNombreR, txtUsuario, txtCorreo, txtUrl;
	private JTextField txtCuentaB, txtTarjeta, txtCVE, txtVencimiento;
	private JTextField txtDomicilio, txtDatoEx1, txtDatoEx2;
	private JPasswordFieldShowHide txtContrasena, txtNIP;
	private JComboBox<String> cmbTipoTarjeta;
	private JLabel lbEncabezado, lbTipoCuenta, lbNombreR, lbUsuario, lbCorreo, lbContrasena, lbURL;
	private JButton btnAltas, btnBajas, btnCambios, btnConsultas;
	private JLabel lbCuentaBancaria, lbTarjeta, lbTipoTarjeta, lbNIP, lbCVE, lbVencimiento;
	private JLabel lbDomicilio, lbDatoEx1, lbDatoEx2;
	private JLabel lbFiltro;
	private JComboBox<String> cmbFiltro;
	private SimpleDateFormat formatoFecha = new SimpleDateFormat("MM/YY");
	private ArrayList<Registros> array = new ArrayList<Registros>();
	private String[] opciones = { "Seleccionar", "Bancaria", "Servicios", "Pagina Web", "App Movil", "App Desktop" };

	private boolean isConsultas = false;
	Archivo archivo = new Archivo();

	// COSAS POR ARREGLAR //
	// cmbCombo1: siempre muestra "Seleccionar" aunque se escoja otra opcion.

	public MainFrame() {
		setTitle("Proyecto Unidad 1 y 2");
		setIconImage(new ImageIcon("src\\img\\logoTec.png").getImage());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// setSize(700, 700);
		setMinimumSize(new Dimension(500, 500));
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		InitComponents();
	}

	private void InitComponents() {
		// LEER DATOS DEL ARCHIVO
		array = archivo.leerArchivo();

		// ZONA CENTRAL
		datos = new JPanel();
		menu();
		add(datos, BorderLayout.CENTER);

		// ZONA SUR
		cabecera = new JPanel();
		opciones();
		add(cabecera, BorderLayout.SOUTH);

	}

	private void opciones() {
		cabecera.setLayout(new FlowLayout());
		btnAltas = new JButton("Altas");
		btnAltas.setPreferredSize(new Dimension(80, 30));
		btnAltas.addActionListener(this);
		btnAltas.setEnabled(false);
		cabecera.add(btnAltas);

		btnBajas = new JButton("Bajas");
		btnBajas.setPreferredSize(new Dimension(80, 30));
		btnBajas.addActionListener(this);
		btnBajas.setEnabled(false);
		cabecera.add(btnBajas);

		btnCambios = new JButton("Cambios");
		btnCambios.setPreferredSize(new Dimension(90, 30));
		btnCambios.addActionListener(this);
		btnCambios.setEnabled(false);
		cabecera.add(btnCambios);

		btnConsultas = new JButton("Consultas");
		btnConsultas.setPreferredSize(new Dimension(100, 30));
		btnConsultas.addActionListener(this);
		cabecera.add(btnConsultas);
	}

	public void menu() {
		datos.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		c.gridheight = 1;
		c.weightx = 0.0;
		c.weighty = 0.0;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(0, 5, 0, 0);
		c.ipadx = 0;
		c.ipady = 2;
		lbEncabezado = new JLabel("REGISTRO DE USUARIOS");
		datos.add(lbEncabezado, c);

		// Tipo de cuenta
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weightx = 0.0;
		c.weighty = 0.0;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.LINE_END;
		c.insets = new Insets(5, 5, 0, 5);
		c.ipadx = 0;
		c.ipady = 7;
		lbTipoCuenta = new JLabel("Tipo de cuenta: ");
		datos.add(lbTipoCuenta, c);

		c.gridx = 1;
		c.gridy = 1;
		c.anchor = GridBagConstraints.LINE_START;
		cmbTipoCuenta = new JComboBox<>(opciones);
		cmbTipoCuenta.addActionListener(this);
		datos.add(cmbTipoCuenta, c);

		// Nombre del registro
		c.gridx = 0;
		c.gridy = 2;
		c.anchor = GridBagConstraints.LINE_END;
		lbNombreR = new JLabel("Nombre del registro: ");
		datos.add(lbNombreR, c);

		c.gridx = 1;
		c.gridy = 2;
		c.anchor = GridBagConstraints.LINE_START;
		txtNombreR = new JTextField();
		txtNombreR.setPreferredSize(new Dimension(150, 15));
		txtNombreR.addKeyListener(this);
		datos.add(txtNombreR, c);

		// Usuario
		c.gridx = 0;
		c.gridy = 3;
		c.anchor = GridBagConstraints.LINE_END;
		lbUsuario = new JLabel("Usuario: ");
		datos.add(lbUsuario, c);

		c.gridx = 1;
		c.gridy = 3;
		c.anchor = GridBagConstraints.LINE_START;
		txtUsuario = new JTextField();
		txtUsuario.setPreferredSize(new Dimension(150, 15));
		datos.add(txtUsuario, c);

		// Correo
		c.gridx = 0;
		c.gridy = 4;
		c.anchor = GridBagConstraints.LINE_END;
		lbCorreo = new JLabel("Correo: ");
		datos.add(lbCorreo, c);

		c.gridx = 1;
		c.gridy = 4;
		c.anchor = GridBagConstraints.LINE_START;
		txtCorreo = new JTextField();
		txtCorreo.setPreferredSize(new Dimension(150, 15));
		datos.add(txtCorreo, c);

		// Contraseña
		c.gridx = 0;
		c.gridy = 5;
		c.anchor = GridBagConstraints.LINE_END;
		lbContrasena = new JLabel("Contraseña: ");
		datos.add(lbContrasena, c);

		c.gridx = 1;
		c.gridy = 5;
		c.anchor = GridBagConstraints.LINE_START;
		txtContrasena = new JPasswordFieldShowHide(20);
		txtContrasena.setPreferredSize(new Dimension(150, 15));
		datos.add(txtContrasena, c);

		// URL
		c.gridx = 0;
		c.gridy = 6;
		c.anchor = GridBagConstraints.LINE_END;
		lbURL = new JLabel("URL: ");
		datos.add(lbURL, c);

		c.gridx = 1;
		c.gridy = 6;
		c.anchor = GridBagConstraints.LINE_START;
		txtUrl = new JTextField();
		txtUrl.setPreferredSize(new Dimension(150, 15));
		datos.add(txtUrl, c);

		// Campos de Cuentas Bancarias
		// Cuenta Bancaria
		c.gridx = 0;
		c.gridy = 7;
		c.anchor = GridBagConstraints.LINE_END;
		lbCuentaBancaria = new JLabel("Cuenta bancaria: ");
		datos.add(lbCuentaBancaria, c);

		c.gridx = 1;
		c.gridy = 7;
		c.anchor = GridBagConstraints.LINE_START;
		txtCuentaB = new JTextField();
		txtCuentaB.setPreferredSize(new Dimension(150, 15));
		txtCuentaB.addKeyListener(this);
		datos.add(txtCuentaB, c);

		// Tarjeta
		c.gridx = 0;
		c.gridy = 8;
		c.anchor = GridBagConstraints.LINE_END;
		lbTarjeta = new JLabel("Tarjeta: ");
		datos.add(lbTarjeta, c);

		c.gridx = 1;
		c.gridy = 8;
		c.anchor = GridBagConstraints.LINE_START;
		txtTarjeta = new JTextField();
		txtTarjeta.setPreferredSize(new Dimension(150, 15));
		txtTarjeta.addKeyListener(this);
		datos.add(txtTarjeta, c);

		// Tipo de tarjeta
		c.gridx = 0;
		c.gridy = 9;
		c.anchor = GridBagConstraints.LINE_END;
		lbTipoTarjeta = new JLabel("Tipo: ");
		datos.add(lbTipoTarjeta, c);

		c.gridx = 1;
		c.gridy = 9;
		c.anchor = GridBagConstraints.LINE_START;
		String[] tipos = { "Débito", "Crédito", "Recompensas", "Departamental" };
		cmbTipoTarjeta = new JComboBox<>(tipos);
		datos.add(cmbTipoTarjeta, c);

		// NIP
		c.gridx = 0;
		c.gridy = 10;
		c.anchor = GridBagConstraints.LINE_END;
		lbNIP = new JLabel("NIP: ");
		datos.add(lbNIP, c);

		c.gridx = 1;
		c.gridy = 10;
		c.anchor = GridBagConstraints.LINE_START;
		txtNIP = new JPasswordFieldShowHide(10);
		txtNIP.setPreferredSize(new Dimension(150, 15));
		txtNIP.addKeyListener(this);
		datos.add(txtNIP, c);

		// CVE
		c.gridx = 0;
		c.gridy = 11;
		c.anchor = GridBagConstraints.LINE_END;
		lbCVE = new JLabel("CVE: ");
		datos.add(lbCVE, c);

		c.gridx = 1;
		c.gridy = 11;
		c.anchor = GridBagConstraints.LINE_START;
		txtCVE = new JTextField();
		txtCVE.setPreferredSize(new Dimension(150, 15));
		txtCVE.addKeyListener(this);
		datos.add(txtCVE, c);

		// Vencimiento
		c.gridx = 0;
		c.gridy = 12;
		c.anchor = GridBagConstraints.LINE_END;
		lbVencimiento = new JLabel("Vencimiento (MM/YY): ");
		datos.add(lbVencimiento, c);

		c.gridx = 1;
		c.gridy = 12;
		c.anchor = GridBagConstraints.LINE_START;

		txtVencimiento = new JFormattedTextField(formatoFecha);
		txtVencimiento.addKeyListener(this);

		// Ocultar los campos de Cuentas Bancarias
		txtVencimiento.setPreferredSize(new Dimension(150, 15));
		datos.add(txtVencimiento, c);
		txtCuentaB.setVisible(false);
		txtTarjeta.setVisible(false);
		cmbTipoTarjeta.setVisible(false);
		txtNIP.setVisible(false);
		txtCVE.setVisible(false);
		txtVencimiento.setVisible(false);
		lbCuentaBancaria.setVisible(false);
		lbTarjeta.setVisible(false);
		lbTipoTarjeta.setVisible(false);
		lbNIP.setVisible(false);
		lbCVE.setVisible(false);
		lbVencimiento.setVisible(false);

		// Campos de cuentas de servicio
		// Domicilio
		c.gridx = 0;
		c.gridy = 7;
		c.anchor = GridBagConstraints.LINE_END;
		lbDomicilio = new JLabel("Domicilio: ");
		datos.add(lbDomicilio, c);

		c.gridx = 1;
		c.gridy = 7;
		c.anchor = GridBagConstraints.LINE_START;
		txtDomicilio = new JTextField();
		txtDomicilio.setPreferredSize(new Dimension(150, 15));
		datos.add(txtDomicilio, c);

		// Dato extra 1
		c.gridx = 0;
		c.gridy = 8;
		c.anchor = GridBagConstraints.LINE_END;
		lbDatoEx1 = new JLabel("Dato extra 1: ");
		datos.add(lbDatoEx1, c);

		c.gridx = 1;
		c.gridy = 8;
		c.anchor = GridBagConstraints.LINE_START;
		txtDatoEx1 = new JTextField();
		txtDatoEx1.setPreferredSize(new Dimension(150, 15));
		datos.add(txtDatoEx1, c);

		// Dato extra 2
		c.gridx = 0;
		c.gridy = 9;
		c.anchor = GridBagConstraints.LINE_END;
		lbDatoEx2 = new JLabel("Dato extra 2: ");
		datos.add(lbDatoEx2, c);

		c.gridx = 1;
		c.gridy = 9;
		c.anchor = GridBagConstraints.LINE_START;
		txtDatoEx2 = new JTextField();
		txtDatoEx2.setPreferredSize(new Dimension(150, 15));
		datos.add(txtDatoEx2, c);

		// Ocultar los campos de las cuentas de servicio
		lbDomicilio.setVisible(false);
		lbDatoEx1.setVisible(false);
		lbDatoEx2.setVisible(false);
		txtDomicilio.setVisible(false);
		txtDatoEx1.setVisible(false);
		txtDatoEx2.setVisible(false);

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// Validar datos del txtNIP
		// Formato: (0000)
		if (e.getSource() == txtNIP) {
			char c = e.getKeyChar();
			char[] nipChars = txtNIP.getPassword();
			String nip = new String(nipChars);
			if (Character.isDigit(c)) {
				if (nip.length() > 3) {
					e.consume();
				}

			} else {
				e.consume();
			}
		}

		// Validar datos del txtCVE
		// Formato: (000)
		if (e.getSource() == txtCVE) {
			char c = e.getKeyChar();
			if (Character.isDigit(c)) {
				if (txtCVE.getText().length() > 2) {
					e.consume();
				}
			} else {
				e.consume();
			}
		}

		// Cuenta Bancaria
		if (e.getSource() == txtCuentaB) {
			char c = e.getKeyChar();
			if (Character.isDigit(c)) {
				if (txtCuentaB.getText().length() > 17) {
					e.consume();
				}
			} else {
				e.consume();
			}
		}

		// Validar datos del txtVencimiento
		// Formato: (MM/YY)
		if (e.getSource() == txtVencimiento) {
			char c = e.getKeyChar();
			if (Character.isDigit(c)) {
				if (txtVencimiento.getText().length() == 2) {
					txtVencimiento.setText(txtVencimiento.getText() + "/");
				}
				if (txtVencimiento.getText().length() > 4) {
					e.consume();
				}
			} else {
				e.consume();
			}
		}

		// Validar datos del txtTarjeta
		// Formato: (0000-0000-0000-0000)
		if (e.getSource() == txtTarjeta) {
			char c = e.getKeyChar();
			if (Character.isDigit(c)) {
				if (txtTarjeta.getText().length() % 5 == 4 && txtTarjeta.getText().length() <= 18) {
					txtTarjeta.setText(txtTarjeta.getText() + '-');
				}
				if (txtTarjeta.getText().length() > 18) {
					e.consume();
				}
			} else {
				e.consume();
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String seleccion = (String) cmbTipoCuenta.getSelectedItem();

		// Bloquear botones de Altas y Bajas
		if (cmbTipoCuenta.getSelectedIndex() == 0) {
			btnAltas.setEnabled(false);
			btnBajas.setEnabled(false);
		} else {
			btnAltas.setEnabled(true);
		}

		// Cuentas Bancarias
		if (e.getSource() == cmbTipoCuenta) {
			if ("Bancaria".equals(seleccion)) {
				txtCuentaB.setVisible(true);
				txtTarjeta.setVisible(true);
				cmbTipoTarjeta.setVisible(true);
				txtNIP.setVisible(true);
				txtCVE.setVisible(true);
				txtVencimiento.setVisible(true);
				lbCuentaBancaria.setVisible(true);
				lbTarjeta.setVisible(true);
				lbTipoTarjeta.setVisible(true);
				lbNIP.setVisible(true);
				lbCVE.setVisible(true);
				lbVencimiento.setVisible(true);
			} else {
				txtCuentaB.setVisible(false);
				txtTarjeta.setVisible(false);
				cmbTipoTarjeta.setVisible(false);
				txtNIP.setVisible(false);
				txtCVE.setVisible(false);
				txtVencimiento.setVisible(false);
				lbCuentaBancaria.setVisible(false);
				lbTarjeta.setVisible(false);
				lbTipoTarjeta.setVisible(false);
				lbNIP.setVisible(false);
				lbCVE.setVisible(false);
				lbVencimiento.setVisible(false);
			}
		}

		// Cuentas de servicios
		if (e.getSource() == cmbTipoCuenta) {
			if ("Servicios".equals(seleccion)) {
				lbDomicilio.setVisible(true);
				lbDatoEx1.setVisible(true);
				lbDatoEx2.setVisible(true);
				txtDomicilio.setVisible(true);
				txtDatoEx1.setVisible(true);
				txtDatoEx2.setVisible(true);
			} else {
				lbDomicilio.setVisible(false);
				lbDatoEx1.setVisible(false);
				lbDatoEx2.setVisible(false);
				txtDomicilio.setVisible(false);
				txtDatoEx1.setVisible(false);
				txtDatoEx2.setVisible(false);
			}

		}

		// Altas
		if (e.getSource() == btnAltas) {
			if (isConsultas) {
				lbEncabezado.setVisible(true);
				txtNombreR.setVisible(true);
				lbTipoCuenta.setVisible(true);
				cmbTipoCuenta.setVisible(true);
				lbNombreR.setVisible(true);
				txtNombreR.setVisible(true);
				lbUsuario.setVisible(true);
				txtUsuario.setVisible(true);
				lbCorreo.setVisible(true);
				txtCorreo.setVisible(true);
				lbContrasena.setVisible(true);
				txtContrasena.setVisible(true);
				lbURL.setVisible(true);
				txtUrl.setVisible(true);
				lbFiltro.setVisible(false);
				cmbFiltro.setVisible(false);

				isConsultas = false;
			} else {
				String tipoCuenta = null;
				String cuenta = txtNombreR.getText();
				String usuario = txtUsuario.getText();
				String correo = txtCorreo.getText();
				char[] passwordChars = txtContrasena.getPassword();
				String password = new String(passwordChars);
				String url = txtUrl.getText();

				switch (cmbTipoCuenta.getSelectedIndex()) {
					// Default
					case 0:

						break;
					// Bancaria
					case 1:
						Banco cuentaBanco;

						tipoCuenta = "Bancaria";
						String cuentaBancaria = txtCuentaB.getText();
						String tarjeta = txtTarjeta.getText();
						String tipoTarjeta = cmbTipoTarjeta.getSelectedItem().toString();
						char[] nipChars = txtNIP.getPassword();
						String nip = new String(nipChars);
						String cve = txtCVE.getText();
						String vencimiento = txtVencimiento.getText();

						cuentaBanco = new Banco(tipoCuenta, cuenta, usuario, correo, password, url, cuentaBancaria,
								tarjeta,
								tipoTarjeta, nip, cve, vencimiento);
						array.add(cuentaBanco);
						break;
					// Servicios
					case 2:
						Servicios servicio;

						tipoCuenta = "Servicio";
						String domicilio = txtDomicilio.getText();
						String datoExtra1 = txtDatoEx1.getText();
						String datoExtra2 = txtDatoEx2.getText();

						servicio = new Servicios(tipoCuenta, cuenta, usuario, correo, password, url, domicilio,
								datoExtra1,
								datoExtra2);
						array.add(servicio);
						break;
					// Pagina Web, App Movil, App Desktop
					case 3:

					case 4:
					case 5:
						switch (cmbTipoCuenta.getSelectedIndex()) {
							case 3:
								tipoCuenta = "Pagina Web";
								break;
							case 4:
								tipoCuenta = "App Movil";
								break;
							case 5:
								tipoCuenta = "App Desktop";
								break;
						}
						Registros registro = new Registros(tipoCuenta, cuenta, usuario, correo, password, url);
						array.add(registro);
						break;
				}
				limpiarCampos();

				// MODIFICAR DESPUES //
				archivo.guardarArchivo(array);
			}
		}

		// Bajas
		if (e.getSource() == btnBajas) {

		}

		// Cambios
		if (e.getSource() == btnCambios) {

		}

		// Consultas
		if (e.getSource() == btnConsultas) {
			GridBagConstraints c;
			// BOTONES
			isConsultas = true;
			btnAltas.setEnabled(true);
			btnBajas.setEnabled(true);
			btnCambios.setEnabled(true);

			// REGISTROS
			lbEncabezado.setVisible(false);
			txtNombreR.setVisible(false);
			lbTipoCuenta.setVisible(false);
			cmbTipoCuenta.setVisible(false);
			lbNombreR.setVisible(false);
			txtNombreR.setVisible(false);
			lbUsuario.setVisible(false);
			txtUsuario.setVisible(false);
			lbCorreo.setVisible(false);
			txtCorreo.setVisible(false);
			lbContrasena.setVisible(false);
			txtContrasena.setVisible(false);
			lbURL.setVisible(false);
			txtUrl.setVisible(false);

			// BANCO
			txtCuentaB.setVisible(false);
			txtTarjeta.setVisible(false);
			cmbTipoTarjeta.setVisible(false);
			txtNIP.setVisible(false);
			txtCVE.setVisible(false);
			txtVencimiento.setVisible(false);
			lbCuentaBancaria.setVisible(false);
			lbTarjeta.setVisible(false);
			lbTipoTarjeta.setVisible(false);
			lbNIP.setVisible(false);
			lbCVE.setVisible(false);
			lbVencimiento.setVisible(false);

			// SERVICIOS
			lbDomicilio.setVisible(false);
			lbDatoEx1.setVisible(false);
			lbDatoEx2.setVisible(false);
			txtDomicilio.setVisible(false);
			txtDatoEx1.setVisible(false);
			txtDatoEx2.setVisible(false);

			c = new GridBagConstraints();
			// Filtro
			c.gridx = 0;
			c.gridy = 0;
			c.gridwidth = 1; // Cambiado para que ocupen una sola columna cada uno
			c.gridheight = 1;
			c.weightx = 0.0;
			c.weighty = 0.0;
			c.fill = GridBagConstraints.NONE;
			c.anchor = GridBagConstraints.LINE_START; // Cambiado para que se alinee a la izquierda
			c.insets = new Insets(10, 5, 0, 0); // Cambiado para agregar más espacio en el margen superior
			c.ipadx = 0;
			c.ipady = 2;
			lbFiltro = new JLabel("Seleccion: ");
			datos.add(lbFiltro, c);

			c.gridx = 1; // Cambiado para que cmbFiltro esté en la columna siguiente
			c.anchor = GridBagConstraints.LINE_END; // Cambiado para que se alinee a la derecha
			cmbFiltro = new JComboBox<>(opciones);
			cmbFiltro.addActionListener(this);
			datos.add(cmbFiltro, c);

			// Agregar datos al JTable
			for (Registros r : array) {
				r.mostrarInfo();

			}
		}

		if (e.getSource() == cmbFiltro) {
			// Eliminar cualquier componente JScrollPane existente en el contenedor datos
			datos.removeAll();
			btnAltas.setEnabled(true);
			btnBajas.setEnabled(true);
			btnCambios.setEnabled(true);
			isConsultas = true;
		
			// Configuración de GridBagConstraints para la tabla
			GridBagConstraints c = new GridBagConstraints();
			c.gridx = 0;
			c.gridy = 1; // Colocar la tabla debajo del JLabel y JComboBox
			c.gridwidth = 2;
			c.gridheight = 1;
			c.weightx = 1.0; // La tabla se extiende horizontalmente
			c.weighty = 1.0; // La tabla se extiende verticalmente
			c.fill = GridBagConstraints.BOTH; // La tabla se expande en ambas direcciones
			c.anchor = GridBagConstraints.CENTER;
			c.insets = new Insets(10, 5, 0, 0);
			c.ipadx = 0;
			c.ipady = 2;
		
			// Crear modelo de tabla y agregar columnas comunes
			DefaultTableModel modeloConsultas = new DefaultTableModel();
			modeloConsultas.addColumn("Tipo de cuenta");
			modeloConsultas.addColumn("Nombre del registro");
			modeloConsultas.addColumn("Usuario");
			modeloConsultas.addColumn("Correo");
			modeloConsultas.addColumn("Contraseña");
			modeloConsultas.addColumn("URL");
		
			switch (cmbFiltro.getSelectedIndex()) {
				case 0: // BANCARIA
					modeloConsultas.addColumn("Cuenta Bancaria");
					modeloConsultas.addColumn("Tarjeta");
					modeloConsultas.addColumn("Tipo Tarjeta");
					modeloConsultas.addColumn("NIP");
					modeloConsultas.addColumn("CVE");
					modeloConsultas.addColumn("VENCIMIENTO");
		
					for (Registros registro : array) {
						if (registro instanceof Banco) {
							Banco banco = (Banco) registro;
							modeloConsultas.addRow(new Object[]{
									banco.getCuenta(),
									banco.getUsuario(),
									banco.getPassword(),
									banco.getCorreo(),
									banco.getUrl(),
									banco.getCuentaBancaria(),
									banco.getTarjeta(),
									banco.getTipoTarjeta(),
									banco.getNip(),
									banco.getCve(),
									banco.getVencimiento()
							});
						}
					}
					break;
				// Caso SERVICIOS
				case 1:
					// Agregar columnas específicas para servicios
					modeloConsultas.addColumn("Domicilio");
					modeloConsultas.addColumn("Dato Extra 1");
					modeloConsultas.addColumn("Dato Extra 2");
		
					// Llenar la tabla con datos de servicios
					for (Registros registro : array) {
						if (registro instanceof Servicios) {
							Servicios servicio = (Servicios) registro;
							modeloConsultas.addRow(new Object[]{
									servicio.getCuenta(),
									servicio.getUsuario(),
									servicio.getPassword(),
									servicio.getCorreo(),
									servicio.getUrl(),
									servicio.getDomicilio(),
									servicio.getDatoExtra1(),
									servicio.getDatoExtra2()
							});
						}
					}
					break;
				// Caso PAGINA WEB
				case 2:
					// Llenar la tabla con datos de páginas web
					for (Registros registro : array) {
						if (registro.getTipoCuenta().equals("Pagina Web")) {
							modeloConsultas.addRow(new Object[]{
									registro.getCuenta(),
									registro.getUsuario(),
									registro.getPassword(),
									registro.getCorreo(),
									registro.getUrl()
							});
						}
					}
					break;
				// Caso APP MOVIL
				case 3:
					// Llenar la tabla con datos de aplicaciones móviles
					for (Registros registro : array) {
						if (registro.getTipoCuenta().equals("App Movil")) {
							modeloConsultas.addRow(new Object[]{
									registro.getCuenta(),
									registro.getUsuario(),
									registro.getPassword(),
									registro.getCorreo(),
									registro.getUrl()
							});
						}
					}
					break;
				// Caso APP DESKTOP
				case 4:
					// Llenar la tabla con datos de aplicaciones de escritorio
					for (Registros registro : array) {
						if (registro.getTipoCuenta().equals("App Desktop")) {
							modeloConsultas.addRow(new Object[]{
									registro.getCuenta(),
									registro.getUsuario(),
									registro.getPassword(),
									registro.getCorreo(),
									registro.getUrl()
							});
						}
					}
					break;
			}
		
			// Crear la tabla con el modelo de datos
			JTable tabla = new JTable(modeloConsultas);
		
			// Agregar la tabla al JScrollPane y luego agregar el JScrollPane al contenedor datos
			JScrollPane scrollPane = new JScrollPane(tabla);
			datos.add(scrollPane, c);
		
			// Actualizar la interfaz
			datos.revalidate();
			datos.repaint();
		}

	}

	private void limpiarCampos() {
		cmbTipoCuenta.setSelectedIndex(0);
		txtNombreR.setText(null);
		txtUsuario.setText(null);
		txtCorreo.setText(null);
		txtContrasena.setText(null);
		txtUrl.setText(null);
		txtCuentaB.setText(null);
		cmbTipoTarjeta.setSelectedIndex(0);
		txtTarjeta.setText(null);
		txtNIP.setText(null);
		txtCVE.setText(null);
		txtVencimiento.setText(null);
		txtDomicilio.setText(null);
		txtDatoEx1.setText(null);
		txtDatoEx2.setText(null);
	}
}
