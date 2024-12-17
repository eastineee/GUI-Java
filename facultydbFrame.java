package java_gui_group7;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JDesktopPane;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import javax.swing.JScrollPane;
import javax.swing.JInternalFrame;
import javax.swing.JSplitPane;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class facultydbFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable facultyTable;
	private JTextField searchField;
	private JTextField idFld;
	private JTextField nameFld;
	private JTextField emailFld;
	private JTextField ageFld;
	private JTextField departmentFld;

	ArrayList<String> allFacultyList = new ArrayList<>();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					facultydbFrame frame = new facultydbFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public facultydbFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 490);
		setTitle("Faculty - USTP DBMS");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.decode("#e6edf7"));;

		setContentPane(contentPane);
		
		contentPane.setLayout(null);
		
		//set image icon
		ImageIcon ustpLogoIcon = new ImageIcon("USTP_DBMS-removebg-preview.png");
		setIconImage(ustpLogoIcon.getImage());
		
		JLabel studentDBMSLbl = new JLabel("Faculty - DBMS");
		studentDBMSLbl.setFont(new Font("Tahoma", Font.BOLD, 20));
		studentDBMSLbl.setBounds(10, 11, 167, 25);
		studentDBMSLbl.setForeground(Color.decode("#004aad"));
		contentPane.add(studentDBMSLbl);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 195, 363, 211);
		contentPane.add(scrollPane);
		
		
		//faculty table
		
		facultyTable = new JTable();
		DefaultTableModel model = new DefaultTableModel();
		Object[] column = {"FID","Name","Email","Age","Department"};
		final Object[] row = new Object[5];
		model.setColumnIdentifiers(column);
		facultyTable.setModel(model);
		displayToStudentDBList(allFacultyList,model,"facultydb.txt");
		scrollPane.setViewportView(facultyTable);
		
		
		
		//go to student dbms
		JButton toStudentDBMSBtn = new JButton("Go to Student DBMS");
		toStudentDBMSBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				studentdbFrame studentdbframe = new studentdbFrame();
				studentdbframe.setVisible(true);
			}
		});
		toStudentDBMSBtn.setFocusPainted(false);
		toStudentDBMSBtn.setBounds(336, 11, 140, 25);
		contentPane.add(toStudentDBMSBtn);
		toStudentDBMSBtn.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		
		//add a faculty
		JButton addBtn = new JButton("ADD");
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(idFld.getText().equals("") || nameFld.getText().equals("") || emailFld.getText().equals("") || ageFld.getText().equals("") || departmentFld.getText().equals("")) {
					JOptionPane.showMessageDialog(contentPane, "Please Fill Complete Information.");
				}
				else {
					row[0] = idFld.getText();
					row[1] = nameFld.getText();
					row[2] = emailFld.getText();
					row[3] = ageFld.getText();
					row[4] = departmentFld.getText();
					
					String insertedStrings = row[0]+","+row[1]+","+row[2]+","+row[3]+","+row[4];
					String filepath = "facultydb.txt";
					
					addtostudentDBList(insertedStrings, filepath);
					
					model.addRow(row);
					
					nameFld.setText("");
					idFld.setText("");
					emailFld.setText("");
					ageFld.setText("");
					departmentFld.setText("");
					
					JOptionPane.showMessageDialog(contentPane, "Saved successfully!");
				}
			}
		});
		addBtn.setFocusPainted(false);
		addBtn.setFont(new Font("Tahoma", Font.PLAIN, 11));
		addBtn.setBounds(387, 94, 89, 23);
		contentPane.add(addBtn);
		
		
		//edit a faculty
		JButton updateBtn = new JButton("UPDATE");
		updateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//index of selected row
				int i = facultyTable.getSelectedRow();
				
				if(i>=0) {
					if(idFld.getText().equals("") || nameFld.getText().equals("") || emailFld.getText().equals("") || ageFld.getText().equals("") || departmentFld.getText().equals("")) {
						JOptionPane.showMessageDialog(contentPane, "Please Fill Complete Information.");
					}
					else {
						
						String newId = idFld.getText();
						String newName = nameFld.getText();
						String newEmail = emailFld.getText();
						String newAge = ageFld.getText();
						String newProgram = departmentFld.getText();
						
						editFromArrList("facultydb.txt",allFacultyList, i, newId, newName, newEmail, newAge, newProgram);
						
						model.setValueAt(newId, i, 0);
						model.setValueAt(newName, i, 1);
						model.setValueAt(newEmail, i, 2);
						model.setValueAt(newAge, i, 3);
						model.setValueAt(newProgram, i, 4);
						
						nameFld.setText("");
						idFld.setText("");
						emailFld.setText("");
						ageFld.setText("");
						departmentFld.setText("");
						
						JOptionPane.showMessageDialog(contentPane, "Edited successfully!");
						
					}
				}
				else {
					JOptionPane.showMessageDialog(contentPane, "Please select a row first.");
				}
			}
		});
		updateBtn.setFocusPainted(false);
		updateBtn.setFont(new Font("Tahoma", Font.PLAIN, 11));
		updateBtn.setBounds(387, 119, 89, 23);
		contentPane.add(updateBtn);
		
		
		//delete a faculty
		JButton delBtn = new JButton("DELETE");
		delBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = facultyTable.getSelectedRow();
				
//				removeFromArrList("studentdb.txt",allStudentList,i);
//				model.removeRow(i);
//				JOptionPane.showMessageDialog(contentPane, "Removed successfully!");
				
				if(i >= 0) {
					removeFromArrList("facultydb.txt",allFacultyList,i);
					model.removeRow(i);
					
					JOptionPane.showMessageDialog(contentPane, "Removed successfully!");
				}
				else {
					JOptionPane.showMessageDialog(contentPane, "Please select a row first.");
				}
			}
		});
		delBtn.setFocusPainted(false);
		delBtn.setFont(new Font("Tahoma", Font.PLAIN, 11));
		delBtn.setBounds(387, 347, 89, 23);
		contentPane.add(delBtn);
		
		
		//logout
		JButton logoutBtn = new JButton("LOGOUT");
		logoutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirm = JOptionPane.showConfirmDialog(contentPane, "Are you sure?");
				
				if(confirm == JOptionPane.YES_OPTION) {
					dispose();
					loginFrame loginframe = new loginFrame();
					loginframe.setVisible(true);
				}
			}
		});
		logoutBtn.setFocusPainted(false);
		logoutBtn.setFont(new Font("Tahoma", Font.PLAIN, 11));
		logoutBtn.setBounds(10, 417, 89, 25);
		contentPane.add(logoutBtn);
		
		
		//view a faculty
		JButton fetchBtn = new JButton("FETCH");
		fetchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(e.getSource()==fetchBtn) {
					fetchData();
				}
				
			}
		});
		fetchBtn.setFocusPainted(false);
		fetchBtn.setFont(new Font("Tahoma", Font.PLAIN, 11));
		fetchBtn.setBounds(387, 322, 89, 23);
		contentPane.add(fetchBtn);
		
		
		//searching a faculty
		searchField = new JTextField();
		searchField.setBounds(387, 220, 89, 20);
		contentPane.add(searchField);
		searchField.setColumns(10);
		
		JButton searchBtn = new JButton("Search ID");
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String searchId = searchField.getText();
				int idColIdx = 0;
				boolean found = false;
				
				for(int i = 0; i < model.getRowCount(); i++) {
					String currentId = model.getValueAt(i, idColIdx).toString();
					
					if(currentId.equals(searchId)) {
						
						found = true;
						
						String sid = (String) facultyTable.getValueAt(i, 0);
						String name = (String) facultyTable.getValueAt(i, 1);
						String email = (String) facultyTable.getValueAt(i, 2);
						String age = (String) facultyTable.getValueAt(i, 3);
						String program = (String) facultyTable.getValueAt(i, 4);
						
						nameFld.setText(name);
						idFld.setText(sid);
						emailFld.setText(email);
						ageFld.setText(age);
						departmentFld.setText(program);
						
						break;
					}
					
				}
				
				if(!found) {
					JOptionPane.showMessageDialog(contentPane, "ID not found!");
				}
				

			}
		});
		searchBtn.setFocusPainted(false);
		searchBtn.setFont(new Font("Tahoma", Font.PLAIN, 11));
		searchBtn.setBounds(387, 243, 89, 25);
		contentPane.add(searchBtn);
		
		JPanel formPanel = new JPanel();
		formPanel.setBounds(10, 47, 363, 137);
		formPanel.setBackground(Color.decode("#e6edf7"));;
		
		contentPane.add(formPanel);
		formPanel.setLayout(null);
		
		nameFld = new JTextField();
		nameFld.setBounds(47, 38, 112, 20);
		formPanel.add(nameFld);
		nameFld.setColumns(10);
		
		idFld = new JTextField();
		idFld.setBounds(47, 69, 112, 20);
		formPanel.add(idFld);
		idFld.setColumns(10);
		
		emailFld = new JTextField();
		emailFld.setBounds(47, 100, 112, 20);
		formPanel.add(emailFld);
		emailFld.setColumns(10);
		
		ageFld = new JTextField();
		ageFld.setBounds(228, 38, 41, 20);
		formPanel.add(ageFld);
		ageFld.setColumns(10);
		
		departmentFld = new JTextField();
		departmentFld.setBounds(228, 69, 96, 20);
		formPanel.add(departmentFld);
		departmentFld.setColumns(10);
		
		JLabel nameLbl = new JLabel("Name:");
		nameLbl.setFont(new Font("Tahoma", Font.PLAIN, 11));
		nameLbl.setBounds(10, 41, 49, 14);
		formPanel.add(nameLbl);
		
		JLabel fidLbl = new JLabel("FID:");
		fidLbl.setFont(new Font("Tahoma", Font.PLAIN, 11));
		fidLbl.setBounds(20, 72, 41, 14);
		formPanel.add(fidLbl);
		
		JLabel emailLbl = new JLabel("Email:");
		emailLbl.setFont(new Font("Tahoma", Font.PLAIN, 11));
		emailLbl.setBounds(10, 103, 49, 14);
		formPanel.add(emailLbl);
		
		JLabel ageLbl = new JLabel("Age:");
		ageLbl.setFont(new Font("Tahoma", Font.PLAIN, 11));
		ageLbl.setBounds(199, 41, 33, 14);
		formPanel.add(ageLbl);
		
		JLabel programLbl = new JLabel("Department:");
		programLbl.setFont(new Font("Tahoma", Font.PLAIN, 11));
		programLbl.setBounds(176, 72, 49, 14);
		formPanel.add(programLbl);
		
		JLabel facultyFormLbl = new JLabel("Faculty Form Fields");
		facultyFormLbl.setHorizontalAlignment(SwingConstants.CENTER);
		facultyFormLbl.setBounds(10, 11, 343, 14);
		facultyFormLbl.setForeground(Color.decode("#004aad"));
		formPanel.add(facultyFormLbl);
		
		JButton clrBtn = new JButton("CLEAR");
		clrBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				idFld.setText("");
				nameFld.setText("");
				emailFld.setText("");
				ageFld.setText("");
				departmentFld.setText("");
			}
		});
		clrBtn.setFocusPainted(false);
		clrBtn.setFont(new Font("Tahoma", Font.PLAIN, 11));
		clrBtn.setBounds(228, 99, 96, 23);
		formPanel.add(clrBtn);
		
		
		JButton refreshTbl = new JButton("REFRESH");
		refreshTbl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
//				setVisible(true);
				
				facultydbFrame frame = new facultydbFrame();
				frame.setVisible(true);
			}
		});
		refreshTbl.setFocusPainted(false);
		refreshTbl.setFont(new Font("Tahoma", Font.PLAIN, 11));
		refreshTbl.setBounds(284, 418, 89, 23);
		contentPane.add(refreshTbl);

		setLocationRelativeTo(null);

	}
	
	
	public void fetchData(){
		int i = facultyTable.getSelectedRow();
		
		if(i>=0) {
			String fid = (String) facultyTable.getValueAt(i, 0);
			String name = (String) facultyTable.getValueAt(i, 1);
			String email = (String) facultyTable.getValueAt(i, 2);
			String age = (String) facultyTable.getValueAt(i, 3);
			String department = (String) facultyTable.getValueAt(i, 4);
			
			nameFld.setText(name);
			idFld.setText(fid);
			emailFld.setText(email);
			ageFld.setText(age);
			departmentFld.setText(department);
		}
		else {
			JOptionPane.showMessageDialog(contentPane, "Please select a row first.");
		}
	}
	
	
	//writing and appending
	public void addtostudentDBList(String dataStrings, String filepath) {
//		list.add(dataStrings);
		
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(filepath, true))){
			writer.write(dataStrings);
			writer.newLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	//reading from file and displaying
	public void displayToStudentDBList(ArrayList<String> facultyList, DefaultTableModel model, String filepath) {
		try(BufferedReader reader = new BufferedReader(new FileReader(filepath))){
			List<String[]> dataList = new ArrayList<>();
			
			//read data from textfile
			String line;
			while((line = reader.readLine()) != null) {
				//read from text file and put it into arraylist
				facultyList.add(line);
				
				String[] rowData = line.split(",");
				dataList.add(rowData);
			}
			reader.close();
			
			//display to table
			for(String[] rowData : dataList) {
				model.addRow(rowData);
			}
			
//			for(int item = 0; item < facultyList.size(); item++) {
//				System.out.println("Idx "+item+": "+facultyList.get(item));
//			}
			
//			System.out.println(studentList.get(0));
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	//index row, sid, sname, semail, sage, sprogram
	public void editFromArrList(String filepath, ArrayList<String> facultyList, int idxRow, String fid, String fname, String femail, String fage, String fdepartment) {
		
		//inserting edited data
		String newData = fid+","+fname+","+femail+","+fage+","+fdepartment;
		facultyList.set(idxRow, newData);
		
		//apply arraylist changes to textfile
		applyArrListChanges("facultydb.txt", allFacultyList);
		
	}
	
	
	//delete from arraylist
	public void removeFromArrList(String filepath, ArrayList<String> facultyList, int idxRow) {
		facultyList.remove(idxRow);
		
		//apply arraylist changes to textfile
		applyArrListChanges("facultydb.txt", allFacultyList);
	}
	
	
	public void applyArrListChanges(String filepath, ArrayList<String> facultyList) {
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(filepath))){
			for(String item : facultyList) {
				writer.write(item);
				writer.newLine();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}