import java.awt.EventQueue;
import net.proteanit.sql.*;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class JavaCrud {

	private JFrame frame;
	private JTable table;
	private JTextField txtname;
	private JTextField txtedition;
	private JTextField txtprice;
	private JTextField txtbookid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JavaCrud window = new JavaCrud();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public JavaCrud() {
		initialize();
		Connect();
		
	}
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	
	public void Connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/javacrud", "root", "");
		}
		catch(ClassNotFoundException ex) {
			
		}
		catch(SQLException ex) {
			
		}
	}
	
	
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 745, 461);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Book Shop");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setBounds(299, 11, 194, 31);
		frame.getContentPane().add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(393, 80, 316, 241);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("Save");
		
		btnNewButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				String bname,bedition,bprice;
				
				bname=txtname.getText();
				bedition=txtedition.getText();
				bprice=txtprice.getText();
				
				
				try {
					pst=con.prepareStatement("insert into bookrecords(name, edition, price) values(?,?,?)");
					pst.setString(1, bname);
					pst.setString(2, bedition);
					pst.setString(3, bprice);
					pst.executeUpdate();
				
					JOptionPane.showMessageDialog(null, "Record addeddddddddddddddddd");
					table_load();
					txtname.setText("");
					txtedition.setText("");
					txtprice.setText("");
					txtname.requestFocus();
					
				}
				catch(SQLException e1) {
					e1.printStackTrace();
				}
				
				
				
			}
			
			public void table_load() {
				try {
					pst=con.prepareStatement("Select* from bookrecords");
					rs=pst.executeQuery(); 
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
				}
				catch(SQLException e) {
					e.printStackTrace();
				}
			}
			
			
		});
		
		
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(21, 247, 111, 49);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnExit.setBounds(142, 247, 111, 49);
		frame.getContentPane().add(btnExit);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Registration", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(21, 68, 362, 168);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Book name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(25, 30, 106, 25);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Edition");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(25, 66, 106, 25);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Price");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_2.setBounds(25, 102, 106, 25);
		panel.add(lblNewLabel_1_2);
		
		txtname = new JTextField();
		txtname.setBounds(155, 36, 197, 20);
		panel.add(txtname);
		txtname.setColumns(10);
		
		txtedition = new JTextField();
		txtedition.setColumns(10);
		txtedition.setBounds(155, 72, 197, 20);
		panel.add(txtedition);
		
		txtprice = new JTextField();
		txtprice.setColumns(10);
		txtprice.setBounds(155, 108, 197, 20);
		panel.add(txtprice);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Search", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(21, 307, 362, 106);
		frame.getContentPane().add(panel_1);
		
		JLabel lblNewLabel_1_3 = new JLabel("Book ID");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_3.setBounds(10, 35, 106, 25);
		panel_1.add(lblNewLabel_1_3);
		
		
		txtbookid = new JTextField();
		txtbookid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
			try {
				
				String bid ;
				bid=txtbookid.getText();
				pst=con.prepareStatement("select name,edition,price from bookrecords where id = ?");
				pst.setString(1,bid);
				
				ResultSet rs=pst.executeQuery();
				
				
				if(rs.next()==true) {
					String bbname=rs.getString(1);
					String bbedition=rs.getString(2);
					String bbprice=rs.getString(3);
					
					txtname.setText(bbname);
					txtedition.setText(bbedition);
					txtprice.setText(bbprice);
					
				}
				else {
					
					txtname.setText("");
					txtedition.setText("");
					txtprice.setText("");
					
				}
								
			} 
			
			catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
				
				
			}
		});
		
		
		
		txtbookid.setColumns(10);
		txtbookid.setBounds(126, 35, 226, 25);
		panel_1.add(txtbookid);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					pst=con.prepareStatement("Delete from bookrecords");
					JOptionPane.showMessageDialog(null, "Records deleteddddddddd");
					
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		
		
		
		btnClear.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnClear.setBounds(263, 247, 111, 49);
		frame.getContentPane().add(btnClear);
		
		
		
		
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					String bname,bid,bedition,bprice;
					
					bid=txtbookid.getText();
					pst=con.prepareStatement("Delete from bookrecords where id=?");
					pst.setString(1, bid);
					pst.executeUpdate();
				
					JOptionPane.showMessageDialog(null, "Record Deletedddddddd");
					table_load();
					txtname.setText("");
					txtedition.setText("");
					txtprice.setText("");
					txtname.requestFocus();
				
				
				
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
			
			public void table_load() {
				try {
					pst=con.prepareStatement("Select* from bookrecords");
					rs=pst.executeQuery(); 
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
				}
				catch(SQLException e) {
					e.printStackTrace();
				}
			}
		});
		
		
		
		
		
		
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnDelete.setBounds(403, 334, 134, 69);
		frame.getContentPane().add(btnDelete);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				String bname,bedition,bprice,bid;
				
				bname=txtname.getText();
				bedition=txtedition.getText();
				bprice=txtprice.getText();
				bid=txtbookid.getText();
				
				
				try {
					pst=con.prepareStatement("Update bookrecords set name=?, edition=?, price=? where id=?");
					pst.setString(1, bname);
					pst.setString(2, bedition);
					pst.setString(3, bprice);
					pst.setString(4,bid);
					pst.executeUpdate();
				
					JOptionPane.showMessageDialog(null, "Record Updateddddddddddddd");
					table_load();
					txtname.setText("");
					txtedition.setText("");
					txtprice.setText("");
					txtname.requestFocus();
					
				}
				catch(SQLException e1) {
					e1.printStackTrace();
				}
			}
				
				public void table_load() {
					try {
						pst=con.prepareStatement("Select* from bookrecords");
						rs=pst.executeQuery(); 
						table.setModel(DbUtils.resultSetToTableModel(rs));
						
					}
					catch(SQLException e) {
						e.printStackTrace();
					}
				}
			
		});
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnUpdate.setBounds(564, 334, 134, 69);
		frame.getContentPane().add(btnUpdate);
	}
}
