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
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import Utility.JPasswordFieldShowHide;

import javax.swing.JFormattedTextField;
import java.text.SimpleDateFormat;

public class MainFrame extends JFrame implements ActionListener, KeyListener {

	private static final long serialVersionUID = 1L;

	private JPanel datos, cabecera;
	private JComboBox<String> cmbCombo1;
	private JTextField txtCuenta, txtUsuario, txtCorreo, txtUrl;
	private JTextField txtCuentaB, txtTarjeta, txtNIP, txtCVE, txtVencimiento;
	private JTextField txtDomicilio, txtDatoEx1, txtDatoEx2;
	private JPasswordFieldShowHide txtContrasena;
	private JComboBox<String> cmbTipo;
	private JButton btnAltas, btnBajas, btnCambios, btnConsultas;
	private JLabel lbCuentaBancaria, lbTarjeta, lbTipoTarjeta, lbNIP, lbCVE, lbVencimiento;
	private JLabel lbDomicilio, lbDatoEx1,lbDatoEx2;
	private SimpleDateFormat formatoFecha = new SimpleDateFormat("MM/YY");

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
		JLabel lbl1 = new JLabel("REGISTRO DE USUARIOS");
		datos.add(lbl1, c);

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
		JLabel lbTipo = new JLabel("Tipo de cuenta: ");
		datos.add(lbTipo, c);

		c.gridx = 1;
		c.gridy = 1;
		c.anchor = GridBagConstraints.LINE_START;
		String[] opciones = { "Seleccionar", "Bancaria", "Servicios", "Pagina web", "App movil", "App Desktop" };
		cmbCombo1 = new JComboBox<>(opciones);
		cmbCombo1.addActionListener(this);
		datos.add(cmbCombo1, c);

		// Cuenta
		c.gridx = 0;
		c.gridy = 2;
		c.anchor = GridBagConstraints.LINE_END;
		JLabel lbCuenta = new JLabel("Cuenta: ");
		datos.add(lbCuenta, c);

		c.gridx = 1;
		c.gridy = 2;
		c.anchor = GridBagConstraints.LINE_START;
		txtCuenta = new JTextField();
		txtCuenta.setPreferredSize(new Dimension(150, 15));
		txtCuenta.addKeyListener(this);
		datos.add(txtCuenta, c);

		// Usuario
		c.gridx = 0;
		c.gridy = 3;
		c.anchor = GridBagConstraints.LINE_END;
		JLabel lbUsuario = new JLabel("Usuario: ");
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
		JLabel lbCorreo = new JLabel("Correo: ");
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
		JLabel lbContrasena = new JLabel("Contraseña: ");
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
		JLabel labUrl = new JLabel("URL: ");
		datos.add(labUrl, c);

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
		datos.add(txtCuentaB, c);

		// Tarjeta
		c.gridx = 0;
		c.gridy = 8;
		c.anchor = GridBagConstraints.LINE_START;
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
		c.anchor = GridBagConstraints.LINE_START;
		lbTipoTarjeta = new JLabel("Tipo: ");
		datos.add(lbTipoTarjeta, c);

		c.gridx = 1;
		c.gridy = 9;
		c.anchor = GridBagConstraints.LINE_START;
		String[] tipos = { "Débito", "Crédito", "Recompensas", "Departamental" };
		cmbTipo = new JComboBox<>(tipos);
		datos.add(cmbTipo, c);

		// NIP
		c.gridx = 0;
		c.gridy = 10;
		c.anchor = GridBagConstraints.LINE_START;
		lbNIP = new JLabel("NIP: ");
		datos.add(lbNIP, c);

		c.gridx = 1;
		c.gridy = 10;
		c.anchor = GridBagConstraints.LINE_START;
		txtNIP = new JTextField();
		txtNIP.setPreferredSize(new Dimension(150, 15));
		txtNIP.addKeyListener(this);
		datos.add(txtNIP, c);

		// CVE
		c.gridx = 0;
		c.gridy = 11;
		c.anchor = GridBagConstraints.LINE_START;
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
		c.anchor = GridBagConstraints.LINE_START;
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
		cmbTipo.setVisible(false);
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
		c.anchor = GridBagConstraints.LINE_START;
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
		c.anchor = GridBagConstraints.LINE_START;
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
			if (Character.isDigit(c)) {
				if (txtNIP.getText().length() > 3) {
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
		String seleccion = (String) cmbCombo1.getSelectedItem();

		// Bloquear botones de Altas y Bajas
		if (cmbCombo1.getSelectedIndex() == 0) {
			btnAltas.setEnabled(false);
			btnBajas.setEnabled(false);
		} else {
			btnAltas.setEnabled(true);
			btnBajas.setEnabled(true);

		}

		// Cuentas Bancarias
		if (e.getSource() == cmbCombo1) {
			if ("Bancaria".equals(seleccion)) {
				txtCuentaB.setVisible(true);
				txtTarjeta.setVisible(true);
				cmbTipo.setVisible(true);
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
				cmbTipo.setVisible(false);
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
		if (e.getSource() == cmbCombo1) {
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
			limpiarCampos();
		}

	}

	private void limpiarCampos() {
		cmbCombo1.setSelectedIndex(0);
		txtCuenta.setText(null);
		txtUsuario.setText(null);
		txtCorreo.setText(null);
		txtContrasena.setText(null);
		txtUrl.setText(null);
		txtCuentaB.setText(null);
		cmbTipo.setSelectedIndex(0);
		txtTarjeta.setText(null);
		txtNIP.setText(null);
		txtCVE.setText(null);
		txtVencimiento.setText(null);
		txtDomicilio.setText(null);
		txtDatoEx1.setText(null);
		txtDatoEx2.setText(null);
	}

}
