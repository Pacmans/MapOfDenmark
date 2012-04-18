package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import controller.Controller;

/**
 * 
 * @author Anders
 * @author Pacmans
 * @version 10. April 2012
 *
 */

@SuppressWarnings("serial")
public class GUI extends JComponent {

	// This field contains the current version of the program.
    private static final String VERSION = "Version 1.0";
        // The main frame of our program.
    private JFrame frame;
    private JPanel contentPane, mapPanel, loadingPanel;
	private int number = 1;
	// Coordinates for zoom box
	private int xClicked, yClicked, xMove, yMove;
	// The map from the controller
	private JComponent map;
	// A ButtonGroup with car, bike, and walk.
	private ButtonGroup group;
	// selected JToggleButton - 0 if car, 1 if bike, 2 if walk.
	private int selectedTransport = 0;
	private boolean isMouseDown;
	
    public GUI() {
		makeFrame();
		makeMenuBar();
		makeRightPanel();
		setupFrame();
		Controller.getInstance();
		setupMap();
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
		
	}

	private void setupMap() {
		map = Controller.getMap();
		createZoomRect(map);
		mapPanel = new JPanel();
		mapPanel.add(map);
		contentPane.remove(loadingPanel);
		contentPane.add(mapPanel,"Center");
		frame.addComponentListener(new ComponentAdapter(){
			public void componentResized(ComponentEvent e) {
				 Controller.scaleMap(mapPanel.getWidth(),mapPanel.getHeight());
				 mapPanel.updateUI();
			}
		});
		frame.pack();
		frame.setSize(800, 600);
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
				      "\nMade by Claus, Bjørn, Phillip, Morten & Anders.",
				      "About Map Of Denmark", 
				      JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void makeFrame() {
    	// create the frame set the layout and border.
    	frame = new JFrame("Map Of Denmark");
    	contentPane = (JPanel) frame.getContentPane();
    	contentPane.setBorder(new EmptyBorder(4, 4, 4, 4));
    	contentPane.setLayout(new BorderLayout(5, 5));
    	loadingPanel = new JPanel(new FlowLayout(1));
    	loadingPanel.setBorder(new EmptyBorder(150, 6, 6, 6));
    	loadingPanel.add(new JLabel("Loading map..."));
    	contentPane.add(loadingPanel, "Center");
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
    
	private void makeRightPanel() {
		// initialize a new JPanel.
		JPanel optionPanel = new JPanel();
		// create a vertical BoxLayout on the optionPanel.
		optionPanel.setLayout(new BoxLayout(optionPanel,BoxLayout.Y_AXIS));

		// add the checkbox, and the other GUI to the right panel.
		optionPanel.add(createRouteplanningBox());
		optionPanel.add(createCheckbox());
		optionPanel.add(createZoomOutButton());
		// add the optionPanel to the contentPanes borderlayout.
		contentPane.add(optionPanel,"East");
	}
	
	private JPanel createRouteplanningBox() {
		JPanel routePlanning = new JPanel();
		routePlanning.setLayout(new BoxLayout(routePlanning,BoxLayout.Y_AXIS));
		routePlanning.setBorder(new TitledBorder(new EtchedBorder(), "Route planning"));
		
		// from row
		JLabel label = new JLabel("From");
		JTextField text = new JTextField(10);
		text.setBackground(Color.lightGray);
		JPanel fromPanel = new JPanel(new FlowLayout(2));
		fromPanel.add(label);
		fromPanel.add(text);
		
		// to row
		label = new JLabel("To");
		text = new JTextField(10);
		text.setBackground(Color.lightGray);
		JPanel toPanel = new JPanel(new FlowLayout(2));
		toPanel.add(label);
		toPanel.add(text);
		
		// go button
		JButton go = new JButton("Go");
		go.setPreferredSize(new Dimension(60,25));
		go.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// get the selected transportation type and DO SOMETHING
//				getSelectedTransportation()
			}
		} );
		JPanel goPanel = new JPanel(new FlowLayout(1));
		goPanel.add(go);
		
		routePlanning.add(fromPanel);
		routePlanning.add(toPanel);
		routePlanning.add(createTogglePanel());
		routePlanning.add(goPanel);
		
		return routePlanning;		
	}

	// toggleButtons in a ButtonGroup
	private JPanel createTogglePanel() {
		JPanel togglePanel = new JPanel(new FlowLayout(1));
		group = new ButtonGroup();
		ImageIcon icon = getScaledIcon(new ImageIcon("./src/icons/car.png"));
		togglePanel.add(createJToggleButton(icon,true, 0));
		
		icon = getScaledIcon(new ImageIcon("./src/icons/bike.png"));
		togglePanel.add(createJToggleButton(icon,false, 1));
		
		icon = getScaledIcon(new ImageIcon("./src/icons/walk.png"));
		togglePanel.add(createJToggleButton(icon,false, 2));
		return togglePanel;
	}
	
	private ImageIcon getScaledIcon(ImageIcon icon) {
		Image img = icon.getImage();  
		Image newimg = img.getScaledInstance(30, 30,  java.awt.Image.SCALE_SMOOTH);  
		return new ImageIcon(newimg);
	}
	
	private JToggleButton createJToggleButton(ImageIcon ico, boolean selected, int number) {
		JToggleButton button = new JToggleButton();
		final int _number = number;
		if(selected == true) 
			button.setSelected(true);
		button.setIcon(ico);
		button.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED)
					setSelectedTransportation(_number);
			}
		});
		group.add(button);
		return button;
	}
	
	// return 0 if car, 1 if bike, 2 if walk.
	private int getSelectedTransportation() {
		return selectedTransport;
	}
	
	// return 0 if car, 1 if bike, 2 if walk.
		private void setSelectedTransportation(int number) {
			selectedTransport = number;
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
		final String _string = string;
		JPanel fl = new JPanel(new FlowLayout(0));
		JCheckBox box = new JCheckBox(string);
		box.setSelected(selected);
		box.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(_string.equals("Highways")) number = 1;
				if(_string.equals("Expressways")) number = 2;
				if(_string.equals("Primary roads")) number = 3;
				if(_string.equals("Secondary roads")) number = 4;
				if(_string.equals("Normal roads")) number = 5;
				if(_string.equals("Trails & streets")) number = 6;
				if(_string.equals("Trails & streets")) number = 7;
				Controller.updateMap(number, e.getStateChange());
			}
		});
		fl.add(box);
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
