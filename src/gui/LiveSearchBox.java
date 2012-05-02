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

public class LiveSearchBox {

	private JComboBox adress;
	private JTextField component;
	private DocumentListener listener;
	private Document doc;
	private Controller controller;

	public LiveSearchBox() {
		controller = Controller.getInstance();
		adress = new JComboBox();
		Dimension d = adress.getPreferredSize();
		adress.setPreferredSize(new Dimension(120,(int) d.getHeight()));
		adress.setEditable(true);
	  adress.setBackground(Color.lightGray);
		component = (JTextField) adress.getEditor().getEditorComponent();
		component.setSize(50, 10);
		doc = component.getDocument();
		listener = createListener();
		doc.addDocumentListener(listener);
	}
	
	// create a document listener and add to the field listener
	private DocumentListener createListener() {
		return new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent e) {
				update();
			}
	
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
				if(component.getText().length()==0) return;
		      	doc.removeDocumentListener(listener);
		      	String typedRoad = component.getText();
		      	String[] roads = controller.getRoads(typedRoad);
						adress.removeAllItems();
						adress.addItem(typedRoad);
						for (String road : roads) {
							adress.addItem(road);
						}
						adress.showPopup();
						doc.addDocumentListener(listener);
					}
				} );
			}
		}; 
	}
	
	// returns the JComboBox
	public JComboBox getBox() {
		return adress;
	}
	
}
