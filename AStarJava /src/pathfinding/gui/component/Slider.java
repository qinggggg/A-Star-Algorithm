package pathfinding.gui.component;

import javax.swing.JSlider;


public class Slider extends JSlider
{
    private String label;

    public Slider(String label, int min, int max, int value)
    {
        super(min, max, value);
        this.label = label;
    }

    public String getLabel()
    {
        return label;
    }

}