package mypackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Timer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.util.TimerTask;

public class MainFrame {

	public static void main(String[] args) throws IOException {
		new Frame();
	}

}

@SuppressWarnings("serial")
class Frame extends JFrame {

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
//	private JLabel l4;
//	private JLabel l5;

	private JLabel infolabel;

	private JMenuBar mb = new JMenuBar();
	private JMenu modifica = new JMenu("Modifica");
	private JMenuItem vel = new JMenuItem("Velocità");
	private JMenuItem str_jmenu = new JMenuItem("Stringa");

	public Frame() throws IOException {

		timer = new Timer();

		addString();
		String sec = JOptionPane.showInputDialog("Inserisci i secondi");

		addSeconds(sec);

		setJFrameOnScreen();

		setLayout(new BorderLayout());

		setTitle("Campanari v1.0");

		JPanel panel_buttons = new JPanel();
		panel_buttons.setLayout(new FlowLayout());

		JPanel panel_central = new JPanel();
		panel_central.setLayout(new BorderLayout());

		JPanel panel_fields = new JPanel();
		panel_fields.setLayout(new GridLayout());

		l1 = new JLabel();
		l1.setFont(new Font("Helvetica", Font.ITALIC | Font.BOLD, 200));
		l1.setHorizontalAlignment(JLabel.CENTER);
		l1.setForeground(Color.RED);
		l2 = new JLabel();
		l2.setFont(new Font("Helvetica", Font.ITALIC, 100));
		l2.setHorizontalAlignment(JLabel.CENTER);
		l3 = new JLabel();
		l3.setFont(new Font("Helvetica", Font.ITALIC, 100));
		l3.setHorizontalAlignment(JLabel.CENTER);

		newPainting();

		pauseb.setEnabled(false);
		stopb.setEnabled(false);

		vel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String sec = JOptionPane.showInputDialog("Inserisci i secondi");
				addSeconds(sec);
				infolabel.setText("Secondi: " + SEC / 1000 + "   |   Stringa: "
						+ old_str);
			}
		});

		str_jmenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				addString();
				infolabel.setText("Secondi: " + SEC / 1000 + "   |   Stringa: "
						+ old_str);
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

		infolabel = new JLabel("Secondi: " + SEC / 1000 + "   |   Stringa: "
				+ old_str);
		infolabel.setHorizontalAlignment(JLabel.CENTER);

		JLabel titlelabel = new JLabel("KARAOKE CAMPANARI");
		titlelabel.setFont(new Font("Helvetica", Font.ITALIC, 20));
		titlelabel.setHorizontalAlignment(JLabel.CENTER);
		titlelabel.setForeground(Color.RED);

		panel_buttons.add(startb);
		panel_buttons.add(pauseb);
		panel_buttons.add(stopb);

		panel_fields.add(l1);
		panel_fields.add(l2);
		panel_fields.add(l3);

		panel_central.add(panel_fields, BorderLayout.CENTER);
		panel_central.add(infolabel, BorderLayout.SOUTH);
		panel_central.add(titlelabel, BorderLayout.NORTH);

		add(panel_buttons, BorderLayout.SOUTH);
		add(panel_central, BorderLayout.CENTER);
		add(MyMenuBar(), BorderLayout.NORTH);
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
		l3.setText("");
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
		if (i < 2 || i >= str.length + 2) {
			l1.setText("");
		} else {
			l1.setText(str[i - 2]);
		}
		if (i == 0 || i >= str.length + 1) {
			l2.setText("");
		} else {
			l2.setText(str[i - 1]);
		}
		if (i >= str.length) {
			l3.setText("");
		} else {
			l3.setText(str[i]);
		}

		if (i == str.length + 2) {
			JOptionPane.showMessageDialog(this,
					"La stringa è stata completata correttamente!", "Fine",
					JOptionPane.WARNING_MESSAGE);
			pause();
			i = -1;
			newPainting();

			modifica.setEnabled(true);

			pauseb.setEnabled(false);
			stopb.setEnabled(false);
			startb.setEnabled(true);
		}

		i++;

	}

	private void addString() {
		old_str = JOptionPane.showInputDialog("Inserisci la stringa");
		while (old_str.equals("")) {
			JOptionPane.showMessageDialog(this,
					"Devi inserire almeno un carattere",
					"Errore: stringa non trovata", JOptionPane.ERROR_MESSAGE);
			old_str = JOptionPane.showInputDialog("Inserisci la stringa");
		}
		str = old_str.split(",");
	}

	private void addSeconds(String sec) {
		if (sec.equals("") || sec.equals("0")) {
			JOptionPane.showMessageDialog(this,
					"Verrà settato di default un delay di 1 sec", "Attenzione",
					JOptionPane.WARNING_MESSAGE);
			SEC = 1000;
		} else
			SEC = Integer.valueOf(sec) * 1000;
	}

}
