package gui;

import java.awt.BorderLayout;
import java.awt.Color;
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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.KeyStroke;
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
public class GUI {

  // This field contains the current version of the program.
  private static final String VERSION = "Version 1.0";
  // The main frame of our program.
  private JFrame frame;
  private Controller controller;
  private JPanel contentPane, mapPanel, loadingPanel, optionPanel;
  // The map from the controller
  private JComponent map;
  // A ButtonGroup with car, bike, and walk.
  private ButtonGroup group;
  // selected JToggleButton - 0 if car, 1 if bike, 2 if walk.
  private int selectedTransport = 0, number;
  private JLabel statusbar = new JLabel(" ");

  public GUI() {
    makeFrame();
    makeMenuBar();
    makeRightPanel();
    setupFrame();
    controller = Controller.getInstance();
  }

  private void setupFrame() {
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setSize(800, 600);
    // place the frame at the center of the screen and show.
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    frame.setLocation(d.width / 2 - frame.getWidth() / 2,
        d.height / 2 - frame.getHeight() / 2);
    contentPane.setEnabled(false);
    frame.setBackground(Color.darkGray);
    frame.setState(Frame.NORMAL);
    frame.setVisible(true);

  }

  public void setupMap() {
    map = controller.getMap();
    mapPanel = new JPanel(new GridLayout(1, 1));
    mapPanel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,
    		Color.darkGray));
    mapPanel.add(map);
		contentPane.remove(loadingPanel);
    contentPane.add(mapPanel, "Center");
    contentPane.add(statusbar, "South");
    frame.addComponentListener(new ComponentAdapter() {
      public void componentResized(ComponentEvent e) {
        // Controller.scaleMap(mapPanel.getWidth(),mapPanel.getHeight());
        mapPanel.updateUI();
      }
    });
    frame.pack();
    frame.setSize(800, 600);
  }
  
  public void enableFrame() {
	  contentPane.setEnabled(true);
	  frame.setBackground(Color.lightGray);
  }

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
    item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, SHORTCUT_MASK));
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
    item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, SHORTCUT_MASK));
    item.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        showAbout();
      }
    });
    menu.add(item);
  }

  private void makeRightPanel() {
    // initialize a new JPanel.
    optionPanel = new JPanel();
    // create a vertical BoxLayout on the optionPanel.
    optionPanel.setLayout(new BoxLayout(optionPanel, BoxLayout.Y_AXIS));

    // add the checkbox, and the other GUI to the right panel.
    optionPanel.add(createRouteplanningBox());
    optionPanel.add(createCheckbox());
    optionPanel.add(createZoomOutButton());
    // add the optionPanel to the contentPanes borderlayout.
    contentPane.add(optionPanel, "East");
  }

  private JPanel createRouteplanningBox() {
    JPanel routePlanning = new JPanel();
    TitledBorder border = new TitledBorder(new EtchedBorder(), "Route planning");
    border = setHeadlineFont(border);
    routePlanning.setBorder(border);
    routePlanning.setLayout(new BoxLayout(routePlanning, BoxLayout.Y_AXIS));

    // from row
    JLabel label = new JLabel("From");
    label = setLabelFont(label);
    JTextField text = new JTextField(10);
    text = addLiveSearch(text);
    text.setBackground(Color.lightGray);
    JPanel fromPanel = new JPanel(new FlowLayout(2));
    fromPanel.add(label);
    fromPanel.add(text);

    // to row
    label = new JLabel("To");
    label = setLabelFont(label);
    text = new JTextField(10);
    text = addLiveSearch(text);
    text.setBackground(Color.lightGray);
    JPanel toPanel = new JPanel(new FlowLayout(2));
    toPanel.add(label);
    toPanel.add(text);

    // go button
    JButton go = new JButton("Go");
    go = setButtonText(go);
    go.setPreferredSize(new Dimension(55, 33));
    go.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        // get the selected transportation type and DO SOMETHING
        // getSelectedTransportation()
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
  
  private JTextField addLiveSearch(JTextField text) {
	  text.addKeyListener(new KeyAdapter() {
		  public void keyReleased(KeyEvent e) {
			  JTextField textField = (JTextField) e.getSource();
			  String string = textField.getText();
			  setStatus(string);
		  }
	  });
	  return text;
  }

  private <T extends JComponent> T setLabelFont(T label) {
	  label.setFont(new Font("Verdana", Font.PLAIN, 14));
	  return label;
  }
  
  private TitledBorder setHeadlineFont(TitledBorder label) {
	  label.setTitleFont(new Font("Verdana", Font.BOLD, 15));	
	  return label;
  }
  
  private <T extends JComponent> T setButtonText(T label) {
	  label.setFont(new Font("Verdana", Font.PLAIN, 14));	
	  return label;
  }
  
  // toggleButtons in a ButtonGroup
  private JPanel createTogglePanel() {
    JPanel togglePanel = new JPanel(new FlowLayout(1));
    group = new ButtonGroup();
    ImageIcon icon = getScaledIcon(new ImageIcon("./src/icons/car.png"));
    togglePanel.add(createJToggleButton(icon, true, 0));

    icon = getScaledIcon(new ImageIcon("./src/icons/bike.png"));
    togglePanel.add(createJToggleButton(icon, false, 1));

    icon = getScaledIcon(new ImageIcon("./src/icons/walk.png"));
    togglePanel.add(createJToggleButton(icon, false, 2));
    return togglePanel;
  }

  private ImageIcon getScaledIcon(ImageIcon icon) {
    Image img = icon.getImage();
    Image newimg = img.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
    return new ImageIcon(newimg);
  }

  private JToggleButton createJToggleButton(ImageIcon ico, boolean selected,
      int number) {
    JToggleButton button = new JToggleButton();
    final int _number = number;
    if (selected == true)
      button.setSelected(true);
    button.setIcon(ico);
    button.addItemListener(new ItemListener() {
      public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
          setSelectedTransportation(_number);
          System.out.println(getSelectedTransportation());
        }
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
    zoomOut.setPreferredSize(new Dimension(90, 35));
    zoomOut = setButtonText(zoomOut);
    zoomOut.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        controller.showAll();
      }
    });
    zoomPanel.add(zoomOut);
    return zoomPanel;
  }

  private JPanel createCheckbox() {
    // initialize checkboxPanel
    JPanel checkboxPanel = new JPanel(new GridLayout(7, 1));
    TitledBorder border = new TitledBorder(new EtchedBorder(), "Road types");
    border = setHeadlineFont(border);
    checkboxPanel.setBorder(border);
    // fill the checkboxPanel
    checkboxPanel.add(createRoadtypeBox("Highways", true)); // Priority 1 roads
    checkboxPanel.add(createRoadtypeBox("Expressways", true)); // Priority 2
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
    box = setLabelFont(box);
    box.setSelected(selected);
    box.addItemListener(new ItemListener() {
      public void itemStateChanged(ItemEvent e) {
        if (_string.equals("Highways"))
          number = 1;
        if (_string.equals("Expressways"))
          number = 2;
        if (_string.equals("Primary roads"))
          number = 3;
        if (_string.equals("Secondary roads"))
          number = 4;
        if (_string.equals("Normal roads"))
          number = 5;
        if (_string.equals("Trails & streets"))
          number = 6;
        if (_string.equals("Paths"))
          number = 7;
        if (e.getStateChange() == 1)
          controller.updateMap(number, true);
        else
          controller.updateMap(number, false);
      }
    });
    fl.add(box);
    return fl;
  }

  public void setStatus(String text) {
    statusbar.setText(text);
  }

  public void quit() {
    // Exits the application.
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
}