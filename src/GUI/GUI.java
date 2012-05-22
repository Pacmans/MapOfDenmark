package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
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
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.HashMap;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import visualization.MapComponent;
import visualization.SliderComponent;
import controller.Controller;

/**
 * The main class of the graphical user interface
 * @author Pacmans
 * @version 14. May 2012
 */
public class GUI {
	// This field contains the current version of the application.
	private static final String VERSION = "Version 1.0";
	// The main frame of our application.
	private JFrame frame;
	private Controller controller;
	private JPanel contentPane, loadingPanel, optionPanel, roadtypeBoxes, checkboxPanel;
	// The map from the controller
	private MapComponent map;
	// A layer which is in top of the map
	private JLayeredPane layer;
	// An empty area in the right option panel
	private Component area = Box.createRigidArea(new Dimension(200,253));
	// The map slider
	private SliderComponent slider;
	// A ButtonGroup with car, bike, and walk.
	private ButtonGroup group;
	// A HashMap with a roadtype string and a checkbox attached to it.
	private HashMap<String, JCheckBox> boxes = new HashMap<String, JCheckBox>();
	// An enum which is the selected transport.
	private TransportationType selectedTransport;
	// The roadtype
	private int number;
	// A field for the statusbar in the bottom of the frame
	private JLabel statusbar = new JLabel(" ");
	// The checkbox for switching manual control
	private JCheckBox manualControlBox;
	// The address fields from and to
	private LiveSearchBox fromBox, toBox;
	// The minimum size of the frame.
	private Dimension windowSize = new Dimension(880, 655);

	/**
	 * Constructor for the GUI class.
	 * First it makes the frame and then the menu bar.
	 * Then the right panel is made and inserted into the frame.
	 * The frame is then set up and a pointer to the controller is created. 
	 */
	public GUI() {
		makeFrame();
		makeMenuBar();
		makeRightPanel();
		setupFrame();
		controller = Controller.getInstance();
	}
	
	/**
	 * Creates a new JFrame and set's the layout to a BorderLayout.
	 * Then a loading panel is added to the JFrame's contentPane.
	 */
	private void makeFrame() {
		// create the frame set the layout and border.
		frame = new JFrame("Map Of Denmark");
		contentPane = (JPanel) frame.getContentPane();
		contentPane.setBorder(new EmptyBorder(4, 4, 4, 4));
		contentPane.setLayout(new BorderLayout(5, 5));
		loadingPanel = new JPanel(new FlowLayout(1));
		loadingPanel.setBorder(new EmptyBorder(150, 6, 6, 6));
		JLabel loadingLabel = new JLabel("Loading map...");
		loadingLabel.setForeground(Color.white);
		loadingLabel.setFont(new Font("Verdana", Font.BOLD, 40));
		loadingPanel.add(loadingLabel);
		contentPane.add(loadingPanel, "Center");
	}

	/**
	 * First make sure that the application exit's when the user closes the frame.
	 * Setup the size and make sure it doesn't get smaller than the minimum size.
	 *  
	 */
	private void setupFrame() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setMinimumSize(windowSize);
		frame.setSize(windowSize);
		frame.pack();
		frame.setState(Frame.NORMAL);
		// place the frame at the center of the screen and show.
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(d.width / 2 - frame.getWidth() / 2, d.height / 2
				- frame.getHeight() / 2);
		contentPane.setEnabled(false);
		frame.setBackground(Color.darkGray);
		updateGUI();
	}

	/**
	 * Updates the GUI by showing it and shows/hides the road type boxes,
	 * depending on the manual control box's state.
	 */
	private void updateGUI() {
		frame.setVisible(true);
		if (manualControlBox.isSelected()) {
			area.setVisible(false);
			roadtypeBoxes.setVisible(true);
		} else {
			roadtypeBoxes.setVisible(false);
			area.setVisible(true);
		}
	}

	/**
	 * Calls the controller to retrieve the map and the slider.
	 * Add a mouse wheel listener to the map and 
	 * get the zoom level to the slider. 
	 * Then remove the loadingPanel, setup the slider and map,
	 * and then enable the contentPane at last. 
	 */
	public void setupMap() {
    map = controller.getMap();
    slider = controller.getSlider();

    map.addMouseWheelListener(new MouseWheelListener() {
      public void mouseWheelMoved(MouseWheelEvent e) {
        int zoom = map.getZoomLevel();
        slider.setSlider(zoom);
      }
    });
    frame.setVisible(false);
    contentPane.remove(loadingPanel);

    slider.setBounds(15,10,70,200);

    layer = new JLayeredPane();
    layer.add(map, new Integer(1));
    layer.add(slider, new Integer(2));
    layer.addComponentListener(new ComponentAdapter(){

      @Override
      public void componentResized(ComponentEvent e) {
        frame.repaint();
        map.setBounds(0,0,layer.getWidth(),layer.getHeight());
      }
    });
    contentPane.add(layer, "Center");
    contentPane.add(statusbar, "South");

    contentPane.setEnabled(true);
    frame.setBackground(Color.lightGray);
    updateGUI();
    map.setBounds(0,0,layer.getWidth(),layer.getHeight());
  }

	/**
	 * Create a menu bar with key stroke shortcut attached.
	 */
	private void makeMenuBar() {
		// Create key stroke shortcuts for the menu.
		final int SHORTCUT_MASK = Toolkit.getDefaultToolkit()
				.getMenuShortcutKeyMask();

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
		item.setAccelerator(KeyStroke
				.getKeyStroke(KeyEvent.VK_Q, SHORTCUT_MASK));
		// create an actionlistener and call the method quit() when chosen.
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quit();
			}
		});
		menu.add(item);

		// create Help menu
		menu = new JMenu("Help");
		menubar.add(menu);

		item = new JMenuItem("About Map Of Denmark");
		item.setAccelerator(KeyStroke
				.getKeyStroke(KeyEvent.VK_A, SHORTCUT_MASK));
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showAbout();
			}
		});
		menu.add(item);
	}

	/**
	 * Creates a JPanel with a BoxLayout for the right side of the JFrame.
	 * First add the route planning box and the check box.
	 * Then add an empty area to make it look nice. At last create the zoom out
	 * button and add to the JPanel and then to the contentPane. 
	 */
	private void makeRightPanel() {
		// initialize a new JPanel.
		optionPanel = new JPanel();
		// create a vertical BoxLayout on the optionPanel.
		optionPanel.setLayout(new BoxLayout(optionPanel, BoxLayout.Y_AXIS));

		// add the check box, and the other GUI to the right panel.
		optionPanel.add(createRouteplanningBox());
		optionPanel.add(createCheckbox());
		optionPanel.add(area);
		area.setVisible(false);
		optionPanel.add(createZoomOutButton());
		// add the optionPanel to the contentPanes BorderLayout.
		contentPane.add(optionPanel, "East");
	}

	/**
	 * Create a JPanel routePlanning with route planning elements
	 * First create the border for the JPanel with a headline font 
	 * then set the layout. Then it creates two labels and LiveSearchBoxes
	 * and adds to the JPanel. The LiveSearchBoxes are then disabled so 
	 * the user can't search until everything is loaded.
	 * The go button is created and a listener is attached and after that
	 * everything is added to routePlanning.
	 * 
	 * @return routePlanning
	 */
	private JPanel createRouteplanningBox() {
		JPanel routePlanning = new JPanel();
		TitledBorder border = new TitledBorder(
				new EtchedBorder(), "Route planning");
		border = setHeadlineFont(border);
		routePlanning.setBorder(border);
		routePlanning.setLayout(new BoxLayout(routePlanning, BoxLayout.Y_AXIS));

		// from row
    JLabel label = new JLabel("From");
    label = setLabelFont(label);
    
    fromBox = new LiveSearchBox();

    JPanel fromPanel = new JPanel(new FlowLayout(2));
    fromPanel.add(label);
    fromPanel.add(fromBox.getBox());

    // to row
    label = new JLabel("To");
    label = setLabelFont(label);
    
    toBox = new LiveSearchBox();
    JPanel toPanel = new JPanel(new FlowLayout(2));
    toPanel.add(label);
    toPanel.add(toBox.getBox());
    
    enableSearch(false);

		// go button
		JButton go = new JButton("Go");
		go = setButtonFont(go);
		go.setPreferredSize(new Dimension(55, 33));
		go.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// get the text from the address boxes get a route if not empty.
				if(fromBox.getText().equals("") || toBox.getText().equals(""))
					setStatus("Please fill in both address fields");
				else {
					if(getSelectedTransportation() == TransportationType.BIKE || 
							getSelectedTransportation() == TransportationType.WALK)
						// Bike and walk is not supported so show fail status
						setStatus("Bike and walking route is unfortunately not" +
											" supported yet so please choose car instead");
					else {
						controller.getRoadPlan(fromBox.getText(), toBox.getText());
						setStatus("You route is the blue line");
					}
				}			
			}
		});
		JPanel goPanel = new JPanel(new FlowLayout(1));
		goPanel.add(go);

		routePlanning.add(fromPanel);
		routePlanning.add(toPanel);
		routePlanning.add(createTogglePanel());
		routePlanning.add(goPanel);
		return routePlanning;
	}

	/**
	 * Takes a label and sets the font to Verdana, size 14.
	 * @param label
	 * @return label
	 */
	private <T extends JComponent> T setLabelFont(T label) {
		label.setFont(new Font("Verdana", Font.PLAIN, 14));
		return label;
	}

	/**
	 * Takes a border and sets the font to of the text to Verdana, size 14.
	 * @param TitledBorder
	 * @return TitledBorder
	 */
	private TitledBorder setHeadlineFont(TitledBorder label) {
		label.setTitleFont(new Font("Verdana", Font.BOLD, 15));
		return label;
	}

	/**
	 * Takes a button and sets the font to of the text to Verdana, size 14.
	 * @param Button
	 * @return Button
	 */
	private <T extends JComponent> T setButtonFont(T label) {
		label.setFont(new Font("Verdana", Font.PLAIN, 14));
		return label;
	}

	/**
	 * Creates a JPanel togglePanel. 
	 * Then add the icons to the buttons which are added to the togglePanel.
	 * @return togglePanel
	 */
	// toggleButtons in a ButtonGroup
	private JPanel createTogglePanel() {
		JPanel togglePanel = new JPanel(new FlowLayout(1));
		group = new ButtonGroup();
		ImageIcon icon = getScaledIcon(new ImageIcon("./src/icons/car.png"));
		togglePanel.add(createJToggleButton(icon, true, TransportationType.CAR));

		icon = getScaledIcon(new ImageIcon("./src/icons/bike.png"));
		togglePanel.add(createJToggleButton(icon, false, TransportationType.BIKE));

		icon = getScaledIcon(new ImageIcon("./src/icons/walk.png"));
		togglePanel.add(createJToggleButton(icon, false, TransportationType.WALK));
		return togglePanel;
	}

	/**
	 * Takes an icon and returns a scaled version of it.
	 * @param icon
	 * @return icon
	 */
	private ImageIcon getScaledIcon(ImageIcon icon) {
		Image img = icon.getImage();
		Image newimg = img.getScaledInstance(30, 30,
				java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(newimg);
	}

	/**
	 * Creates a JToggleButton.
	 * Adds an ItemListener to the button 
	 * to set the selected transportation type.
	 * Finally it adds the button to the ButtonGroup
	 * and returns it.
	 * @param ico - The icon of the button
	 * @param selected - The selected state of the button
	 * @param type - The transportation type for the button
	 * @return
	 */
	private JToggleButton createJToggleButton(ImageIcon ico, boolean selected,
			TransportationType type) {
		JToggleButton button = new JToggleButton();
		final TransportationType _type = type;
		if (selected == true)
			button.setSelected(true);
		button.setIcon(ico);
		button.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					setSelectedTransportation(_type);
				}
			}
		});
		group.add(button);
		return button;
	}

	/**
	 * Returns an enum which is the selected transportation type.
	 * @return selectedTransport
	 */
	private TransportationType getSelectedTransportation() {
		return selectedTransport;
	}

	/**
	 * Sets the enum which is the selected transportation type.
	 * @param type
	 */
	private void setSelectedTransportation(TransportationType type) {
		selectedTransport = type;
	}

	/**
	 * Creates the zoom out buttons JPanel and the JButton.
	 * The JButton then has a preferred size and font set.
	 * Then an ActionListener is attached to set the slider 
	 * and then show everything when pushed. The JButton is then
	 * added to JPanel and the JPanel is returned 
	 * @return zoomPanel
	 */
	private JPanel createZoomOutButton() {
    JPanel zoomPanel = new JPanel(new FlowLayout(1));
    JButton zoomOut = new JButton("Zoom out");
    zoomOut.setPreferredSize(new Dimension(110, 40));
    zoomOut = setButtonFont(zoomOut);
    zoomOut.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        slider.setSlider(0);
        controller.showAll();
      }
    });
    zoomPanel.add(zoomOut);
    return zoomPanel;
  }

	/**
	 * Creates a JPanel and sets a BoxLayout.
	 * A border is then added to the JPanel.
	 * Then a new JCheckBox is created for the "Manual Control" box 
	 * and added to the JPanel manualPanel.
	 * It has a ItemListener attached which calls setRoadtypeSelections
	 * and calls the map to set manual control to true.
	 * Then a JPanel for the road type boxes is created with a GridLayout,
	 * and a checkbox for each road type is create and added to the JPanel.
	 * 
	 * @return checkboxPanel 
	 */
	private JPanel createCheckbox() {
		// initialize checkboxPanel
		checkboxPanel = new JPanel();
		checkboxPanel.setLayout(new BoxLayout(checkboxPanel, BoxLayout.Y_AXIS));
		TitledBorder border = new TitledBorder(new EtchedBorder(), "Road types");
		border = setHeadlineFont(border);
		checkboxPanel.setBorder(border);

		// fill the checkboxPanel
		JPanel manualPanel = new JPanel(new FlowLayout(0));
		manualControlBox = new JCheckBox("Manual Control");
		manualControlBox.setFont(new Font("Verdana", Font.CENTER_BASELINE, 13));
		manualControlBox.setSelected(false);
		manualControlBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
			  if (e.getStateChange() == ItemEvent.SELECTED){
			  	setRoadtypeSelections();
			  	map.setManualControl(true);
			  }
        else
          map.setManualControl(false);
        updateGUI();
      }
		});
		manualPanel.add(manualControlBox);

		roadtypeBoxes = new JPanel(new GridLayout(7, 1));
		roadtypeBoxes.add(createRoadtypeBox("Highways", true)); 
																			// Priority 1 roads
		roadtypeBoxes.add(createRoadtypeBox("Expressways", true)); // Priority 2
		roadtypeBoxes.add(createRoadtypeBox("Primary roads", true)); // and so on...
		roadtypeBoxes.add(createRoadtypeBox("Secondary roads", true));
		roadtypeBoxes.add(createRoadtypeBox("Normal roads", false));
		roadtypeBoxes.add(createRoadtypeBox("Trails & streets", false));
		roadtypeBoxes.add(createRoadtypeBox("Paths", false));
		checkboxPanel.add(manualPanel);
		checkboxPanel.add(roadtypeBoxes);
		return checkboxPanel;
	}

	/**
	 * Gets the road types from the map and selects the right check boxes.
	 */
	private void setRoadtypeSelections()
	{
		boolean[] roadtypes = map.getRoadtypes();

		boxes.get("Highways").setSelected(roadtypes[0]);
		boxes.get("Expressways").setSelected(roadtypes[1]);
		boxes.get("Primary roads").setSelected(roadtypes[2]);
		boxes.get("Secondary roads").setSelected(roadtypes[3]);
		boxes.get("Normal roads").setSelected(roadtypes[4]);
		boxes.get("Trails & streets").setSelected(roadtypes[5]);
		boxes.get("Paths").setSelected(roadtypes[6]);
	}

	/**
	 * Creates a JPanel roadTypeBoxPanel for the JCheckBox which is created with 
	 * the given roadtypeString. Then the font is set up and the state is selected.
	 * It has an ItemListener attached which compares the roadtypeString
	 * and sets the variable number. When the user clicks a checkbox,
	 * the listener call updateMap on the map. 
	 * At last the roadtypeString and checkbox is added to the HashMap boxes,
	 * the checkbox is added to the roadTypeBoxPanel which is returned.
	 * 
	 * @param roadtypeString
	 * @param selected
	 * @return roadTypeBoxPanel
	 */
	private JPanel createRoadtypeBox(String roadtypeString, boolean selected) {
		JPanel roadTypeBoxPanel = new JPanel(new FlowLayout(0));
		JCheckBox box = new JCheckBox(roadtypeString);
		box = setLabelFont(box);
		box.setSelected(selected);
		box.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				JCheckBox box = (JCheckBox) e.getSource();
				if (box.getText().equals("Highways"))
					number = 1;
				if (box.getText().equals("Expressways"))
					number = 2;
				if (box.getText().equals("Primary roads"))
					number = 3;
				if (box.getText().equals("Secondary roads"))
					number = 4;
				if (box.getText().equals("Normal roads"))
					number = 5;
				if (box.getText().equals("Trails & streets"))
					number = 6;
				if (box.getText().equals("Paths"))
					number = 7;
				
				if (e.getStateChange() == ItemEvent.SELECTED)
					controller.updateMap(number, true);
				else
					controller.updateMap(number, false);
			}
		});
		boxes.put(roadtypeString, box);
		roadTypeBoxPanel.add(box);
		return roadTypeBoxPanel;
	}

	/**
	 * Set the text of the JLabel statusbar
	 * @param text
	 */
	public void setStatus(String text) {
		statusbar.setText(text);
	}

	/**
	 * Quit the application
	 */
	public void quit() {
		System.exit(0);
	}

	/**
	 * Creates a message dialog which shows the current version of the
	 * application.
	 */
	private void showAbout() {
		JOptionPane.showMessageDialog(frame, "Map Of Denmark - " + VERSION
				+ "\nMade by Claus, Bjørn, Phillip, Morten & Anders.",
				"About Map Of Denmark", JOptionPane.INFORMATION_MESSAGE);
	}
  
	/**
	 * Sets the contentPane to enabled and the background to lightGray.
	 */
  public void enableFrame() {
	  contentPane.setEnabled(true);
	  frame.setBackground(Color.lightGray);
  }
  
  /**
   * Calls the setEnabled method on the address boxes with the given boolean.
   * @param bool
   */
  public void enableSearch(boolean bool){
    toBox.getBox().setEnabled(bool);
    fromBox.getBox().setEnabled(bool);
    toBox.getBox().setEditable(bool);
    fromBox.getBox().setEditable(bool);
  }
}