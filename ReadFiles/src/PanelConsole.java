

import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.border.TitledBorder;
import javax.swing.JScrollPane;

public class PanelConsole extends JPanel implements Observer {

	private static final long serialVersionUID = 3257246625571282387L;
	private JTextArea textAreaConsole;
	
	public JTextArea getTextAreaConsole() {
		return textAreaConsole;
	}

	public void setTextAreaConsole(JTextArea textAreaConsole) {
		this.textAreaConsole = textAreaConsole;
	}


	/**
	 * Create the panel.
	 */
	public PanelConsole() {
		setBorder(new TitledBorder(null, "Console", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new BorderLayout(0, 0));
		textAreaConsole = new JTextArea();
		textAreaConsole.setRows(8);
		textAreaConsole.setEditable(false);
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		scrollPane.setViewportView(textAreaConsole);
	}

	@Override
	public void update(Observable o, Object obj) {
		textAreaConsole.append(obj.toString());
	}
}
