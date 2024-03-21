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
	private JPanel center, foot;
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

		// ELIMINAR DESPUES
		setAlwaysOnTop(true);
	}

	private void InitComponents() {
		// LEER DATOS DEL ARCHIVO
		array = archivo.leerArchivo();
		center = new JPanel();
		menu();
		add(center, BorderLayout.NORTH);

		foot = new JPanel();
		opciones();
		add(foot, BorderLayout.SOUTH);

	}

	public void menu() {
		center.setLayout(new GridBagLayout());
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

		c.ipadx = 10;
		c.ipady = 2;
		c.anchor = GridBagConstraints.LINE_START;
		cmbFiltro = new JComboBox<>(opciones);
		cmbFiltro.addActionListener(this);
		center.add(cmbFiltro, c);
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

			int selectedRowIndex = tabla.getSelectedRow();

			if (tabla.getSelectedRow() != -1) {
				array.remove(selectedRowIndex);
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
			JTable tablaDatos;
			JScrollPane mostrarTabla;
			String columnas[] = {};
			String datos[][] = {};
			int i;

			GridBagConstraints c;

			switch (cmbFiltro.getSelectedIndex()) {
				case 1: // BANCARIA
					int contadorBancos = 0;
					for (Registros registro : array) {
						if (registro instanceof Banco) {
							contadorBancos++;
						}
					}
					columnas = new String[] { "Tipo Cuenta", "Cuenta", "Usuario", "Correo", "Password", "URL",
							"Cuenta Bancaria", "Tarjeta", "Tipo Tarjeta", "NIP", "CVE", "Vencimiento" };
					datos = new String[contadorBancos][columnas.length];

					i = 0;
					for (Registros registro : array) {
						if (registro instanceof Banco) {
							Banco banco = (Banco) registro;
							datos[i][0] = banco.getTipoCuenta();
							datos[i][1] = banco.getCuenta();
							datos[i][2] = banco.getUsuario();
							datos[i][3] = banco.getCorreo();
							datos[i][4] = banco.getPassword();
							datos[i][5] = banco.getUrl();
							datos[i][6] = banco.getCuentaBancaria();
							datos[i][7] = banco.getTarjeta();
							datos[i][8] = banco.getTipoTarjeta();
							datos[i][9] = banco.getNip();
							datos[i][10] = banco.getCve();
							datos[i][11] = banco.getVencimiento();
							i++;
						}
					}

					tablaDatos = new JTable(datos, columnas);
					mostrarTabla = new JScrollPane(tablaDatos);

					center.removeAll(); // Limpiar el contenedor antes de agregar la nueva tabla

					menu();
					c = formatoTabla();
					center.add(mostrarTabla, c);
					center.revalidate();
					center.repaint();
					break;
				case 2: // SERVICIOS
					int contadorServicios = 0;
					for (Registros registro : array) {
						if (registro instanceof Servicios) {
							contadorServicios++;
						}
					}
					columnas = new String[] { "Tipo Cuenta", "Cuenta", "Usuario", "Correo", "Password", "URL",
							"Domicilio", "Dato Extra 1", "Dato Extra 2" };
					datos = new String[contadorServicios][columnas.length];

					i = 0;
					for (Registros registro : array) {
						if (registro instanceof Servicios) {
							Servicios servicio = (Servicios) registro;
							datos[i][0] = servicio.getTipoCuenta();
							datos[i][1] = servicio.getCuenta();
							datos[i][2] = servicio.getUsuario();
							datos[i][3] = servicio.getCorreo();
							datos[i][4] = servicio.getPassword();
							datos[i][5] = servicio.getUrl();
							datos[i][6] = servicio.getDomicilio();
							datos[i][7] = servicio.getDatoExtra1();
							datos[i][8] = servicio.getDatoExtra2();
							i++;
						}
					}

					tablaDatos = new JTable(datos, columnas);
					mostrarTabla = new JScrollPane(tablaDatos);

					center.removeAll(); // Limpiar el contenedor antes de agregar la nueva tabla

					menu();
					c = formatoTabla();
					center.add(mostrarTabla, c);
					center.revalidate();
					center.repaint();
					break;
				// PAGINA WEB
				case 3:
					int contadorWeb = 0;
					for (Registros registro : array) {
						if (registro.getTipoCuenta().equals("Pagina Web")) {
							contadorWeb++;
						}
					}
					columnas = new String[] { "Tipo Cuenta", "Cuenta", "Usuario", "Correo", "Password", "URL" };
					datos = new String[contadorWeb][columnas.length];

					i = 0;
					for (Registros web : array) {
						if (web.getTipoCuenta().equals("Pagina Web")) {
							datos[i][0] = web.getTipoCuenta();
							datos[i][1] = web.getCuenta();
							datos[i][2] = web.getUsuario();
							datos[i][3] = web.getCorreo();
							datos[i][4] = web.getPassword();
							datos[i][5] = web.getUrl();
							i++;
						}
					}

					tablaDatos = new JTable(datos, columnas);
					mostrarTabla = new JScrollPane(tablaDatos);

					center.removeAll(); // Limpiar el contenedor antes de agregar la nueva tabla

					menu();
					c = formatoTabla();
					center.add(mostrarTabla, c);
					center.revalidate();
					center.repaint();
					break;
				// APP MOVIL
				case 4:
					int contadorMovil = 0;
					for (Registros registro : array) {
						if (registro.getTipoCuenta().equals("App Movil")) {
							contadorMovil++;
						}
					}
					columnas = new String[] { "Tipo Cuenta", "Cuenta", "Usuario", "Correo", "Password", "URL" };
					datos = new String[contadorMovil][columnas.length];

					i = 0;
					for (Registros movil : array) {
						if (movil.getTipoCuenta().equals("App Movil")) {
							datos[i][0] = movil.getTipoCuenta();
							datos[i][1] = movil.getCuenta();
							datos[i][2] = movil.getUsuario();
							datos[i][3] = movil.getCorreo();
							datos[i][4] = movil.getPassword();
							datos[i][5] = movil.getUrl();
							i++;
						}
					}

					tablaDatos = new JTable(datos, columnas);
					mostrarTabla = new JScrollPane(tablaDatos);

					center.removeAll(); // Limpiar el contenedor antes de agregar la nueva tabla

					menu();
					c = formatoTabla();
					center.add(mostrarTabla, c);
					center.revalidate();
					center.repaint();
					break;
				// APP DESKTOP
				case 5:
					int contadorDesktop = 0;
					for (Registros registro : array) {
						if (registro.getTipoCuenta().equals("App Desktop")) {
							contadorDesktop++;
						}
					}
					columnas = new String[] { "Tipo Cuenta", "Cuenta", "Usuario", "Correo", "Password", "URL" };
					datos = new String[contadorDesktop][columnas.length];

					i = 0;
					for (Registros desktop : array) {
						if (desktop.getTipoCuenta().equals("App Desktop")) {
							datos[i][0] = desktop.getTipoCuenta();
							datos[i][1] = desktop.getCuenta();
							datos[i][2] = desktop.getUsuario();
							datos[i][3] = desktop.getCorreo();
							datos[i][4] = desktop.getPassword();
							datos[i][5] = desktop.getUrl();
							i++;
						}
					}

					tablaDatos = new JTable(datos, columnas);
					mostrarTabla = new JScrollPane(tablaDatos);

					center.removeAll(); // Limpiar el contenedor antes de agregar la nueva tabla

					menu();
					c = formatoTabla();
					center.add(mostrarTabla, c);
					center.revalidate();
					center.repaint();
					break;
			}

		}
	}

	private GridBagConstraints formatoTabla() {
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 2;
		c.gridheight = 1;
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(10, 5, 0, 0);
		c.ipadx = 0;
		c.ipady = 2;

		return c;
	}

	public void actualizarTabla() {
		modeloConsultas.setRowCount(0); // Limpiar todos los datos de la tabla
		// cmbFiltro.getSelectedIndex();

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
		center.revalidate();
		center.repaint();
	}
}