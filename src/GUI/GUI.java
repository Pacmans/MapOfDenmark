package GUI;


import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import controller.Controller;

public class GUI {

	// This field contains the current version of the program.
    private static final String VERSION = "Version 1.0";
        // The main frame of our program.
    private JFrame frame;
    private JPanel contentPane;
    private int number = 1;
    private Controller controller;

    public GUI() {
    controller = Controller.getInstance();
		makeFrame();
		makeMenuBar();
		makeMap(Controller.getMap());
  	makeRightPanel();
		setupFrame();
    }
    
    
	private void setupFrame() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setSize(800, 600);
		// place the frame at the center of the screen and show.
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(d.width/2 - frame.getWidth()/2, 
				d.height/2 - frame.getHeight()/2);
		// make the user unable to resize the window.
		frame.setResizable(false);
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
				      "\nMade by Claus, Bj�rn, Phillip, Morten & Anders.",
				      "About Map Of Denmark", 
				      JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void makeFrame() {
    	// create the frame set the layout and border.
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
    	
    	// create Menu2 menu
    	menu = new JMenu("Menu2");
    	menubar.add(menu);
    	
    	// create the menu item1 . 
    	item = new JMenuItem("Menuitem1");
    	item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, SHORTCUT_MASK));
    	item.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) { 
    			System.out.println("Menuitem1"); } } );
    	menu.add(item);

    	// create Help menu
    	menu = new JMenu("Help");
    	menubar.add(menu);
    	
    	item = new JMenuItem("About Map Of Denmark");
    	item.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) { 
    			showAbout(); } } );
    	menu.add(item);
    }
    
	private void makeMap(JComponent map) {
		JPanel mapPanel = new JPanel();
		mapPanel.add(map);
		contentPane.add(mapPanel,"Center");
	}

	private void makeRightPanel() {
		// initialize a new JPanel.
		JPanel optionPanel = new JPanel();
		// create a vertical BoxLayout on the optionPanel.
		optionPanel.setLayout(new BoxLayout(optionPanel,BoxLayout.Y_AXIS));

		// add the checkbox, and the other GUI to the right panel.
		optionPanel.add(createCheckbox());
		optionPanel.add(Box.createRigidArea(new Dimension(50,350)));
		// add the optionPanel to the contentPanes borderlayout.
		contentPane.add(optionPanel,"East");
	}

	private JPanel createCheckbox() {
		// initialize checkboxPanel
		JPanel checkboxPanel = new JPanel(new GridLayout(7, 1));
		checkboxPanel.setBorder(new TitledBorder(new EtchedBorder(), "Vejtyper"));
		
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
		box.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent e) {
//				controller.updateMap(number, ((JCheckBox) e.getSource()).isSelected());
			}
		});
		fl.add(box);
		number++;
		return fl;
	}

}
