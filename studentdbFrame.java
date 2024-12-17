package java_gui_group7;


import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.TableView.TableRow;
import javax.swing.SwingConstants;

public class studentdbFrame extends JFrame {

	private static final long serialVersionUID = 1L;
//	private JFrame frame;
	private JPanel contentPane;
	private JTable studentTable;
	private JTextField searchField;
	
	ArrayList<String> allStudentList = new ArrayList<>();
	private JTextField nameFld;
	private JTextField idFld;
	private JTextField emailFld;
	private JTextField ageFld;
	private JTextField programFld;
	
	/*Launch the application.*/
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					studentdbFrame frame = new studentdbFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/*Create the frame.*/
	public studentdbFrame() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 490);
		setTitle("Student - USTP DBMS");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.decode("#e6edf7"));;

		setContentPane(contentPane);
		
		contentPane.setLayout(null);
		
		//set image icon
		ImageIcon ustpLogoIcon = new ImageIcon("USTP_DBMS-removebg-preview.png");
		setIconImage(ustpLogoIcon.getImage());
		
		JLabel studentDBMSLbl = new JLabel("Student - DBMS");
		studentDBMSLbl.setFont(new Font("Tahoma", Font.BOLD, 20));
		studentDBMSLbl.setBounds(10, 11, 167, 25);
		studentDBMSLbl.setForeground(Color.decode("#004aad"));
		contentPane.add(studentDBMSLbl);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 195, 363, 211);
		contentPane.add(scrollPane);
		
		
		//faculty table
		
		studentTable = new JTable();
		DefaultTableModel model = new DefaultTableModel();
		Object[] column = {"SID","Name","Email","Age","Program"};
		final Object[] row = new Object[5];
		model.setColumnIdentifiers(column);
		studentTable.setModel(model);
		displayToStudentDBList(allStudentList,model,"studentdb.txt");
		scrollPane.setViewportView(studentTable);
		
		//go to student dbms
		JButton toFacultyDBMSBtn = new JButton("Go to Faculty DBMS");
		toFacultyDBMSBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				facultydbFrame facultydbframe = new facultydbFrame();
				facultydbframe.setVisible(true);
			}
		});
		toFacultyDBMSBtn.setFocusPainted(false);
		toFacultyDBMSBtn.setBounds(336, 11, 140, 25);
		contentPane.add(toFacultyDBMSBtn);
		toFacultyDBMSBtn.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		
		//add a faculty
		JButton addBtn = new JButton("ADD");
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(idFld.getText().equals("") || nameFld.getText().equals("") || emailFld.getText().equals("") || ageFld.getText().equals("") || programFld.getText().equals("")) {
					JOptionPane.showMessageDialog(contentPane, "Please Fill Complete Information.");
				}
				else {
					row[0] = idFld.getText();
					row[1] = nameFld.getText();
					row[2] = emailFld.getText();
					row[3] = ageFld.getText();
					row[4] = programFld.getText();
					
					String insertedStrings = row[0]+","+row[1]+","+row[2]+","+row[3]+","+row[4];
					String filepath = "studentdb.txt";
					
					addtostudentDBList(insertedStrings, filepath);
					
					model.addRow(row);
					
					nameFld.setText("");
					idFld.setText("");
					emailFld.setText("");
					ageFld.setText("");
					programFld.setText("");
					
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
				if(e.getSource()==updateBtn) {
					int i = studentTable.getSelectedRow();
					
					if(i>=0) {
						if(idFld.getText().equals("") || nameFld.getText().equals("") || emailFld.getText().equals("") || ageFld.getText().equals("") || programFld.getText().equals("")) {
							JOptionPane.showMessageDialog(contentPane, "Please Fill Complete Information.");
						}
						else {
							
//							fetchData();
							
							String newId = idFld.getText();
							String newName = nameFld.getText();
							String newEmail = emailFld.getText();
							String newAge = ageFld.getText();
							String newProgram = programFld.getText();
							
							model.setValueAt(newId, i, 0);
							model.setValueAt(newName, i, 1);
							model.setValueAt(newEmail, i, 2);
							model.setValueAt(newAge, i, 3);
							model.setValueAt(newProgram, i, 4);
							
							editFromArrList("studentdb.txt",allStudentList, i, newId, newName, newEmail, newAge, newProgram);
							
							nameFld.setText("");
							idFld.setText("");
							emailFld.setText("");
							ageFld.setText("");
							programFld.setText("");
							
							JOptionPane.showMessageDialog(contentPane, "Edited successfully!");
							
						}
					}
					else {
						JOptionPane.showMessageDialog(contentPane, "Please select a row first.");
					}
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
				int i = studentTable.getSelectedRow();
				
//				removeFromArrList("studentdb.txt",allStudentList,i);
//				model.removeRow(i);
//				JOptionPane.showMessageDialog(contentPane, "Removed successfully!");
				
				if(i >= 0) {
					removeFromArrList("studentdb.txt",allStudentList,i);
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
						
						String sid = (String) studentTable.getValueAt(i, 0);
						String name = (String) studentTable.getValueAt(i, 1);
						String email = (String) studentTable.getValueAt(i, 2);
						String age = (String) studentTable.getValueAt(i, 3);
						String program = (String) studentTable.getValueAt(i, 4);
						
						nameFld.setText(name);
						idFld.setText(sid);
						emailFld.setText(email);
						ageFld.setText(age);
						programFld.setText(program);
						
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
		
		
		//fill up Form
		JPanel formPanel = new JPanel();
		formPanel.setBounds(10, 47, 363, 137);
		formPanel.setBackground(Color.decode("#e6edf7"));;
		formPanel.setVisible(true);
		
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
		
		programFld = new JTextField();
		programFld.setBounds(228, 69, 96, 20);
		formPanel.add(programFld);
		programFld.setColumns(10);
		
		JLabel nameLbl = new JLabel("Name:");
		nameLbl.setFont(new Font("Tahoma", Font.PLAIN, 11));
		nameLbl.setBounds(10, 41, 49, 14);
		formPanel.add(nameLbl);
		
		JLabel sidLbl = new JLabel("SID:");
		sidLbl.setFont(new Font("Tahoma", Font.PLAIN, 11));
		sidLbl.setBounds(20, 72, 41, 14);
		formPanel.add(sidLbl);
		
		JLabel emailLbl = new JLabel("Email:");
		emailLbl.setFont(new Font("Tahoma", Font.PLAIN, 11));
		emailLbl.setBounds(10, 103, 49, 14);
		formPanel.add(emailLbl);
		
		JLabel ageLbl = new JLabel("Age:");
		ageLbl.setFont(new Font("Tahoma", Font.PLAIN, 11));
		ageLbl.setBounds(199, 41, 33, 14);
		formPanel.add(ageLbl);
		
		JLabel programLbl = new JLabel("Program:");
		programLbl.setFont(new Font("Tahoma", Font.PLAIN, 11));
		programLbl.setBounds(176, 72, 49, 14);
		formPanel.add(programLbl);
		
		JLabel studentFormLbl = new JLabel("Student Form Fields");
		studentFormLbl.setHorizontalAlignment(SwingConstants.CENTER);
		studentFormLbl.setBounds(10, 11, 343, 14);
		studentFormLbl.setForeground(Color.decode("#004aad"));
		formPanel.add(studentFormLbl);
		
		
		JButton clrBtn = new JButton("CLEAR");
		clrBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				idFld.setText("");
				nameFld.setText("");
				emailFld.setText("");
				ageFld.setText("");
				programFld.setText("");
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
				
				studentdbFrame frame = new studentdbFrame();
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
		int i = studentTable.getSelectedRow();
		
		if(i>=0) {
			String sid = (String) studentTable.getValueAt(i, 0);
			String name = (String) studentTable.getValueAt(i, 1);
			String email = (String) studentTable.getValueAt(i, 2);
			String age = (String) studentTable.getValueAt(i, 3);
			String program = (String) studentTable.getValueAt(i, 4);
			
			nameFld.setText(name);
			idFld.setText(sid);
			emailFld.setText(email);
			ageFld.setText(age);
			programFld.setText(program);
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
	public void displayToStudentDBList(ArrayList<String> studentList, DefaultTableModel model, String filepath) {
		try(BufferedReader reader = new BufferedReader(new FileReader(filepath))){
			List<String[]> dataList = new ArrayList<>();
			
			//read data from textfile
			String line;
			while((line = reader.readLine()) != null) {
				//read from text file and put it into arraylist
				studentList.add(line);
				
				String[] rowData = line.split(",");
				dataList.add(rowData);
			}
			reader.close();
			
			//display to table
			for(String[] rowData : dataList) {
				model.addRow(rowData);
			}
			
//			for(int item = 0; item < studentList.size(); item++) {
//				System.out.println("Idx "+item+": "+studentList.get(item));
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
	public void editFromArrList(String filepath, ArrayList<String> studentList, int idxRow, String sid, String sname, String semail, String sage, String sprogram) {
		
		//inserting edited data
		String newData = sid+","+sname+","+semail+","+sage+","+sprogram;
		studentList.set(idxRow, newData);
		
		//apply arraylist changes to textfile
		applyArrListChanges("studentdb.txt", allStudentList);
		
	}
	
	
	//delete from arraylist
	public void removeFromArrList(String filepath, ArrayList<String> studentList, int idxRow) {
		studentList.remove(idxRow);
		
		//apply arraylist changes to textfile
		applyArrListChanges("studentdb.txt", allStudentList);
	}
	
	
	public void applyArrListChanges(String filepath, ArrayList<String> studentList) {
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(filepath))){
			for(String item : studentList) {
				writer.write(item);
				writer.newLine();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}