package pathfinding.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import pathfinding.PathFinder;
import pathfinding.StatusEnum;
import pathfinding.data.structure.StructureSelector;
import pathfinding.gui.component.ButtonPanel;
import pathfinding.gui.component.ComboboxPanel;
import pathfinding.gui.component.Dropdown;
import pathfinding.gui.component.DropdownPanel;
import pathfinding.gui.component.InfoPanel;
import pathfinding.gui.component.JRadioBoxPanel;
import pathfinding.gui.component.Slider;
import pathfinding.gui.component.SliderPanel;
import pathfinding.gui.component.TextfieldPanel;
import pathfinding.map.generator.MapGenerator;
import pathfinding.map.generator.MapManager;
import pathfinding.map.heuristic.HeuristicScheme;
import pathfinding.map.neighbor.NeighborSelector;

/**
 * The toolbox GUI component for interacting with the algorithm
 */
public class ToolboxPanel extends JPanel
{

    private MapPanel mp;

    //The PathFinder runs the algorithm
    private PathFinder pf;
    
    private MapManager mManager;
    
    private MapGenerator mGenerator;

    private final String BUTTON_TEXT_GENERATE = "Generate";
    private final String BUTTON_TEXT_STEP = "Step";
    private final String BUTTON_TEXT_SOLVE = "Solve";
    private final String BUTTON_TEXT_RESET = "Reset";

    private final String DROPDOWN_TEXT_HEURISTICS = "Heuristics";
    private final String DROPDOWN_TEXT_NEIGHBORS = "Neighbors";
    private final String DROPDOWN_TEXT_STRUCTURES = "Structures";

    private final String SLIDER_TEXT_SPEED = "Solve Delay";
    private final String SLIDER_TEXT_SIZE = "Zoom";

    private final String CHECKBOX_TEXT_CLOSED = "Check Closed List";
    private final String CHECKBOX_TEXT_OPEN = "Check Open List";
    private final String CHECKBOX_TEXT_HASH = "Check Duplicated Open List";
    
    private final String CHECKBOX_TEXT_Array = "Unsorted Array";
    private final String CHECKBOX_TEXT_Min = "Min-Heap";
    private final String CHECKBOX_TEXT_Multi = "Multi-Stack Heap";

    private final String MAP_HEIGHT = "Map Height: ";
    private final String MAP_WIDTH = " Map Width: ";
    
    private final Double[] percentage = new Double[]{0.0,0.05, 0.1, 0.15, 0.2, 0.25, 0.3, 0.35, 0.4, 0.45, 0.5, 0.55, 0.6};
    private List<Double[]> list= new ArrayList<Double[]>();
    
    
    private final String COMBOBOX_OBSTACLES = "Obstacles: ";
   
//    private long startTime;

    //The slider to zoom in or out of the map panel, made a member so the scroll wheel listener can modify it
    private Slider zoomSlider;

    //Timer to increment the steps in the algorithm
    private Timer solveTimer;

    //The panel for displaying information about the algorithm
    private InfoPanel infoPanel;

    public ToolboxPanel(MapPanel mp, PathFinder pf, MapManager mManager, MapGenerator mGenerator)
    {
        this.mp = mp;
        this.pf = pf;
        this.mManager = mManager;
        this.mGenerator = mGenerator;
        buildGui();
        
    }

    //Build the GUI
    private void buildGui()
    {
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(8, 8, 8, 8), 0, 0);
       
        list.add(percentage);
        ComboboxPanel comboPanel = new ComboboxPanel(list, new String[] {COMBOBOX_OBSTACLES});
        
        TextfieldPanel tPanel = new TextfieldPanel(new String[] {MAP_HEIGHT, MAP_WIDTH});
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent e )
            {
                if (BUTTON_TEXT_GENERATE.equals( ((JButton)e.getSource()).getText()) )
                {
                	pf.getOpenSet().clear();
                	pf.getOpenSet().setComparisonNum(0);
                	pf.getOpenSet().setContainsNum(0);
                	pf.getOpenSet().setSwapNum(0);
                	pf.setCountCheckClosed(0);
                	pf.setCountInsertClosed(0);
                	pf.setCountContains(0);
                	pf.setCountDecrease(0);
                	pf.setCountDelete(0);
                	pf.setCountInsert(0);
                	mManager.setHeight(Integer.parseInt(tPanel.getTextfield()[0].getText()));
                	mManager.setWidth(Integer.parseInt(tPanel.getTextfield()[1].getText()));
                	mGenerator.setPercent((double)comboPanel.getBox()[0].getSelectedItem());
                    regenerateMap(true, mManager.getHeight(), mManager.getWidth(), mGenerator.getPercent());
                }
                else if (BUTTON_TEXT_STEP.equals( ((JButton)e.getSource()).getText()) )
                {
                    pf.step();
                }
                else if (BUTTON_TEXT_SOLVE.equals( ((JButton)e.getSource()).getText()) )
                {
                   
                	pf.getOpenSet().setComparisonNum(0);
                	pf.getOpenSet().setContainsNum(0);
                	pf.getOpenSet().setSwapNum(0);
                	pf.setCountCheckClosed(0);
                	pf.setCountInsertClosed(0);
                	pf.setCountContains(0);
                	pf.setCountDecrease(0);
                	pf.setCountDelete(0);
                	pf.setCountInsert(0);
                    if (solveTimer.isRunning())
                    {
                    	solveTimer.stop();
                    }
                    else
                    {
                    	if (pf.getStatus() != StatusEnum.RUNNING) {
                            pf.getOpenSet().clear();
                            pf.getOpenSet().setComparisonNum(0);
                        	pf.getOpenSet().setContainsNum(0);
                        	pf.getOpenSet().setSwapNum(0);
                            pf.setCountCheckClosed(0);
                        	pf.setCountInsertClosed(0);
                        	pf.setCountContains(0);
                        	pf.setCountDecrease(0);
                        	pf.setCountDelete(0);
                        	pf.setCountInsert(0);
                            pf.reset();
                                
                        }
                            	
                        solveTimer.start();
                    }
                	
                }
                else if (BUTTON_TEXT_RESET.equals( ((JButton)e.getSource()).getText()) )
                {
                	pf.getOpenSet().setComparisonNum(0);
                	pf.getOpenSet().setContainsNum(0);
                	pf.getOpenSet().setSwapNum(0);
                	pf.setCountCheckClosed(0);
                	pf.setCountInsertClosed(0);
                	pf.setCountContains(0);
                	pf.setCountDecrease(0);
                	pf.setCountDelete(0);
                	pf.setCountInsert(0);
                	pf.getOpenSet().clear();
                    pf.reset();
                    solveTimer.stop();
                    
                }
                mp.updateDrawing();
                infoPanel.updateStats(pf);
            }
        };

        ButtonPanel buttonPanel = new ButtonPanel(new String[] {BUTTON_TEXT_STEP, 
                                                                BUTTON_TEXT_SOLVE, 
                                                                BUTTON_TEXT_RESET,
                                                                BUTTON_TEXT_GENERATE}, 
                                                                al);

  
        
        solveTimer = new Timer(2, new ActionListener(){
            public void actionPerformed( ActionEvent e )
            {
            	pf.step();
                mp.updateDrawing();
                infoPanel.updateStats(pf);
                if (pf.getStatus() != StatusEnum.RUNNING)
                	solveTimer.stop();
                
            }
        });

        Dropdown[] dropdowns = new Dropdown[3];

        ActionListener dal = new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent e )
            {
                Dropdown d = ((Dropdown)e.getSource());
                if (DROPDOWN_TEXT_HEURISTICS.equals(d.getLabel()))
                {
                    pf.setHeuristic((HeuristicScheme)d.getSelectedItem());
                }
                else if (DROPDOWN_TEXT_NEIGHBORS.equals(d.getLabel()))
                {
                    pf.setNeighborSelector((NeighborSelector)d.getSelectedItem());
//             
                }else if(DROPDOWN_TEXT_STRUCTURES.equals(d.getLabel())) {
                	pf.setOpenSet((StructureSelector<?>) d.getSelectedItem());
                }
            }

			
        };

        dropdowns[0] = new Dropdown(DROPDOWN_TEXT_HEURISTICS, HeuristicScheme.getAllHeuristics() );
        dropdowns[1] = new Dropdown(DROPDOWN_TEXT_NEIGHBORS, NeighborSelector.getAllNeighborSelectors() );
        dropdowns[2] = new Dropdown(DROPDOWN_TEXT_STRUCTURES, StructureSelector.getAllDataStructures());

        DropdownPanel dropdownPanel = new DropdownPanel(dropdowns, dal);

        pf.setHeuristic((HeuristicScheme)(dropdownPanel.getDropdowns()[0].getSelectedItem()));
        pf.setNeighborSelector((NeighborSelector)(dropdownPanel.getDropdowns()[1].getSelectedItem()));
        pf.setOpenSet((StructureSelector<?>) dropdownPanel.getDropdowns()[2].getSelectedItem());
        
        
       

        Slider[] sliders = new Slider[2];

        ChangeListener cl = new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e)
            {
                Slider s = (Slider)e.getSource();
                if (SLIDER_TEXT_SPEED.equals(s.getLabel()))
                {
                    setSolveDelay(s.getValue());
                }
                else if (SLIDER_TEXT_SIZE.equals(s.getLabel()))
                {
                    mp.setTileSize(s.getValue());
                    mp.enforceBoundaries();
                    mp.updateDrawing();
                }
            }
        };

        sliders[0] = new Slider(SLIDER_TEXT_SPEED, 0, 1000, 0);
        zoomSlider = new Slider(SLIDER_TEXT_SIZE, 6, 32, 16);
        sliders[1] = zoomSlider;

        setSolveDelay(sliders[0].getValue());
        mp.setTileSize(sliders[1].getValue());

        SliderPanel sliderPanel = new SliderPanel(sliders, cl);

        ItemListener il = new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e)
            {
                JRadioButton cb = (JRadioButton)e.getSource();
//            	
                if (CHECKBOX_TEXT_OPEN.equals(cb.getText()))
                {	
                    pf.setCheckOpen(cb.isSelected());
//                    cb.setSelected(true);
                }
               
                else if (CHECKBOX_TEXT_CLOSED.equals(cb.getText()))
                {
                	pf.setCheckClosed(cb.isSelected());
                	
                }
            }
            	
//               
            
        };

        
        JRadioBoxPanel checkboxPanel = new JRadioBoxPanel(new String[] {
        		CHECKBOX_TEXT_OPEN, CHECKBOX_TEXT_CLOSED}, 
        		il); 

//        ItemListener il_ = new ItemListener() {
//            @Override
//            public void itemStateChanged(ItemEvent e)
//            {
//                JRadioButton cb = (JRadioButton)e.getSource();
////            	
//                if (CHECKBOX_TEXT_Array.equals(cb.getText()))
//                {	
//                	pf.setUnsortedArray(cb.isSelected());
//                }
//               
//                else if (CHECKBOX_TEXT_Min.equals(cb.getText()))
//                {
//                	pf.setMinHeap(cb.isSelected());
//                	
//                }else if(CHECKBOX_TEXT_Multi.equals(cb.getText())) 
//                {
//                	pf.setMultiStack(cb.isSelected());
//                	
//                }
//            }
//            	
////               
//            
//        };
//
//        
//        JRadioBoxPanel checkboxPanel_ = new JRadioBoxPanel(new String[] {
//        		CHECKBOX_TEXT_Array, CHECKBOX_TEXT_Min, CHECKBOX_TEXT_Multi}, 
//        		il_); 
//        
      
        infoPanel = new InfoPanel(pf);
        

        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        
        add(tPanel, gbc);
//        gbc.gridy++;
//        add(wLabel, gbc);
        gbc.gridy++;
        add(dropdownPanel, gbc);
        
        gbc.gridy++;
        add(comboPanel, gbc);
        gbc.gridy++;
        add(buttonPanel, gbc);
       
        gbc.gridy++;
        add(sliderPanel, gbc);
        
        gbc.gridy++;
        add(checkboxPanel, gbc);
        
        gbc.gridy++;
//        add(checkboxPanel_, gbc);
//        gbc.gridy++;
        
        gbc.weighty = 1.0f;
        add(infoPanel, gbc);
    }

    //Set the solve delay time
    private void setSolveDelay(int delay)
    {
        solveTimer.setDelay(delay);
    }

    //Randomly generate a tilemap
    private void regenerateMap(boolean reseed, int height, int width, double percent)
    {
        pf.reset(mManager.getInstance().generate(reseed, height, width, percent));
        mp.centerMap();
        mp.updateDrawing();
        infoPanel.updateStats(pf);
    }
    
//    private void regenerateMap(boolean b) {
//		// TODO Auto-generated method stub
//    	pf.reset(mManager.getInstance().generate(b));
//        mp.centerMap();
//        mp.updateDrawing();
//        infoPanel.updateStats(pf);
//	}

    //Randomly generate a tilemap using the specified random number generator seed
    public void regenerateMap(int seed, int height, int width, double percent)
    {
    	
        pf.reset(mManager.getInstance().generate(seed, height, width, percent));
        mp.updateDrawing();
        infoPanel.updateStats(pf);
    }


    //Increment the zoom
    public void incrementZoom(int amt)
    {
        int newValue = zoomSlider.getValue() + amt;
        if (newValue >= zoomSlider.getMinimum() && newValue <= zoomSlider.getMaximum())
        {
            zoomSlider.setValue(newValue);
        }
    }


}
