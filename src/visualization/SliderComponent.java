package visualization;

import java.awt.*;
import java.util.Hashtable;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import controller.Controller;

/**
 * This class creates the JComponent that shows the slider for zooming on the map 
 * @author bjørne
 *
 */

@SuppressWarnings("serial")
public class SliderComponent extends JComponent{
	
	private Controller control = Controller.getInstance();
	private MapComponent map = control.getMap();
	private final int width = 70, height = 200;
	private JSlider slider;
	
	public SliderComponent()
	{
		createSlider();
		createPanel();
	}
	
	/**
	 * for gui to update slider once the zoom is invoked by the mousewheel
	 */
	public void setSlider(int value)
	{
		slider.setValue(value);
	}
	
	/**
	 * this method creates JSlider inside the SliderComponent
	 */
	private void createSlider()
	{
		int maxZoom = 30, minZoom = 0;
		slider = new JSlider(JSlider.VERTICAL, minZoom, maxZoom, minZoom);
		slider.setMajorTickSpacing(5);
		slider.setMinorTickSpacing(1);
		slider.setPaintTicks(true);
		slider.setLabelTable(createLabelTable(minZoom, maxZoom));
		slider.setPaintLabels(true);
		slider.setForeground(Color.darkGray);
		slider.setOpaque(false);
		
		//adds the listener that makes the map zoom once the slider change value
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider) e.getSource();
		        int zoomNiveau = (int) source.getValue();
		        map.zoom(zoomNiveau);
		        repaint();
			}
		});
	}
	
	/**
	 * creates the labels for the slider and draw then next to the min and max value
	 */
	private Hashtable<Integer, JLabel> createLabelTable(int min, int max)
	{
		Hashtable<Integer, JLabel> table = new Hashtable<Integer, JLabel>();
		Icon icon = getScaledIcon(new ImageIcon("./src/icons/+.gif"));
		table.put(new Integer(max), new JLabel(icon));
		icon = getScaledIcon(new ImageIcon("./src/icons/-.gif"));
		table.put(new Integer(min), new JLabel(icon));
		
		return table;
	}
	
	/**
	 * Creates the imageIcon
	 */
	private ImageIcon getScaledIcon(ImageIcon icon) {
		Image img = icon.getImage();
		Image newimg = img.getScaledInstance(30, 30,
				java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(newimg);
	}
	
	/**
	 * creates the panel that contains the JSlider
	 */
	private void createPanel()
	{
		setLayout(new GridLayout(1,1));
		setBorder(new EmptyBorder(5,0,5,0));
		setOpaque(false);
		setPreferredSize(new Dimension(width,height));
		add(slider);
	}
	
	/**
	 * overrides paint to draw transparent background 
	 */
	public void paintComponent(Graphics g)
	{
		//draws the transparent box with rounded edges
		g.setColor(new Color(200,200,200,210));
		g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 20, 20);
		g.setColor(Color.darkGray);
		g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 20, 20);
		super.paintComponents(g);
	}
}
