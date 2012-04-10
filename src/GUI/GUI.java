package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import controller.Controller;
import dataStructure.Connection;

public class GUI extends JComponent {

	// This field contains the current version of the program.
    private static final String VERSION = "Version 1.0";
        // The main frame of our program.
    private JFrame frame;
    private JPanel contentPane, mapPanel;
	private int number = 1;
	// Coordinates for zoom box
	private int xClicked, yClicked, xMove, yMove;
	// The map from the controller
	private JComponent map;
	private boolean isMouseDown;
	
    public GUI() {
    	Controller.getInstance();
		makeFrame();
		makeMenuBar();
		map = Controller.getMap();
		createZoomRect(map);
		makeMap(map);
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
		frame.setVisible(true);
		frame.addComponentListener(new ComponentAdapter(){
			public void componentResized(ComponentEvent e) {
				 Controller.scaleMap(mapPanel.getWidth(),mapPanel.getHeight());
				 mapPanel.updateUI();
			}
		});
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
    
	private void makeMap(JComponent map) {
		mapPanel = new JPanel();
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
		optionPanel.add(createZoomOutButton());
		optionPanel.add(Box.createRigidArea(new Dimension(50,350)));
		// add the optionPanel to the contentPanes borderlayout.
		contentPane.add(optionPanel,"East");
	}

	private JPanel createZoomOutButton() {
		JPanel zoomPanel = new JPanel(new FlowLayout(1));
		JButton zoomOut = new JButton("Zoom out");
		zoomOut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { 
            	Controller.showAll();
             } } );
		zoomPanel.add(zoomOut);
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
				Controller.updateMap(number, e.getStateChange());
				
			}
		});
		fl.add(box);
		number++;
		return fl;
	}
	
	public void paint(Graphics g) {
		if(isMouseDown) {
		g.setColor(new Color(255,255,255));
		g.drawRect(xClicked, yClicked, xMove-xClicked, yMove-yClicked);
		}
	}
	
	private void createZoomRect(JComponent map) {
		map.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				xClicked = e.getX();
				yClicked = e.getY();
				isMouseDown = true;
				repaint();
			}

			public void mouseDragged(MouseEvent e) {
				isMouseDown = true;
				xMove = e.getX();
				yMove = e.getY();
				repaint();
			}
			
			public void mouseReleased(MouseEvent e) {
				xClicked = -10;
				yClicked = -10;
				xMove = -10;
				yMove = -10;
				isMouseDown = false;
				repaint();
			} 
		});
	}
	
	public Dimension getPreferredSize() {
		return (new Dimension(map.getPreferredSize()));	
	}
	
	public Dimension getMinimumSize() {
		return getPreferredSize();
	}

}
