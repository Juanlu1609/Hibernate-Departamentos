package ventana;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import clases.DepartamentoDao;

public class Ventana_empleado extends JFrame {

	public Ventana_empleado() {
		setSize(500, 450);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		Elementos();
		setVisible(true);
	}

	public void Elementos() {

		// Etiqueta Titulo
		JLabel titulo = new JLabel("Introduce Nº de departamento");
		titulo.setBounds(10, 20, 190, 25);
		add(titulo);

		// Cuadro de id
		final JTextArea id = new JTextArea(20, 23);
		id.setBounds(200, 22, 50, 20);
		add(id);
		
		
		final JTextArea cuadro = new JTextArea(20, 23);
		cuadro.setBounds(20, 100, 400, 200);
		add(cuadro);
		

		// boton consulta
		JButton limpiar = new JButton("Consultar");
		limpiar.setBounds(260, 20, 90, 25);
		add(limpiar);
		limpiar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				DepartamentoDao dp = new DepartamentoDao();
				
				int a = Integer.parseInt(id.getText());
				//String emple = dp.buscarDepart(a);
				
				if(id.getText().isEmpty()){
					cuadro.setText("INTRODUCE UN ID");
				}else{
					String emple = dp.buscarDepart(a);
					cuadro.setText(emple);
				}
				
				
			}
		});

	}
}
