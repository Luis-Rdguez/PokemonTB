package windows;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class StartWindow extends JFrame {
	
	private JPanel contentPane;
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		StartWindow frame = new StartWindow();
		frame.setVisible(true);
	}

	
	public StartWindow() {
		//setIconImage(Toolkit.getDefaultToolkit().getImage(StartWindow.class.getResource("/resources/other/logo.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("PokemonTB");
		setSize(500, 500);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
			
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton bLoginUser = new JButton("Login");
		panel.add(bLoginUser);
			
		bLoginUser.addActionListener(new ActionListener() {
		@Override
			public void actionPerformed(ActionEvent e) {
				// Abrir ventana login user
				dispose();
			}
		});
		
		JButton bRegisterUser = new JButton("Register");
		panel.add(bRegisterUser);
			
		bRegisterUser.addActionListener(new ActionListener() {
		@Override
			public void actionPerformed(ActionEvent e) {
				// Abrir ventana register user
				setVisible(false);
			}
		});
			
		
		setVisible(true);
	}
}
