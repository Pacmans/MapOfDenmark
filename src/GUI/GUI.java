package GUI;


import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class GUI {

	// This field contains the current version of the program.
    private static final String VERSION = "Version 1.0";
        // The main frame of our program.
    private JFrame frame;

    public GUI() {
		makeFrame();
		makeMenuBar();
		fillFrame();
		setupFrame();
    }
    
    
	private void setupFrame() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
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
				      "\nMade by Claus, Bj¿rn, Phillip, Morten & Anders.",
				      "About Map Of Denmark", 
				      JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void makeFrame() {
    	// create the frame set the layout and border.
    	frame = new JFrame("Car Rental");
    	JPanel contentPane = (JPanel) frame.getContentPane();
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
    
    private void fillFrame() {
    	makeMap();
    	makeRightPanel();
    	
    }

	private void makeMap() {
		
	}

	private void makeRightPanel() {
		
	}



}
