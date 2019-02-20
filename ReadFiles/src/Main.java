

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


//import business.DevPHP;

public class Main extends JFrame {
	
	private static final long serialVersionUID = -6470220817562714125L;
	private JPanel contentPane;
	private PanelConsole panelConsole;
	private Reader reader;
	private int lines;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
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
	public Main() {
		setTitle("Read Several Files");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 300);
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("Files");
		menuBar.add(mnFile);

		JMenuItem mntmOpenFiles = new JMenuItem("Open Directory");
		mntmOpenFiles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mntmOpenFilesActionPerformed();
			}
		});
		mnFile.add(mntmOpenFiles);
		
		JMenuItem mntmGenerateLines = new JMenuItem("Count lines from files");
		mntmGenerateLines.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mntmCountFilesActionPerformed();
			}
		});
		mnFile.add(mntmGenerateLines);
		
		this.reader = new Reader();
		this.lines = 0;
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		panelConsole = new PanelConsole();
		contentPane.add(panelConsole, BorderLayout.CENTER);
	}

	/**
	 * Count the lines of files
	 */
	protected void mntmCountFilesActionPerformed() {
		try {
			if(this.reader.getGenerationDirectory()!=null) {
		        Stream<Path> str = Files.find(Paths.get(this.reader.getGenerationDirectory()), Integer.MAX_VALUE, (filePath, fileAttr) -> fileAttr.isRegularFile());
		        str.forEach((path)->{
		        	if(!path.toString().contains("assets")) {
		        		this.panelConsole.getTextAreaConsole().append("Directory: "+path.toString()+"\n");
		        		try {
							BufferedReader br = new BufferedReader(new FileReader(path.toString()));
							while(br.readLine()!=null) {
								lines++;
							}
							br.close();
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		        	}
		        });
		        str.close();
		        this.panelConsole.getTextAreaConsole().append("Number of lines : "+lines+"\n");
			}else {
				this.mntmOpenFilesActionPerformed();
				JOptionPane.showMessageDialog(this, "Please, set a directory", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		
	}

	/**
	 * Open the Files
	 */
	protected void mntmOpenFilesActionPerformed() {
		JFileChooser fileChooser = new JFileChooser();
	    fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		if (fileChooser.showOpenDialog(this)==JFileChooser.APPROVE_OPTION){
			File file = fileChooser.getSelectedFile();
			this.reader.setGenerationDirectory(file.getAbsolutePath());
			this.panelConsole.getTextAreaConsole().append("Reader directory: "+this.reader.getGenerationDirectory()+"\n");
		}
	}
	
	
}
