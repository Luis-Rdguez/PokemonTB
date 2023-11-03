package windows;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
		ImageIcon icon = new ImageIcon("resources/other/MainImage.png");
		setIconImage(icon.getImage());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("PokemonTB");
		setSize(800, 600);
		setLocationRelativeTo(null);
		
		try {
            BufferedImage backgroundImage = ImageIO.read(new File("resources/other/background.jpg"));
            
            contentPane = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.drawImage(backgroundImage, 0, 0, this);
                }
            };
            contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    		contentPane.setLayout(new BorderLayout(0, 0));
    		setContentPane(contentPane);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		JLabel imLabel = new JLabel(new ImageIcon("resources/other/MainImage.png"));	
		contentPane.add(imLabel, BorderLayout.CENTER);
		
		JPanel bPanel = new JPanel();
		bPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		contentPane.add(bPanel, BorderLayout.SOUTH);

		JButton bLoginUser = new JButton("Login");
		bPanel.add(bLoginUser);
		
		JButton bRegisterUser = new JButton("Register");
		bPanel.add(bRegisterUser);
			
		bPanel.setOpaque(false);
		
		bLoginUser.addActionListener(new ActionListener() {
		@Override
			public void actionPerformed(ActionEvent e) {
				LoginUserWindow luw = new LoginUserWindow();
				luw.setVisible(true);
				dispose();
			}
		});
			
		bRegisterUser.addActionListener(new ActionListener() {
		@Override
			public void actionPerformed(ActionEvent e) {
				RegisterUserWindow ruw = new RegisterUserWindow();
				ruw.setVisible(true);
				dispose();
			}
		});
		setVisible(true);
	}
}
