import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.Timer;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		String s = "Tomorrow, and tomorrow, and tomorrow, "
		        + "creeps in this petty pace from day to day, "
		        + "to the last syllable of recorded time; ... "
		        + "It is a tale told by an idiot, full of "
		        + "sound and fury signifying nothing.";
		        MarqueePanel mp = new MarqueePanel(s, 32);
		        add(mp);
		        pack();
		        setLocationRelativeTo(null);
		        setVisible(true);
		        mp.start();
		
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
	
	class MarqueePanel extends JPanel implements ActionListener {

	    private static final int RATE = 12;
	    private final Timer timer = new Timer(100 / RATE, this);
	    private final JLabel label = new JLabel();
	    private final String s;
	    private final int n;
	    private int index;

	    public MarqueePanel(String s, int n) {
	        if (s == null || n < 1) {
	            throw new IllegalArgumentException("Null string or n < 1");
	        }
	        StringBuilder sb = new StringBuilder(n);
	        for (int i = 0; i < n; i++) {
	            sb.append(' ');
	        }
	        this.s = sb + s + sb;
	        this.n = n;
	        label.setFont(new Font("Serif", Font.ITALIC, 36));
	        label.setText(sb.toString());
	        this.add(label);
	    }

	    public void start() {
	        timer.start();
	    }

	    public void stop() {
	        timer.stop();
	    }

	    @Override
	    public void actionPerformed(ActionEvent e) {
	        index++;
	        if (index > s.length() - n) {
	            index = 0;
	        }
	        label.setText(s.substring(index, index + n));
	    }

	}
	
}
