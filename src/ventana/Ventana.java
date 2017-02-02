package ventana;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import clases.Depart;
import clases.DepartamentoDao;

public class Ventana extends JFrame {

	public Ventana() {
		setSize(600, 320);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		Elementos();
		setVisible(true);
	}

	public void Elementos() {

		// Etiqueta Titulo
		JLabel titulo = new JLabel("GESTION DE DEPARTAMENTOS");
		titulo.setBounds(10, 20, 190, 25);
		add(titulo);

		// Etiqueta Numero departamento
		JLabel enumero = new JLabel("Nº de departamentos");
		enumero.setBounds(10, 50, 170, 25);
		add(enumero);
		// Cuadro de texto
		final JTextArea id = new JTextArea(20, 23);
		id.setBounds(200, 50, 180, 25);
		add(id);

		// Etiqueta nombre
		JLabel enombre = new JLabel("Nonbre");
		enombre.setBounds(10, 80, 170, 25);
		add(enombre);
		// Cuadro de texto nombre
		final JTextArea nombre = new JTextArea(20, 40);
		nombre.setBounds(200, 80, 180, 25);
		add(nombre);

		// Etiqueta localidad
		JLabel elocalidad = new JLabel("Localidad");
		elocalidad.setBounds(10, 110, 170, 25);
		add(elocalidad);
		// Cuadro de texto localidad
		final JTextArea localidad = new JTextArea(20, 50);
		localidad.setBounds(200, 110, 180, 25);
		add(localidad);

		// Etiqueta SALIDA
		JLabel salida = new JLabel("Localidad");
		salida.setBounds(200, 140, 250, 25);
		salida.setText("");
		add(salida);

		// boton alta
		JButton alta = new JButton("ALTA");
		alta.setBounds(10, 180, 80, 25);
		add(alta);
		alta.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				DepartamentoDao dp = new DepartamentoDao();

				Depart departamento = new Depart();

				int a = Integer.parseInt(id.getText());
				byte b = (byte) a;

				String nom = nombre.getText();

				String loc = localidad.getText();

				departamento.setDeptNo(b);
				departamento.setDnombre(nom);
				departamento.setLoc(loc);

				String mensaje = dp.Insertar(departamento);
				salida.setText(mensaje);

			}
		});

		// boton baja
		JButton baja = new JButton("Baja");
		baja.setBounds(100, 180, 80, 25);
		add(baja);
		baja.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				DepartamentoDao dp = new DepartamentoDao();

				int a = Integer.parseInt(id.getText());

				byte b = (byte) a;

				String eliminar = dp.Eliminar(b);
				salida.setText(eliminar);

			}
		});

		// boton actualizar
		JButton actualizar = new JButton("Modificacion");
		actualizar.setBounds(190, 180, 120, 25);
		add(actualizar);
		actualizar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				DepartamentoDao dp = new DepartamentoDao();

				String nom = nombre.getText();

				String loc = localidad.getText();

				int a = Integer.parseInt(id.getText());

				byte b = (byte) a;

				String actu = dp.Actualizar(b, nom, loc);
				salida.setText(actu);
			}
		});

		// boton limpiar
		JButton limpiar = new JButton("limpiar");
		limpiar.setBounds(320, 180, 80, 25);
		add(limpiar);
		limpiar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				nombre.setText("");
				localidad.setText("");
				id.setText("");
			}
		});
		
		// boton consultar
		JButton consultar = new JButton("consultar");
		consultar.setBounds(410, 180, 90, 25);
		add(consultar);
		consultar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				DepartamentoDao dp = new DepartamentoDao();
				Depart con_depart = new Depart();
				
				int a = Integer.parseInt(id.getText());

				Depart depar = new Depart();
				List<Depart> depart  = dp.Consultar(a);
				
				Iterator<Depart> iter = depart.iterator();
				
				while (iter.hasNext()){
					
					Depart dpt = iter.next();
					depar.setDnombre(dpt.getDnombre());
					depar.setLoc(dpt.getLoc());	
				}
				
				String dnombre = depar.getDnombre();
				String dlocalidad = depar.getLoc();
				
				nombre.setText(dnombre);
				localidad.setText(dlocalidad);
			
			}
		});
	}
}
