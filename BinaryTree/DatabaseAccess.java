
package Project2_CS260_DevanshAgrawal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;

/**
 * @author Devansh Agrawal
 *
 */
public class DatabaseAccess extends JFrame implements Serializable {

	private BinaryTree<Marks> binarySearchTree;

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		DatabaseAccess gui = new DatabaseAccess();
		gui.setVisible(true);
	}

	private JPanel buttonPanel;
	private JPanel ListPanel;
	private JPanel fPanel;
	private JPanel sPanel;
	private JButton addNodeButton;
	private JButton removeNodeButton;
	private JButton findNodeButton;
	private JMenuBar menuBar;
	private JMenu file;
	private JMenuItem saveItem;
	private JMenuItem printtree;
	private JMenuItem loadItem;
	private JMenuItem exitItem;
	private JTextArea printArea;
	private JTextField employeeNameInputField;
	private JTextField serialNumberField;
	private JScrollPane scroll;
	private JLabel employeeNameInputMessage;
	private JLabel message;

	public DatabaseAccess() {
		setSize(800, 605);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Employee Recorder");
		setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - getSize().width) / 2,
				(Toolkit.getDefaultToolkit().getScreenSize().height - getSize().height) / 2);
		getContentPane().setLayout(new FlowLayout());
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		file = new JMenu("FILE");
		file.setMnemonic(KeyEvent.VK_F);
		menuBar.add(file);
		saveItem = new JMenuItem("Save File");
		saveItem.setMnemonic(KeyEvent.VK_S);
		file.add(saveItem);
		loadItem = new JMenuItem("Load File");
		loadItem.setMnemonic(KeyEvent.VK_L);
		file.add(loadItem);
		printtree = new JMenuItem("Print Tree");
		printtree.setMnemonic(KeyEvent.VK_P);
		file.add(printtree);
		exitItem = new JMenuItem("Exit File");
		exitItem.setMnemonic(KeyEvent.VK_X);
		file.add(exitItem);
		MenuItemListener menuItemListener = new MenuItemListener();
		exitItem.addActionListener(menuItemListener);
		printtree.addActionListener(menuItemListener);
		saveItem.addActionListener(menuItemListener);
		loadItem.addActionListener(menuItemListener);
		buttonPanel = new JPanel();
		buttonPanel.setBorder(BorderFactory.createTitledBorder("Control Panel"));
		buttonPanel.setLayout(new GridLayout(2, 1));
		getContentPane().add(buttonPanel);
		fPanel = new JPanel();
		buttonPanel.add(fPanel);
		addNodeButton = new JButton("Add Serial Number");
		fPanel.add(addNodeButton);
		removeNodeButton = new JButton("Delete Serial Number");
		fPanel.add(removeNodeButton);

		findNodeButton = new JButton("Find Serial Nummber");
		fPanel.add(findNodeButton);

		sPanel = new JPanel();
		buttonPanel.add(sPanel);

		employeeNameInputMessage = new JLabel("Enter Name of Employee");
		
		sPanel.add(employeeNameInputMessage);

		employeeNameInputField = new JTextField();
		employeeNameInputField.setColumns(7);
		sPanel.add(employeeNameInputField);

		message = new JLabel("<html>Enter Serial Number");
		sPanel.add(message);

		serialNumberField = new JTextField("");
		serialNumberField.setColumns(7);
		sPanel.add(serialNumberField);

		ListPanel = new JPanel();
		
		ListPanel.setBorder(BorderFactory.createTitledBorder("DISPLAY"));
		getContentPane().add(ListPanel);
		printArea = new JTextArea();
		scroll = new JScrollPane(printArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		ListPanel.add(scroll);
		
		printArea.setEditable(false);
		printArea.setColumns(60);
		printArea.setRows(20);

		ButtonListener buttonListener = new ButtonListener();
		addNodeButton.addActionListener(buttonListener);
		removeNodeButton.addActionListener(buttonListener);
		findNodeButton.addActionListener(buttonListener);

	}

	/**
	 * Load the data from a .txt file
	 * 
	 */
	@SuppressWarnings("unchecked")
	private void loadDatabaseFromFile() {
		try {
			FileInputStream fileInputStream = new FileInputStream("data.txt");
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

			binarySearchTree = (BinaryTree<Marks>) objectInputStream.readObject();

			fileInputStream.close();
			objectInputStream.close();
		} catch (IOException | ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Cannot load the save file due to some reasons.", "ERROR",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Save the database to both the files

	 */
	private void saveDatabaseToFile() {
		FileOutputStream fileOutputStream;
		try {
			PrintWriter saveFile = new PrintWriter("Database.txt");
			saveFile.print(binarySearchTree.toString());
			fileOutputStream = new FileOutputStream("data.txt");
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
			objectOutputStream.writeObject(binarySearchTree);
			objectOutputStream.flush();
			objectOutputStream.close();
			saveFile.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Cannot save to a file due to some reasons.", "ERROR!",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	/**
	 * Used to Add the Node to the Tree
	 * 
	 * @param marks the key to be added.
	 */
	public void addEntry(Marks marks) {
		binarySearchTree.addNode(marks);
	}

	/**
	 * Used this to delete the entry from the Node
	 * 
	 * @param marks The data to be removed
	 */
	public void removeEntry(Marks marks) {
		binarySearchTree.removeNodeWithData(marks);
	}

	/**
	 * Used to Find an entry from the Tree
	 * 
	 * @param marks The data to be found
	 * @return The Node that contains the required key or null
	 */
	public BTSNode<Marks> findEntry(Marks marks) {
		return binarySearchTree.findNodeWithData(marks);
	}

/**
 * All the Button Listeners. 
 *
 */
	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == addNodeButton) {
				if (employeeNameInputField.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please DO NOT leave the fileds empty", "WARNING",JOptionPane.WARNING_MESSAGE);
				} else {
					String employeeName = employeeNameInputField.getText();
					int serialNumberOfEmployee = Integer.parseInt(serialNumberField.getText());
					employeeNameInputField.setText("");
					serialNumberField.setText("");
					Marks marks = new Marks(employeeName, serialNumberOfEmployee);
					addEntry(marks);
					// displayArea.setText("TEST");
					printArea.setText(binarySearchTree.toString());
				}

			} else if (e.getSource() == removeNodeButton) {

				if (employeeNameInputField.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please fill out all the information", "WARNING",
							JOptionPane.WARNING_MESSAGE);
				} else {
					String employeeName = employeeNameInputField.getText();
					int serialNumberOfEmployee = Integer.parseInt(serialNumberField.getText());
					employeeNameInputField.setText("");
					serialNumberField.setText("");

					Marks marks = new Marks(employeeName, serialNumberOfEmployee);
					removeEntry(marks);
					printArea.setText(binarySearchTree.toString());
				}

			} else if (e.getSource() == findNodeButton) {
				if (employeeNameInputField.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please fill out all the information", "WARNING",
							JOptionPane.WARNING_MESSAGE);
				} else {
					String employeeName = employeeNameInputField.getText();
					int serialNumberOfEmployee = Integer.parseInt(serialNumberField.getText());
					employeeNameInputField.setText("");
					serialNumberField.setText("");

					Marks marks = new Marks(employeeName, serialNumberOfEmployee);
					findEntry(marks);
				}

			}

		}
	}
/**
  * All the MenuItemListeners
  */
	private class MenuItemListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == printtree) {
				printArea.setText(" ");
				printArea.setText(binarySearchTree.printTreeDiagram());
			}

			else if (e.getSource() == exitItem) {
				saveDatabaseToFile();
				System.exit(0);
			} else if (e.getSource() == saveItem) {
				saveDatabaseToFile();
			}

			else if (e.getSource() == loadItem) {
				loadDatabaseFromFile();

			}
		}
	}

}
