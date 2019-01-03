package pathfinding.gui.component;

import java.awt.GridLayout;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;


public class JRadioBoxPanel extends JPanel
{
    private JRadioButton[] checkboxes;
  
    public JRadioBoxPanel(String[] checkboxText, ItemListener il)
    {
        super(new GridLayout(checkboxText.length, 1));
        checkboxes = new JRadioButton[checkboxText.length];
        ButtonGroup buttonGroup = new ButtonGroup();
        for (int i = 0; i < checkboxes.length; i++)
        {
            checkboxes[i] = new JRadioButton(checkboxText[i]);
            checkboxes[i].addItemListener(il);
            add(checkboxes[i]);
            buttonGroup.add(checkboxes[i]);
        }
        checkboxes[0].setSelected(true);
    }
    
  
}