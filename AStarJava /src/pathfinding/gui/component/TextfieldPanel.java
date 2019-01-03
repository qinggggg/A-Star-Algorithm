package pathfinding.gui.component;

import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class TextfieldPanel extends JPanel{
	
	private TextField[] text;
	private JLabel label;
	
	public TextfieldPanel(String[] string) {
        super(new GridLayout());
		text = new TextField[string.length];
		
		for(int i=0; i<string.length; i++) {
			label = new JLabel(string[i]);
			text[i] = new TextField();
			text[i].setText("30");
//			text[i].getActionListeners();
			add(label);
			add(text[i]);
		}
	}
	

	public TextField[] getTextfield() {
		return text;
	}
		
}
