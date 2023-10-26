package windows;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class LoginUserWindow extends JDialog{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel southPanel;
	private JPanel panelNorte;
	private JPanel panelCentro;
	private JButton btnAcceder;
	private JLabel lblNombre;
	private JTextField txtNombre;
	private JLabel lblContrasenia;
	private JPasswordField txtContrasenia;
	private JLabel lblInicioSesion;
	private JPanel panel;
	private JPanel panel_1;

	public static void main(String[] args) {
		LoginUserWindow frame = new LoginUserWindow();
		frame.setVisible(true);
	}

	public LoginUserWindow() {
		ImageIcon icon = new ImageIcon("resources/other/MainImage.png");
		setIconImage(icon.getImage());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("PokemonTB");
		setSize(450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		southPanel = new JPanel();
		contentPane.add(southPanel, BorderLayout.SOUTH);

		panelNorte = new JPanel();
		contentPane.add(panelNorte, BorderLayout.NORTH);
		panelNorte.setLayout(new FlowLayout());
		lblInicioSesion = new JLabel("Login Session");
		panelNorte.add(lblInicioSesion);
		
		panelCentro = new JPanel();
		contentPane.add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(new FlowLayout());
		
		JPanel panelCentro2 = new JPanel();
		panelCentro.add(panelCentro2,BorderLayout.CENTER);
		panelCentro2.setLayout(new GridLayout(2, 1, 0, 0));
		
		panel_1 = new JPanel();
		panelCentro2.add(panel_1);
		panel_1.setLayout(new GridLayout(1, 1, 0, 0));
		
		lblNombre = new JLabel("Username or email:");
		panel_1.add(lblNombre);
		
		txtNombre = new JTextField();
		panel_1.add(txtNombre);
		txtNombre.setColumns(10);
		
		panel = new JPanel();
		panel.setLayout(new GridLayout(1,2,0,0));
		panelCentro2.add(panel);
		
		lblContrasenia = new JLabel("Password: ");
		panel.add(lblContrasenia);
		

		txtContrasenia = new JPasswordField();
		panel.add(txtContrasenia);
		txtContrasenia.setColumns(10);
		
		Button bShowPass1 = new Button("Show");
		panel.add(bShowPass1);
		
		bShowPass1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		JCheckBox adminCB = new JCheckBox("Admin");
		southPanel.add(adminCB);
		
		btnAcceder = new JButton("Accept");
		southPanel.add(btnAcceder);
		
		btnAcceder.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					dispose();
			}
		});
		
		JButton bAtras = new JButton("Back");
		southPanel.add(bAtras);
		
		bAtras.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					StartWindow st = new StartWindow();
					st.setVisible(true);
					dispose();
			}
		});
		
		setVisible(true);
	}
}
