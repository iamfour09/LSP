import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class LSPFrame extends JFrame implements ActionListener {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	// Components of the Form
	private Container c;
	private JLabel title;
	private JTextField filePathField;
	private JButton sub;
	private JButton selectFile;
	private JButton reset;
	private JTextArea tout;

	public LSPFrame() {
		setTitle("Longest Steepest Path");
		setBounds(300, 90, 600, 450);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);

		c = getContentPane();
		c.setLayout(null);

		title = new JLabel("Longest Steepest Path");
		title.setFont(new Font("Arial", Font.PLAIN, 30));
		title.setSize(350, 30);
		title.setLocation(150, 30);
		c.add(title);

		selectFile = new JButton("Select File");
		selectFile.setFont(new Font("Arial", Font.PLAIN, 15));
		selectFile.setSize(120, 20);
		selectFile.setLocation(100, 100);
		selectFile.addActionListener(this);
		c.add(selectFile);

		filePathField = new JTextField();
		filePathField.setFont(new Font("Arial", Font.PLAIN, 15));
		filePathField.setSize(260, 20);
		filePathField.setLocation(240, 100);
		filePathField.setEditable(false);
		c.add(filePathField);

		tout = new JTextArea();
		tout.setFont(new Font("Arial", Font.PLAIN, 15));
		tout.setSize(400, 150);
		tout.setLocation(100, 150);
		tout.setLineWrap(true);
		tout.setEditable(false);
		c.add(tout);

		sub = new JButton("Process");
		sub.setFont(new Font("Arial", Font.PLAIN, 15));
		sub.setSize(100, 20);
		sub.setLocation(200, 350);
		sub.addActionListener(this);
		c.add(sub);

		reset = new JButton("Reset");
		reset.setFont(new Font("Arial", Font.PLAIN, 15));
		reset.setSize(100, 20);
		reset.setLocation(320, 350);
		reset.addActionListener(this);
		c.add(reset);

		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == sub) {
			String filePath = filePathField.getText();
			if (!filePath.isBlank()) {
				LongestSteepestPath finder = new LongestSteepestPath(filePathField.getText());
				CalculatedResultData result = finder.findLongestAndSteepestPath();
				tout.setText(result.toString());
				tout.setEditable(false);
			}
		} else if (e.getSource() == reset) {
			String def = "";
			filePathField.setText(def);
			tout.setText(def);
		} else if (e.getSource() == selectFile) {
			String selectedFilePath = "";
			JFileChooser jFileChooser = new JFileChooser(System.getProperty("user.dir"));
			jFileChooser.setAcceptAllFileFilterUsed(false);
			FileNameExtensionFilter fileNameExtensionFilter = new FileNameExtensionFilter("Text file", "txt");
			jFileChooser.addChoosableFileFilter(fileNameExtensionFilter);

			int checkInput = jFileChooser.showOpenDialog(null);

			if (checkInput == JFileChooser.APPROVE_OPTION) {
				File openedFile = jFileChooser.getSelectedFile();
				selectedFilePath = openedFile.getAbsolutePath();
				filePathField.setText(selectedFilePath);
			}
		}
	}
}
