package gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import classes.User;
import db.db;
import net.miginfocom.swing.MigLayout;

public class RegisterUserWindow extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	public static void main(String[] args) {
	    SwingUtilities.invokeLater(() -> {
	    	RegisterUserWindow frame = new RegisterUserWindow();
			frame.setVisible(true);
	    });
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
		JPanel panelButtons = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(7, 2));
		
		JLabel lblUser = new JLabel("Username");
		panel.add(lblUser);
		
		JTextField txtName = new JTextField();
		panel.add(txtName);
		txtName.setColumns(10);
		
		JLabel lblFirstSurname= new JLabel("First Surname");
		panel.add(lblFirstSurname);
		
		JTextField txtFirstSurname = new JTextField();
		panel.add(txtFirstSurname);
		txtFirstSurname.setColumns(10);	
		
		JLabel lblSecondSurname = new JLabel("Second Surname");
		panel.add(lblSecondSurname);
	
		JTextField txtSecondSurname = new JTextField();
		panel.add(txtSecondSurname);
		txtSecondSurname.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		panel.add(lblPassword);
		
		JPasswordField password = new JPasswordField();
		panel.add(password);
		
		JLabel lblPassword2 = new JLabel("Repeat Password");
		panel.add(lblPassword2);
		
		JPasswordField password2 = new JPasswordField();
		panel.add(password2);
		
//		Button bShowPassword2 = new Button("Show");
//		//panel.add(bShowPassword2);
//		bShowPassword2.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//
//			}
//		});
		
		JLabel lblEmail = new JLabel("Correo Electrónico");
		panel.add(lblEmail);
		
		JTextField txtEmail = new JTextField();
		panel.add(txtEmail);
		txtEmail.setColumns(10);
		
		JLabel lblPhone = new JLabel("Teléfono");
		panel.add(lblPhone);
		
		JTextField txtTelefono = new JTextField();
		panel.add(txtTelefono);
		txtTelefono.setColumns(10);
		
		Button bShowPassword = new Button("Show");
		panelButtons.add(bShowPassword);
		bShowPassword.addActionListener(new ActionListener() {
			boolean visible = false;
			@Override
			public void actionPerformed(ActionEvent e) {
				password.setEchoChar((char) 0);
				password2.setEchoChar((char) 0);
				visible = true;				
				bShowPassword.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if(visible) {
							password.setEchoChar('*');
							password2.setEchoChar('*');
							visible=false;
						}else {
							password.setEchoChar((char) 0);
							password2.setEchoChar('*');
							visible = true;		
						}
					}
				});
			}
		});
		
		JButton bAccept = new JButton("Accept");
		panelButtons.add(bAccept);
		
		bAccept.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

		    	if(txtName.getText().equals("")) {
					JOptionPane.showMessageDialog(contentPane, "Falta el nombre de usuario");
				}else if(txtEmail.getText().equals("")) {
					JOptionPane.showMessageDialog(contentPane, "Falta el email");
				}else if(txtFirstSurname.getText().equals("")){
					JOptionPane.showMessageDialog(contentPane, "Falta el primer apellido");
				}else if(txtSecondSurname.getText().equals("")) {
					JOptionPane.showMessageDialog(contentPane, "Falta el segundo apellido");
				}else if(password.getPassword().equals("")) {
					JOptionPane.showMessageDialog(contentPane, "Falta la contraseña");
				}else if(password2.getPassword().equals("")) {
					JOptionPane.showMessageDialog(contentPane, "Falta repetir la contraseña");
				}else if(!(password.getText().equals(password2.getText()))) {
					JOptionPane.showMessageDialog(contentPane, "Las contraseñas no son iguales");
				}else if(txtTelefono.getText().equals("")) {
					JOptionPane.showMessageDialog(contentPane, "Falta el telefono");
				}else if (isUsernameAlreadyRegistered(txtName.getText())) {
		            JOptionPane.showMessageDialog(contentPane, "Username already exists. Please choose another.");
		        }else {
			        String username = txtName.getText();
			        char[] passwordChars = password.getPassword();
			        String pass = new String(passwordChars);
			    	String firstSurname = txtFirstSurname.getText();
			    	String secondSurname = txtSecondSurname.getText();
			    	String email = txtEmail.getText();
			    	int telephone = Integer.parseInt(txtTelefono.getText());
		        	User u = new User(username,pass,firstSurname,secondSurname,email,telephone);
			        db.añadirUsuario(u);
					StartWindow st = new StartWindow();
					st.setVisible(true);
					dispose();
		        }
			}
		});
		
		JButton btnBack = new JButton("Back");
		panelButtons.add(btnBack);
		
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				StartWindow st = new StartWindow();
				st.setVisible(true);
				dispose();
			}
		});
		
		contentPane.add(panelButtons, BorderLayout.SOUTH);
		
		this.setVisible(true);
	}
	
	private boolean isUsernameAlreadyRegistered(String username) {
//	    try {
	    	User u = db.seleccionarUsuarioPorNombre(username);
        	if(u != null) {
        		return true;
        		}
//	    } catch (IOException e) {
	//        e.printStackTrace();
	  //      JOptionPane.showMessageDialog(this, "Error checking username", "Error", JOptionPane.ERROR_MESSAGE);
	    //}

	    return false;
	}
}
