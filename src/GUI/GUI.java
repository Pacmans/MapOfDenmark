package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

import controller.Controller;

/**
 * 
 * @author Anders
 * @author Pacmans
 * @version 10. April 2012
 *
 */

public class GUI {

	// This field contains the current version of the program.
    private static final String VERSION = "Version 1.0";
    // The main frame of our program.
    private JFrame frame;
    private JPanel contentPane, mapPanel;
    
    
	private int number = 1;
	// The map from the controller


	private JComponent map;

    public GUI() {
    	//...
    	Controller.getInstance();
    	map = Controller.getMap();
    	
    	//creates the gui
		makeFrame();
		makeMenuBar();
		createMapPanel(map);
		createOptionPanel();
		setupFrame();
    }
    
	private void setupFrame() {
		frame.pack();
		frame.setSize(800, 600);
		// place the frame at the center of the screen and show.
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(d.width/2 - frame.getWidth()/2, 
				d.height/2 - frame.getHeight()/2);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}


	public void quit() {
		// Exits the application.
		System.exit(0);
	}

	/**
     * Creates a message dialog which shows 
     * the current version of the application.
     */
    private void showAbout() {
	JOptionPane.showMessageDialog(frame, 
				      "Map Of Denmark - " + VERSION + 
				      "\nMade by Claus, Bj¿rn, Phillip, Morten & Anders.",
				      "About Map Of Denmark", 
				      JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Creates the frame and sets the layout and border.
     */
    private void makeFrame() {
    	frame = new JFrame("Map Of Denmark");
    	contentPane = (JPanel) frame.getContentPane();
    	contentPane.setBorder(new EmptyBorder(6, 6, 6, 6));
    	contentPane.setLayout(new BorderLayout(10, 10));
    }
    
    private void makeMenuBar() {
    	// Create key stroke shortcuts for the menu.
    	final int SHORTCUT_MASK =
    	    Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();
    	
    	// make the JMenuBar
    	JMenuBar menubar = new JMenuBar();
    	frame.setJMenuBar(menubar);
    	
    	// create the JMenu fields.
    	JMenu menu;
    	JMenuItem item;
    	
    	// create File menu
    	menu = new JMenu("File");
    	menubar.add(menu);
    	
    	// make a new menu item and add a setAccelerator to use of shortcuts.
    	item = new JMenuItem("Quit");
    	item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, SHORTCUT_MASK));
    	// create an actionlistener and call the method quit() when chosen.
    	item.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) { quit(); } } );
    	menu.add(item);
    	
    	// create Help menu
    	menu = new JMenu("Help");
    	menubar.add(menu);
    	
    	item = new JMenuItem("About Map Of Denmark");
    	item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, SHORTCUT_MASK));
    	item.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) { 
    			showAbout(); } } );
    	menu.add(item);
    }
    	
    /**
     * makes the Panel that contains the map component
     */
	private void createMapPanel(JComponent map) {
		mapPanel = new JPanel(new GridLayout(1,1));
		mapPanel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.darkGray));
		mapPanel.add(map);
		contentPane.add(mapPanel,"Center");
	}

	/**
	 * creates the right panel that contains checkboxes and zoom out button
	 */
	private void createOptionPanel() {
		JPanel optionPanel = new JPanel();
		// create a vertical BoxLayout on the optionPanel.
		optionPanel.setLayout(new BoxLayout(optionPanel,BoxLayout.Y_AXIS));

		// add the checkbox, and the other GUI to the right panel.
		optionPanel.add(createCheckbox());
		optionPanel.add(createZoomOutButton());
		optionPanel.add(Box.createRigidArea(new Dimension(50,350)));
		// add the optionPanel to the contentPanes borderlayout.
		contentPane.add(optionPanel,"East");
	}

	private JPanel createZoomOutButton() {
		JPanel zoomPanel = new JPanel(new FlowLayout(1));
		JButton resetZoom = new JButton("Reset Zoom");
		resetZoom.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { 
            	Controller.showAll();
             } } );
		zoomPanel.add(resetZoom);
		return zoomPanel;
	}

	private JPanel createCheckbox() {
		// initialize checkboxPanel
		JPanel checkboxPanel = new JPanel(new GridLayout(7, 1));
		checkboxPanel.setBorder(new TitledBorder(new EtchedBorder(), "Road types"));
		// fill the checkboxPanel
		checkboxPanel.add(createRoadtypeBox("Highways", true)); // Priority 1 roads
		checkboxPanel.add(createRoadtypeBox("Expressways", true)); // Priority 2 roads
		checkboxPanel.add(createRoadtypeBox("Primary roads", true)); // and so on..
		checkboxPanel.add(createRoadtypeBox("Secondary roads", false));
		checkboxPanel.add(createRoadtypeBox("Normal roads", false));
		checkboxPanel.add(createRoadtypeBox("Trails & streets", false));
		checkboxPanel.add(createRoadtypeBox("Paths", false));
		return checkboxPanel;
	}

	private JPanel createRoadtypeBox(String string, boolean selected) {
		JPanel fl = new JPanel(new FlowLayout(0));
		JCheckBox box = new JCheckBox(string);
		box.setSelected(selected);
		box.addItemListener(new ItemListener() {
			@SuppressWarnings("deprecation")
			public void itemStateChanged(ItemEvent e) {
				if(((AbstractButton) e.getItem()).getLabel() == "Highways")number = 1;
				else if(((AbstractButton) e.getItem()).getLabel() == "Expressways")number = 2;
				else if(((AbstractButton) e.getItem()).getLabel() == "Primary roads")number = 3;
				else if(((AbstractButton) e.getItem()).getLabel() == "Secondary roads")number = 4;
				else if(((AbstractButton) e.getItem()).getLabel() == "Normal roads")number = 5;
				else if(((AbstractButton) e.getItem()).getLabel() == "Trails & streets")number = 6;
				else if(((AbstractButton) e.getItem()).getLabel() == "Paths")number = 7;
				if (e.getStateChange() == 1)
					Controller.updateMap(number, true);
				else
					Controller.updateMap(number, false);

			}
		});
		fl.add(box);
		number++;
		return fl;
	}

	public Dimension getPreferredSize() {
		return (new Dimension(map.getPreferredSize()));	
	}

	public Dimension getMinimumSize() {
		return getPreferredSize();
	}

}