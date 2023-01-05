import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Test {

	private JFrame frame;
	private JTextField textFieldProductName;
	private JTextField textFieldStock;
	private JTextField textFieldPrice;
	private JTextField textFieldProductID;
	private JTable table;
	private Connection connection;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test window = new Test();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void getTable() {
		String sql = "select * from products";
		
			PreparedStatement prp;
			try {
				prp = connection.prepareStatement(sql);
				ResultSet resultSet = prp.executeQuery();
				table.setModel(DBUtils.resultSetToTableModel(resultSet));

				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
	}
	
	
	/**
	 * Create the application.
	 */
	public Test() {
		initialize();
		connection = utils.DBConnection.connect();
		getTable();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1003, 672);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Stock");
		lblNewLabel_1.setBounds(112, 203, 61, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Price");
		lblNewLabel_2.setBounds(112, 245, 61, 16);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel = new JLabel("Product Name");
		lblNewLabel.setBounds(112, 161, 95, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_3 = new JLabel("Product Manager");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Lucida Grande", Font.BOLD, 24));
		lblNewLabel_3.setBounds(286, 30, 348, 52);
		frame.getContentPane().add(lblNewLabel_3);
		
		textFieldProductName = new JTextField();
		textFieldProductName.setBounds(219, 156, 171, 26);
		frame.getContentPane().add(textFieldProductName);
		textFieldProductName.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Product ID");
		lblNewLabel_4.setBounds(112, 485, 95, 16);
		frame.getContentPane().add(lblNewLabel_4);
		
		textFieldStock = new JTextField();
		textFieldStock.setColumns(10);
		textFieldStock.setBounds(219, 198, 171, 26);
		frame.getContentPane().add(textFieldStock);
		
		textFieldPrice = new JTextField();
		textFieldPrice.setColumns(10);
		textFieldPrice.setBounds(219, 240, 171, 26);
		frame.getContentPane().add(textFieldPrice);
		
		textFieldProductID = new JTextField();
		textFieldProductID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					searchById(Integer.parseInt(textFieldProductID.getText()));
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IllegalArgumentException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		textFieldProductID.setColumns(10);
		textFieldProductID.setBounds(219, 480, 171, 26);
		frame.getContentPane().add(textFieldProductID);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql = " insert into products (name,stock,price ) values (?,?,?)";

				PreparedStatement prp;

				try {
					prp = connection.prepareStatement(sql);
					prp.setString(1, textFieldProductName.getText());
					prp.setInt(2, Integer.parseInt(textFieldStock.getText()));
					prp.setDouble(3, Double.parseDouble(textFieldPrice.getText()));
					prp.executeUpdate();
					JOptionPane.showMessageDialog(null, textFieldProductName.getText() + "başaryla eklendi.");
					System.out.println(textFieldProductName.getText() + " veri tabanına eklendi.");
				
					textFieldProductName.setText("");
					textFieldStock.setText("");
					textFieldPrice.setText("");
					getTable();

				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

			}
		});
		btnSave.setBackground(Color.GREEN);
		btnSave.setForeground(Color.BLACK);
		btnSave.setBounds(112, 318, 117, 29);
		frame.getContentPane().add(btnSave);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldProductName.setText("");
				textFieldStock.setText("");
				textFieldPrice.setText("");
				textFieldProductID.setText("");
				
			}
		});
		btnClear.setBounds(112, 369, 117, 29);
		frame.getContentPane().add(btnClear);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateById(Integer.parseInt(textFieldProductID.getText()));
				getTable();
			}
		});
		btnUpdate.setBounds(273, 318, 117, 29);
		frame.getContentPane().add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteById(Integer.parseInt(textFieldProductID.getText()));
				getTable();
				
			}
		});
		btnDelete.setBounds(273, 369, 117, 29);
		frame.getContentPane().add(btnDelete);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				//veya
				//frame.dispose();
			}
		});
		btnExit.setBounds(726, 447, 162, 94);
		frame.getContentPane().add(btnExit);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(98, 449, 327, 94);
		frame.getContentPane().add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Register", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(98, 130, 327, 293);
		frame.getContentPane().add(panel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(474, 131, 412, 293);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}

	protected void deleteById(int id) {
		String sql = "delete from products where id = " + id;
		try {
			PreparedStatement prp = connection.prepareStatement(sql);
			prp.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	protected void updateById(int id) {
		String sql = "update products set name = ?, stock=?, price = ? where id = " + id;

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,textFieldProductName.getText());
			preparedStatement.setInt(2,Integer.parseInt(textFieldStock.getText()));
			preparedStatement.setDouble(3,Double.parseDouble(textFieldPrice.getText()));
			int affectedRow = preparedStatement.executeUpdate();
		
			
			if(affectedRow > 0) {
				System.out.println("Update Success");
			}else {
				System.out.println("Update Fail");

			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		
	}

	protected void searchById(int id) throws IllegalArgumentException,ParseException{
		String sql = "select * from products where id = " + id;
		
		try {
			PreparedStatement prp = connection.prepareStatement(sql);
			ResultSet resultSet = prp.executeQuery();
			if(resultSet.next()) {
			textFieldProductName.setText(resultSet.getString(2));
			textFieldStock.setText(String.valueOf(resultSet.getInt(3)));
			textFieldPrice.setText(String.valueOf(resultSet.getDouble(4)));
			
			}
			else {
				JOptionPane.showMessageDialog(null, "Bu ürün idsi bulunmamaktadır");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private char[] getInt(int i) {
		// TODO Auto-generated method stub
		return null;
	}
}

