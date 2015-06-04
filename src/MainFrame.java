import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	private final Dimension screenSize = Toolkit.getDefaultToolkit()
			.getScreenSize();

	private final int SCREEN_WIDTH = 1200;
	private final int SCREEN_HEIGHT = 300;
	
	private int SEC = 0;

	private MarqueePanel mp;
	
	private String str = "";
	
	public MainFrame() {

		String old_str = JOptionPane.showInputDialog("Inserisci la stringa");
		String sec = JOptionPane.showInputDialog("Inserisci i secondi");
		
		SEC = Integer.valueOf(sec);
		
		String[] str_arr = old_str.split(",");
		
		for(int i = 0; i < str_arr.length; i++){
			str = str + "       " + str_arr[i];
		}
		
		mp = new MarqueePanel(str, 40);

		setJFrameOnScreen();
		setTitle("CAMPANARI v1.0");
		setJMenuBar(MyMenuBar());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setLayout(new BorderLayout());

		JButton startb = new JButton("START");
		JButton stopb = new JButton("STOP");
		JButton pauseb = new JButton("PAUSE");

		stopb.setEnabled(false);
		pauseb.setEnabled(false);
		
		JPanel pbuttons = new JPanel();
		pbuttons.add(startb);
		pbuttons.add(pauseb);
		pbuttons.add(stopb);

		add(pbuttons, BorderLayout.SOUTH);

		// String s = "Tomorrow, and tomorrow, and tomorrow, "
		// + "creeps in this petty pace from day to day, "
		// + "to the last syllable of recorded time; ... "
		// + "It is a tale told by an idiot, full of "
		// + "sound and fury signifying nothing.";

		mp.setText("    " + str);
		add(mp, BorderLayout.NORTH);
		pack();
		setLocationRelativeTo(null);

		startb.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mp.start();
				startb.setEnabled(false);
				stopb.setEnabled(true);
				pauseb.setEnabled(true);
			}
		});
		
		pauseb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mp.pause();
				startb.setEnabled(true);
				stopb.setEnabled(true);
				pauseb.setEnabled(false);
			}
		});

		stopb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				startb.setEnabled(true);
				stopb.setEnabled(false);
				pauseb.setEnabled(false);
				mp.pause();
				mp = new MarqueePanel(str, 40);
				mp.setText("    " + str);
				add(mp, BorderLayout.NORTH);
				pack();
				setLocationRelativeTo(null);
			}
		});
		
		setVisible(true);
	}

	public JMenuBar MyMenuBar() {
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
		setBounds(x, y, SCREEN_WIDTH, SCREEN_HEIGHT);
	}

	class MarqueePanel extends JPanel implements ActionListener {

		// private static final int RATE = 12;
		private final Timer timer = new Timer(1000 * SEC, this);
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
			this.s = "   " + s + sb;
			this.n = n;
			label.setFont(new Font("Serif", Font.ITALIC, 72));
			this.add(label);
		}
		
		public void setText(String s){
			label.setText(s);
		}

		public void start() {
			timer.start();
		}

		public void pause() {
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
