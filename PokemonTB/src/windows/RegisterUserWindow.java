package windows;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

public class RegisterUserWindow extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	public static void main(String[] args) {
		RegisterUserWindow frame = new RegisterUserWindow();
		frame.setVisible(true);
	}

	public RegisterUserWindow() {
		ImageIcon icon = new ImageIcon("resources/other/MainImage.png");
		setIconImage(icon.getImage());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("PokemonTB");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setSize(600, 600);
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new MigLayout("", "[67px][5px][19px][5px][68px,grow][5px][13px][][5px][26px,grow][5px][25px][5px][7px][5px][18px][5px][9px][5px][94px][7px]", "[22px][22px][23px][][][][][][][][][][][][][]"));
		
		JLabel lblUser = new JLabel("Username");
		panel.add(lblUser, "cell 4 0,alignx left,aligny center");
		
		JTextField txtName = new JTextField();
		panel.add(txtName, "cell 4 1,alignx left,aligny center");
		txtName.setColumns(10);
		
		JLabel lblFirstSurname= new JLabel("First Surname");
		panel.add(lblFirstSurname, "cell 4 2");
		
		JLabel lblSecondSurname = new JLabel("Second Surname");
		panel.add(lblSecondSurname, "cell 9 2");
	
		JTextField txtFirstSurname = new JTextField();
		panel.add(txtFirstSurname, "cell 4 3,growx");
		txtFirstSurname.setColumns(10);
		
		JTextField txtSecondSurname = new JTextField();
		panel.add(txtSecondSurname, "cell 9 3,growx");
		txtSecondSurname.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		panel.add(lblPassword, "cell 4 4,alignx left,aligny center");
		
		JPasswordField password = new JPasswordField();
		panel.add(password, "cell 4 5,growx,aligny center");
		
		Button bShowPassword = new Button("Show");
		panel.add(bShowPassword, "cell 7 5,alignx left,aligny top");
		bShowPassword.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		JLabel lblPassword2 = new JLabel("Repeat Password");
		panel.add(lblPassword2, "cell 4 6,alignx left,aligny center");
		
		JPasswordField password2 = new JPasswordField();
		panel.add(password2, "cell 4 7,growx,aligny center");
		
		Button bShowPassword2 = new Button("Show");
		panel.add(bShowPassword2, "cell 7 7,alignx right,aligny top");
		bShowPassword2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
		
		JLabel lblEmail = new JLabel("Correo Electrónico");
		panel.add(lblEmail, "cell 4 8,alignx left,aligny center");
		
		JTextField txtEmail = new JTextField();
		panel.add(txtEmail, "cell 4 9,alignx left,aligny center");
		txtEmail.setColumns(10);
		
		JLabel lblPhone = new JLabel("Teléfono");
		panel.add(lblPhone, "cell 4 10,alignx left,aligny center");
		
		JTextField txtTelefono = new JTextField();
		panel.add(txtTelefono, "cell 4 11,alignx left,aligny center");
		txtTelefono.setColumns(10);
		
		JButton bAccept = new JButton("Accept");
		panel.add(bAccept, "cell 19 16,alignx left,aligny top");
		
		bAccept.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { 

			}
			
		});
		
		JButton btnBack = new JButton("Back");
		panel.add(btnBack, "cell 0 16");
		
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				StartWindow st = new StartWindow();
				st.setVisible(true);
				dispose();
			}
		});
		
		this.setVisible(true);
	}
}
