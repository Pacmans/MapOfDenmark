package gui;

import java.awt.Color;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

public class LiveSearchBox {

	private JComboBox adress;
	private JTextField component;
	private DocumentListener listener;
	private Document doc;
	
	public LiveSearchBox() {
		adress = new JComboBox();
		adress.setEditable(true);
	  adress.setBackground(Color.lightGray);
		component = (JTextField) adress.getEditor().getEditorComponent();
		component.setColumns(6);
		doc = component.getDocument();
		listener = createListener();
		doc.addDocumentListener(listener);
	}
	
	private DocumentListener createListener() {
		return new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent e) {
			}
	
			@Override
			public void insertUpdate(DocumentEvent e) {
				update();
			}
	
			@Override
			public void removeUpdate(DocumentEvent e) {
				update();
			}
			
			public void update() {
				SwingUtilities.invokeLater(new Runnable() {
		      @Override 
		      public void run() {
		      	doc.removeDocumentListener(listener);
		      	String s = component.getText();
		      	System.out.println(s);
						adress.removeAllItems();
						adress.addItem(s);
						String tmp = s + s;
						adress.addItem(tmp);
						adress.addItem(tmp+tmp);
//						Object[] strings = new String[] { "abc", "bcd", "hakf" };
//						for (Object o : strings)
//							adress.addItem(o);
						adress.showPopup();
						doc.addDocumentListener(listener);
					}
				
//				updateBox();
//				System.out.println("chasdadnge");
//				adress.addItem((Object) new String("hgej"));
//				adress.showPopup();
				
				} );
			}
		}; 
	}
	
	public JComboBox getBox() {
		return adress;
	}
	
	public void updateBox() {
		// get the adresses from the controller.
		Object[] strings = new String[] { "abc", "bcd", "hakf" };
		
		if(adress.getItemCount() == 0) {
			for(int i = 0; i < strings.length; i++) {
				adress.insertItemAt(strings[i].toString(),i);
			}
		} else {
			for(int i = 0; i < strings.length; i++) {
				if(adress.getItemAt(i) != null)
					adress.removeItemAt(i);
				adress.insertItemAt(strings[i],i);
			}
		}
		adress.showPopup();
	}
	
}
