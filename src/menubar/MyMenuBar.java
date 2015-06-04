package menubar;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

@SuppressWarnings("serial")
public class MyMenuBar extends JMenuBar{

	public MyMenuBar(JFrame frame){
		JMenuBar mb = new JMenuBar();
		JMenu file = new JMenu("Modifica");
		JMenuItem vel = new JMenuItem("Velocità");
		JMenuItem str = new JMenuItem("Stringa");
		file.add(vel);
		file.add(str);
		mb.add(file);
	}
	
}
