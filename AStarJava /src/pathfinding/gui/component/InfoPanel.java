package pathfinding.gui.component;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import pathfinding.PathFinder;
import pathfinding.map.WeightedPoint;

/**
 * A panel to display information about the algorithm's process
 */
public class InfoPanel extends JPanel
{

    private JLabel info;

    //Formatter for displaying floats on the info panel

    DecimalFormat formatter;
       
    
    //an InfoPanel to display information about the algorithm's process
    public InfoPanel(PathFinder pf)
    {
        super(new GridBagLayout());
        formatter = new DecimalFormat("00.00");
        info = new JLabel("Info", JLabel.LEFT);
        info.setFont(new Font(Font.MONOSPACED, Font.BOLD, 12));
        add(info, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, 
                                         GridBagConstraints.NORTHWEST, 
                                         GridBagConstraints.HORIZONTAL, 
                                         new Insets(0, 0, 0, 0), 
                                         0, 0));
        updateStats(pf);
    }

    //Update the information being displayed
    public void updateStats(PathFinder pf)
    {
        String toCost = "";
        String fromCost = "";
        String totalCost = "";
        String actualCost = "";
        int maxOpen = 0;
        int maxClosed = 0;


        if (pf.getCursor() != null)
        {
            toCost = formatter.format(pf.getCursor().gethCost());
            fromCost = formatter.format(pf.getCursor().getgCost());
            totalCost = formatter.format(pf.getCursor().getTotalCost());
            actualCost = formatter.format(pf.getCursor().getActualCost());
        }

        String text = "<html><pre>";
        text += "Heuristic: " + pf.getHeuristic() + "<br>";
        text += "Directions: " + pf.getNeighbor() + "<br>";
        text += "Data Structure: " + pf.getOpenSet() + "<br>";
//        text += WeightedPoint.toLabeledString("Start", pf.getStart()) + "<br>";
//        text += WeightedPoint.toLabeledString("Goal", pf.getGoal()) + "<br>";
//        text += WeightedPoint.toLabeledString("Cursor", pf.getCursor()) + "<br>";
//        
//        text += "Open Set: " + pf.getOpenSet().size() + " nodes<br>";
//        text += "Max Open Set: " + pf.getMaxOpen() + " nodes<br>";
//
//        text += "Closed Set: " + pf.getClosedSet().size() + " nodes<br>";
//        text += "Check close:" + pf.getCountCheckClosed() + " insert close:"  + pf.getCountInsertClosed() + "<br>"+ "insert:" + pf.getCountInsert() + " delete:" + pf.getCountDelete() + 
//        		" decrease:"+pf.getCountDecrease() + "<br>" + " contains:" + pf.getCountContains() + "<br>";
//        text += "Comparisons:" + pf.getOpenSet().getComparisonNum() + " Swaps:" + pf.getOpenSet().getSwapNum() + " Contains:" + pf.getOpenSet().getContainsNum() + "<br>";
        text += 
//        		"Cursor Cost: <br>" + 
//        "g=  " + fromCost + "<br>" + 
//        "h=  " + toCost + "<br>" + 
        "f (total) = " + totalCost + "<br>";
        text += "Actual Cost: " + actualCost + "<br>";
//        text += "Solved Time: " + pf.getTime() + " ms<br>";
        text += "</pre></html>";
        info.setText(text);
               
        if(pf.getOpenSet().stackLevel()>2) {
        	System.out.println("Stack Explode!" + pf.getOpenSet().stackLevel());
        }

//        float min=10000;
//        for(int i=0; i<pf.getOpenSet().size(); i++) {
//        	if(pf.getOpenSet().getList().get(i).getTotalCost()<min) {
//        		min = pf.getOpenSet().getList().get(i).getTotalCost();
//        	}
//            System.out.println(pf.getOpenSet().getList().get(i) +" "+ pf.getOpenSet().getList().get(i).getTotalCost() +  ","); 
//        }
//        
//        System.out.println("***********" + pf.getOpenSet().size() + "*********minTotalCost:" + min + "*******stackLevel:" + pf.getOpenSet().stackLevel());
    }
    

}
