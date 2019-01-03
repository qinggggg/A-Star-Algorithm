package pathfinding.gui.component;

import javax.swing.JComboBox;


public class Dropdown extends JComboBox<Object>
{
    private String label;

    public Dropdown(String label, Object[] list)
    {
        super(list);
        this.label = label;
        this.setEditable(false);
    }

    public String getLabel()
    {
        return label;
    }

}