package windows;

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
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(7, 2));
		
		JLabel lblUser = new JLabel("Username");
		panel.add(lblUser);
		
		JTextField txtName = new JTextField();
		panel.add(txtName);
		txtName.setColumns(10);
		
		JLabel lblFirstSurname= new JLabel("First Surname");
		panel.add(lblFirstSurname);
		
		JLabel lblSecondSurname = new JLabel("Second Surname");
		panel.add(lblSecondSurname);
	
		JTextField txtFirstSurname = new JTextField();
		panel.add(txtFirstSurname);
		txtFirstSurname.setColumns(10);
		
		JTextField txtSecondSurname = new JTextField();
		panel.add(txtSecondSurname);
		txtSecondSurname.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		panel.add(lblPassword);
		
		JPasswordField password = new JPasswordField();
		panel.add(password);
		
		Button bShowPassword = new Button("Show");
		//panel.add(bShowPassword);
		bShowPassword.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		JLabel lblPassword2 = new JLabel("Repeat Password");
		panel.add(lblPassword2);
		
		JPasswordField password2 = new JPasswordField();
		panel.add(password2);
		
		Button bShowPassword2 = new Button("Show");
		//panel.add(bShowPassword2);
		bShowPassword2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
		
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
		
		
		JPanel panelButtons = new JPanel();
		JButton bAccept = new JButton("Accept");
		panelButtons.add(bAccept);
		
		bAccept.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
		        String username = txtName.getText();
		        char[] passwordChars = password.getPassword();
		        String password = new String(passwordChars);
		    	String firstSurname = txtFirstSurname.getText();
		    	String secondSurname = txtSecondSurname.getText();
		    	String email = txtEmail.getText();
		    	String telephone = txtTelefono.getText();
		    	
		        if (isUsernameAlreadyRegistered(username)) {
//		            JOptionPane.showMessageDialog(this, "Username already exists. Please choose another.", "Error", JOptionPane.ERROR_MESSAGE);
		            System.out.println("Username already exists. Please choose another.");
		            return;
		        }

		        try (PrintWriter writer = new PrintWriter(new FileWriter("resources/user.csv", true))) {
		            writer.println(username + "," + password + "," + firstSurname + "," + secondSurname + "," + email+ "," + telephone);
		            System.out.println("User registered successfully!");
//		            JOptionPane.showMessageDialog(this, "User registered successfully!");
		        } catch (IOException ex) {
		            ex.printStackTrace();
		            System.out.println("Error registering user");
//		            JOptionPane.showMessageDialog(this, "Error registering user", "Error", JOptionPane.ERROR_MESSAGE);
		        }
				StartWindow st = new StartWindow();
				st.setVisible(true);
				dispose();
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
	    try {
	        BufferedReader reader = new BufferedReader(new FileReader("resources/user.csv"));
	        String line;
	        while ((line = reader.readLine()) != null) {
	            String[] parts = line.split(",");
	            if (parts.length > 0 && parts[0].equals(username)) {
	                reader.close();
	                return true;
	            }
	        }
	        reader.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(this, "Error checking username", "Error", JOptionPane.ERROR_MESSAGE);
	    }

	    return false;
	}
}
