package gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

import controller.Controller;

/**
 * @(#)LiveSearchBox.java
 * 
 * This class represents a live search box where the user can
 * type some text and it will automatically show a popup window
 * below with suggestions.
 *
 * @author Pacmans
 * @version 21. May 2012
 */
public class LiveSearchBox {

	private JComboBox adress;
	private JTextField component;
	private DocumentListener listener;
	private Document doc;
	private Controller controller;
	
	/**
	 * Constructor for the LiveSearchBox.
	 * First create a pointer to the controller.
	 * Then create a JComboBox, set the preferred size, set
	 * the setEditable to true, and set the background.
	 * The component is retrieved from adress and sets a size.
	 * Finally a listener is added to component.
	 */
	public LiveSearchBox() {
		controller = Controller.getInstance();
		adress = new JComboBox();
		Dimension d = adress.getPreferredSize();
		adress.setPreferredSize(new Dimension(170,(int) d.getHeight()));
		adress.setEditable(true);
	  adress.setBackground(Color.lightGray);
		component = (JTextField) adress.getEditor().getEditorComponent();
		component.setSize(50, 10);
		doc = component.getDocument();
		listener = createListener();
		doc.addDocumentListener(listener);
	}
	
	/**
	 * Create a document listener and add to the field listener
	 * When the listener gets activated it temporarily deactivates
	 * and when finished it is then activated again.
	 * @return DocumentListener
	 */
	private DocumentListener createListener() {
		return new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent e) { }
	
			@Override
			public void insertUpdate(DocumentEvent e) {
				update();
			}
	
			@Override
			public void removeUpdate(DocumentEvent e) {
				update();
			}
			
			// when the user adds or deletes a character this
			// will be called.
			public void update() {
				SwingUtilities.invokeLater(new Runnable() {
		      @Override 
		      public void run() {
		      	doc.removeDocumentListener(listener);
		      	String typedRoad = component.getText();
		      	String[] roads = controller.getRoads(typedRoad);
						adress.removeAllItems();
						if(typedRoad != "") { 
							// if the users typed road is not empty
							adress.addItem(typedRoad); // then add the typed road.
							for (String road : roads) {
								if(road != null) // if the road string is not null then add
									adress.addItem(road.trim());
							}
							adress.showPopup();
						}
						doc.addDocumentListener(listener);
					}
				} );
			}
		}; 
	}
	
	/**
	 *	Get and return the JComboBox
	 *	@return adress
	 */
	public JComboBox getBox() {
		return adress;
	}
	
	/**
	 * Get and return the text of the component
	 * @return component
	 */
	public String getText() {
		return component.getText();
	}
}
