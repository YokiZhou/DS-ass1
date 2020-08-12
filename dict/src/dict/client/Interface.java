/*COMP90015 Assignment1  
  Shengqi Zhou 893295 */
package dict.client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextPane;

public class Interface {

	private JFrame frmDictionarySystem;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextPane textPane_1;
	private JTextPane textPane_2;
	private JTextPane textPane_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interface window = new Interface();
					window.frmDictionarySystem.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Interface() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDictionarySystem = new JFrame();
		frmDictionarySystem.setTitle("Dictionary System");
		frmDictionarySystem.setBounds(100, 100, 450, 300);
		frmDictionarySystem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDictionarySystem.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Input");
		lblNewLabel.setBounds(20, 6, 61, 16);
		frmDictionarySystem.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setForeground(Color.BLACK);
		textField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
		textField.setBounds(21, 165, 400, 80);
		frmDictionarySystem.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		textField_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
		textField_1.setBounds(20, 165, 400, 80);
		frmDictionarySystem.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		textField_2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
		textField_2.setBounds(20, 165, 400, 80);
		frmDictionarySystem.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		textField_3.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
		textField_3.setBounds(20, 165, 400, 80);
		frmDictionarySystem.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		JTextPane textPane = new JTextPane();
		textPane.setForeground(Color.BLACK);
		textPane.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
		textPane.setBounds(25, 33, 100, 20);
		frmDictionarySystem.getContentPane().add(textPane);
		
		textPane_1 = new JTextPane();
		textPane_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
		textPane_1.setBounds(25, 71, 100, 20);
		frmDictionarySystem.getContentPane().add(textPane_1);
		
		textPane_2 = new JTextPane();
		textPane_2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
		textPane_2.setBounds(25, 110, 100, 20);
		frmDictionarySystem.getContentPane().add(textPane_2);
		
		textPane_3 = new JTextPane();
		textPane_3.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
		textPane_3.setBounds(132, 110, 200, 20);
		frmDictionarySystem.getContentPane().add(textPane_3);
		
		Client client = new Client();
		
		JButton btnNewButton = new JButton("find");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String result="";
				System.out.println(textPane.getText());
				try {
					result = client.find(textPane.getText());
				}
				catch(Exception ex) {
					ex.printStackTrace();
				}
				textField.setText(result);
			}
			
		});
		btnNewButton.setBackground(Color.GRAY);
		btnNewButton.setBounds(132, 29, 80, 29);
		frmDictionarySystem.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("remove");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String result = "";
				System.out.println(textPane_1.getText());
				try {
					result = client.remove(textPane_1.getText());
				}
				catch(Exception ex) {
					ex.printStackTrace();
				}
				textField.setText(result);
			}
		});
		btnNewButton_1.setBounds(132, 67, 80, 29);
		frmDictionarySystem.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("add");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String result = "";
				System.out.println(textPane_2.getText());
				try {
					result = client.add(textPane_2.getText(), textPane_3.getText());
				}
				catch(Exception ex) {
					ex.printStackTrace();
				}
				textField.setText(result);
			}
		});
		btnNewButton_2.setBounds(344, 105, 80, 29);
		frmDictionarySystem.getContentPane().add(btnNewButton_2);
		
		JLabel lblOutput = new JLabel("Output");
		lblOutput.setBounds(20, 137, 61, 16);
		frmDictionarySystem.getContentPane().add(lblOutput);
		
		textField_4 = new JTextField();
		textField_4.setBounds(20, 165, 400, 80);
		frmDictionarySystem.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		

		

	}
}

