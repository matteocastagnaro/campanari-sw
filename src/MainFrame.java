import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import menubar.MyMenuBar;


@SuppressWarnings("serial")
public class MainFrame extends JFrame{
	
	private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();  

	private final int SCREEN_WIDTH = 1200;
	private final int SCREEN_HEIGHT = 300;
	
	public MainFrame(){
		setJFrameOnScreen();
		setTitle("CAMPANARI v1.0");
		setJMenuBar(MyMenuBar());
		setVisible(true);
	}
	
	public JMenuBar MyMenuBar(){
		JMenuBar mb = new JMenuBar();
		JMenu file = new JMenu("Modifica");
		JMenuItem vel = new JMenuItem("Velocità");
		JMenuItem str = new JMenuItem("Stringa");
		file.add(vel);
		file.add(str);
		mb.add(file);
		return mb;
	}

	private void setJFrameOnScreen() {
		int x = (screenSize.width - SCREEN_WIDTH) / 2;  
		int y = (screenSize.height - SCREEN_HEIGHT) / 2;
		setBounds(x,y,SCREEN_WIDTH,SCREEN_HEIGHT);
	}
	
}
