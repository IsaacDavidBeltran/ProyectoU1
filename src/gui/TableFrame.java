package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Point;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

import Components.Banco;
import Components.Registros;
import Components.Servicios;
import Utility.Archivo;

public class TableFrame extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private ArrayList<Registros> array = new ArrayList<>();
	private JPanel datos, foot;
	private JLabel lbFiltro;
	private JTable tabla;
	private JComboBox<String> cmbFiltro;
	DefaultTableModel modeloConsultas;
	private String[] opciones = { "Seleccionar", "Bancaria", "Servicios", "Pagina Web", "App Movil", "App Desktop" };
	private JButton btnAltas, btnBajas, btnCambios, btnConsultas;
	Archivo archivo = new Archivo();

	public TableFrame(Point point) {
		setTitle("Consultas");
		setIconImage(new ImageIcon("src\\img\\logoTec.png").getImage());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(500, 500));
		setLayout(new BorderLayout());
		setLocation(point);
		InitComponents();

	}

	private void InitComponents() {
		// LEER DATOS DEL ARCHIVO
		array = archivo.leerArchivo();
		datos = new JPanel();
		menu();
		add(datos, BorderLayout.NORTH);

		foot = new JPanel();
		opciones();
		add(foot, BorderLayout.SOUTH);

	}

	public void menu() {
		datos.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

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
		c.anchor = GridBagConstraints.LINE_END;
		lbFiltro = new JLabel("Seleccion: ");
		datos.add(lbFiltro, c);

		c.ipadx = 10;
		c.ipady = 2;
		c.anchor = GridBagConstraints.LINE_START;
		cmbFiltro = new JComboBox<>(opciones);
		cmbFiltro.addActionListener(this);
		datos.add(cmbFiltro, c);
	}

	private void opciones() {
		foot.setLayout(new FlowLayout());
		btnAltas = new JButton("Altas");
		btnAltas.setPreferredSize(new Dimension(80, 30));
		btnAltas.addActionListener(this);
		btnAltas.setEnabled(true);
		foot.add(btnAltas);

		btnBajas = new JButton("Bajas");
		btnBajas.setPreferredSize(new Dimension(80, 30));
		btnBajas.addActionListener(this);
		btnBajas.setEnabled(true);
		foot.add(btnBajas);

		btnCambios = new JButton("Cambios");
		btnCambios.setPreferredSize(new Dimension(90, 30));
		btnCambios.addActionListener(this);
		btnCambios.setEnabled(true);
		foot.add(btnCambios);

		btnConsultas = new JButton("Consultas");
		btnConsultas.setPreferredSize(new Dimension(100, 30));
		btnConsultas.addActionListener(this);
		foot.add(btnConsultas);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnAltas) {
			MainFrame ventanaMain = new MainFrame(this.getLocation());
			ventanaMain.setVisible(true);
			this.dispose();
		}

		if (e.getSource() == btnBajas) {

			//int selectedRowIndex = tabla.getSelectedRow();

			if (tabla.getSelectedRow() != -1) { 
				array.remove(tabla.getSelectedRow());
				archivo.guardarArchivo(array);
				actualizarTabla();
			}

		}

		if (e.getSource() == btnCambios) {
			MainFrame ventanaMain = new MainFrame(this.getLocation());
			ventanaMain.setVisible(true);
			this.setVisible(false);
		}

		if (e.getSource() == cmbFiltro) {
			if (tabla != null) {
				datos.remove(tabla.getParent());
				datos.remove(tabla);		
				tabla = new JTable();
				modeloConsultas = new DefaultTableModel();
				
				//modeloConsultas.setRowCount(0);
			}

			tabla = new JTable();
			modeloConsultas = new DefaultTableModel();
			int selectedIndex = cmbFiltro.getSelectedIndex();

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
			DefaultTableModel modeloConsultas = new DefaultTableModel() {
				@Override
				public boolean isCellEditable(int row, int column) {
					return false; // Deshabilitar la edición de todas las celdas
				}
			};
			modeloConsultas.addColumn("Tipo de cuenta");
			modeloConsultas.addColumn("Nombre del registro");
			modeloConsultas.addColumn("Usuario");
			modeloConsultas.addColumn("Correo");
			modeloConsultas.addColumn("Contraseña");
			modeloConsultas.addColumn("URL");

			switch (selectedIndex) {
				case 1: // BANCARIA
					btnBajas.setEnabled(true);
					modeloConsultas.addColumn("Cuenta Bancaria");
					modeloConsultas.addColumn("Tarjeta");
					modeloConsultas.addColumn("Tipo Tarjeta");
					modeloConsultas.addColumn("NIP");
					modeloConsultas.addColumn("CVE");
					modeloConsultas.addColumn("VENCIMIENTO");

					for (Registros registro : array) {
						if (registro instanceof Banco) {
							Banco banco = (Banco) registro;
							modeloConsultas.addRow(new Object[] {
									banco.getTipoCuenta(),
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
				case 2:
					// Agregar columnas específicas para servicios
					btnBajas.setEnabled(true);
					modeloConsultas.addColumn("Domicilio");
					modeloConsultas.addColumn("Dato Extra 1");
					modeloConsultas.addColumn("Dato Extra 2");

					// Llenar la tabla con datos de servicios
					for (Registros registro : array) {
						if (registro instanceof Servicios) {
							Servicios servicio = (Servicios) registro;
							modeloConsultas.addRow(new Object[] {
									servicio.getTipoCuenta(),
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
				case 3:
					// Llenar la tabla con datos de páginas web
					btnBajas.setEnabled(true);
					for (Registros registro : array) {
						if (registro.getTipoCuenta().equals("Pagina Web")) {
							modeloConsultas.addRow(new Object[] {
									registro.getTipoCuenta(),
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
				case 4:
					// Llenar la tabla con datos de aplicaciones móviles
					btnBajas.setEnabled(true);
					for (Registros registro : array) {
						if (registro.getTipoCuenta().equals("App Movil")) {
							modeloConsultas.addRow(new Object[] {
									registro.getTipoCuenta(),
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
				case 5:
					// Llenar la tabla con datos de aplicaciones de escritorio
					btnBajas.setEnabled(true);
					for (Registros registro : array) {
						if (registro.getTipoCuenta().equals("App Desktop")) {
							modeloConsultas.addRow(new Object[] {
									registro.getTipoCuenta(),
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
			tabla = new JTable(modeloConsultas);

			// Agregar la tabla al JScrollPane y luego agregar el JScrollPane al contenedor
			// datos

			JScrollPane scrollPane = new JScrollPane(tabla);
			scrollPane.setPreferredSize(new Dimension(500, 350));
			datos.add(scrollPane, c);

			// Actualizar la interfaz
			datos.revalidate();
			datos.repaint();
		}
	}

	public void actualizarTabla(){
		modeloConsultas.setRowCount(0); // Limpiar todos los datos de la tabla
    	cmbFiltro.getSelectedIndex();

		switch (cmbFiltro.getSelectedIndex()) {
				case 1: // BANCARIA
					btnBajas.setEnabled(true);
					modeloConsultas.addColumn("Cuenta Bancaria");
					modeloConsultas.addColumn("Tarjeta");
					modeloConsultas.addColumn("Tipo Tarjeta");
					modeloConsultas.addColumn("NIP");
					modeloConsultas.addColumn("CVE");
					modeloConsultas.addColumn("VENCIMIENTO");

					for (Registros registro : array) {
						if (registro instanceof Banco) {
							Banco banco = (Banco) registro;
							modeloConsultas.addRow(new Object[] {
									banco.getTipoCuenta(),
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
				case 2:
					// Agregar columnas específicas para servicios
					btnBajas.setEnabled(true);
					modeloConsultas.addColumn("Domicilio");
					modeloConsultas.addColumn("Dato Extra 1");
					modeloConsultas.addColumn("Dato Extra 2");

					// Llenar la tabla con datos de servicios
					for (Registros registro : array) {
						if (registro instanceof Servicios) {
							Servicios servicio = (Servicios) registro;
							modeloConsultas.addRow(new Object[] {
									servicio.getTipoCuenta(),
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
				case 3:
					// Llenar la tabla con datos de páginas web
					btnBajas.setEnabled(true);
					for (Registros registro : array) {
						if (registro.getTipoCuenta().equals("Pagina Web")) {
							modeloConsultas.addRow(new Object[] {
									registro.getTipoCuenta(),
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
				case 4:
					// Llenar la tabla con datos de aplicaciones móviles
					btnBajas.setEnabled(true);
					for (Registros registro : array) {
						if (registro.getTipoCuenta().equals("App Movil")) {
							modeloConsultas.addRow(new Object[] {
									registro.getTipoCuenta(),
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
				case 5:
					// Llenar la tabla con datos de aplicaciones de escritorio
					btnBajas.setEnabled(true);
					for (Registros registro : array) {
						if (registro.getTipoCuenta().equals("App Desktop")) {
							modeloConsultas.addRow(new Object[] {
									registro.getTipoCuenta(),
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

		tabla.setModel(modeloConsultas);
		datos.revalidate();
    	datos.repaint();
	}
}