package newpackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Timer;

import javafx.scene.control.TextArea;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.util.TimerTask;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	private final Dimension screenSize = Toolkit.getDefaultToolkit()
			.getScreenSize();

	private final int SCREEN_WIDTH = 1200;
	private final int SCREEN_HEIGHT = 300;

	private Timer timer;

	private int SEC = 0;

	private String[] str;

	private String old_str;
	
	private JButton startb = new JButton("START");
	private JButton pauseb = new JButton("PAUSE");
	private JButton stopb = new JButton("STOP");

	private JLabel l1;
	private JLabel l2;
	private JLabel l3;
	private JLabel l4;
	private JLabel l5;
	
	private JMenuBar mb = new JMenuBar();
	private JMenu modifica = new JMenu("Modifica");
	private JMenuItem vel = new JMenuItem("Velocità");
	private JMenuItem str_jmenu = new JMenuItem("Stringa");
	
	private JLabel label_info;

	public MainFrame() {

		timer = new Timer();

		old_str = JOptionPane.showInputDialog("Inserisci la stringa");
		String sec = JOptionPane.showInputDialog("Inserisci i secondi");

		SEC = Integer.valueOf(sec) * 1000;

		setJFrameOnScreen();

		str = old_str.split(",");

		setLayout(new BorderLayout());

		setTitle("Campanari v1.0");

		JPanel panel_buttons = new JPanel();
		panel_buttons.setLayout(new FlowLayout());

		JPanel panel_central = new JPanel();
		panel_central.setLayout(new GridLayout());

		l1 = new JLabel();
		l1.setFont(new Font("Helvetica", Font.ITALIC | Font.BOLD, 150));
		l1.setHorizontalAlignment(JLabel.CENTER);
		l1.setForeground(Color.RED);
		l2 = new JLabel();
		l2.setFont(new Font("Helvetica", Font.ITALIC, 150));
		l2.setHorizontalAlignment(JLabel.CENTER);
		l3 = new JLabel();
		l3.setFont(new Font("Helvetica", Font.ITALIC, 150));
		l3.setHorizontalAlignment(JLabel.CENTER);
		l4 = new JLabel();
		l4.setFont(new Font("Helvetica", Font.ITALIC, 150));
		l4.setHorizontalAlignment(JLabel.CENTER);
		l5 = new JLabel();
		l5.setFont(new Font("Helvetica", Font.ITALIC, 150));
		l5.setHorizontalAlignment(JLabel.CENTER);

		newPainting();

		pauseb.setEnabled(false);
		stopb.setEnabled(false);
		
		vel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SEC = Integer.valueOf(JOptionPane.showInputDialog("Inserisci i secondi")) * 1000;
				label_info = new JLabel("Secondi: " + SEC/1000 + "  |  Stringa: " + old_str);
			}
		});
		
		str_jmenu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				old_str = JOptionPane.showInputDialog("Inserisci la stringa");
			}
		});

		startb.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				startb.setEnabled(false);
				stopb.setEnabled(true);
				pauseb.setEnabled(true);
				startPaiting();
				modifica.setEnabled(false);
			}
		});

		pauseb.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				startb.setEnabled(true);
				stopb.setEnabled(true);
				pauseb.setEnabled(false);
				pause();
			}
		});

		stopb.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				startb.setEnabled(true);
				stopb.setEnabled(false);
				pauseb.setEnabled(false);
				pause();
				newPainting();
				i = 0;
				modifica.setEnabled(true);
			}
		});
		
		panel_buttons.add(startb);
		panel_buttons.add(pauseb);
		panel_buttons.add(stopb);
		
		panel_central.add(l1);
		panel_central.add(l2);
		panel_central.add(l3);
		panel_central.add(l4);
		panel_central.add(l5);

		add(panel_buttons, BorderLayout.SOUTH);
		add(panel_central, BorderLayout.CENTER);
		add(MyMenuBar(), BorderLayout.NORTH);
		mb.add(Box.createGlue());
		label_info = new JLabel("Secondi: " + SEC/1000 + "  |  Stringa: " + old_str);
		mb.add(label_info);
		setVisible(true);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public JMenuBar MyMenuBar() {
		modifica.add(vel);
		modifica.add(str_jmenu);
		mb.add(modifica);
		return mb;
	}

	private void setJFrameOnScreen() {
		int x = (screenSize.width - SCREEN_WIDTH) / 2;
		int y = (screenSize.height - SCREEN_HEIGHT) / 2;
		setBounds(x, y, SCREEN_WIDTH, SCREEN_HEIGHT);
	}

	public void newPainting() {
		l1.setText("");
		l2.setText("");
		// l3.setText(str[0]);
		// l4.setText(str[1]);
		// l5.setText(str[2]);
		l3.setText("");
		l4.setText("");
		l5.setText("");
	}

	public void startPaiting() {
		timer = new Timer();
		timer.schedule(new TimerTask() {
			public void run() {
				changeState();
				repaint();
			}
		}, 0, SEC); // or 10000 which is 10 s.
	}

	public void pause() {
		timer.cancel();
	}

	int i = 0;

	private void changeState() {
		if (i < 4 || i >= str.length + 4) {
			l1.setText("W");
			System.out.println("Setta l1 a W");
		} else {
			l1.setText(str[i - 4]);
			System.out.println("Setta l1 a " + str[i - 4] + "posizione: "
					+ (i - 4));
		}
		if (i < 3 || i >= str.length + 3) {
			l2.setText("W");
			System.out.println("Setta l2 a W");
		} else {
			l2.setText(str[i - 3]);
			System.out.println("Setta l1 a " + str[i - 3] + "posizione: "
					+ (i - 3));
		}
		if (i < 2 || i >= str.length + 2) {
			l3.setText("W");
			System.out.println("Setta l3 a W");
		} else {
			l3.setText(str[i - 2]);
			System.out.println("Setta l1 a " + str[i - 2] + "posizione: "
					+ (i - 2));
		}
		if (i == 0 || i >= str.length + 1) {
			l4.setText("W");
			System.out.println("Setta l4 a W");
		} else {
			l4.setText(str[i - 1]);
			System.out.println("Setta l1 a " + str[i - 1] + "posizione: "
					+ (i - 1));
		}
		if (i >= str.length) {
			l5.setText("W");
			System.out.println("Setta l5 a W");
		} else {
			l5.setText(str[i]);
			System.out.println("Setta l1 a " + str[i] + "posizione: " + i);
		}

		if (i == str.length + 4) {
			JOptionPane.showMessageDialog(this,
				    "La stringa è stata completata correttamente!",
				    "Inane warning",
				    JOptionPane.WARNING_MESSAGE);
			pause();
			newPainting();
			
			pauseb.setEnabled(false);
			stopb.setEnabled(false);
			startb.setEnabled(true);
		}

		i++;

	}

}
