package pathfinding.gui.component;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ComboboxPanel extends JPanel{

	private JComboBox<Double>[] box;
	private JLabel label;
	
	public ComboboxPanel(List<Double[]> percentage, String[] string) {
		super(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0);
        box = new JComboBox[percentage.size()];
        for(int i=0; i<percentage.size(); i++) {
        	label = new JLabel(string[i]);
        	box[i] = new JComboBox<Double>(percentage.get(i));
        	add(label);
    		add(box[i]);
    		gbc.anchor = GridBagConstraints.EAST;
            gbc.gridx = 0;

            gbc.anchor = GridBagConstraints.WEST;
            gbc.gridx = 1;
            add(box[i], gbc);
            gbc.gridy++;
        }
		
		 
	}
	
	public JComboBox<Double>[] getBox() {
		return box;
	}
	
}
