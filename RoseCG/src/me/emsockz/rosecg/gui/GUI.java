package me.emsockz.rosecg.gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import me.emsockz.rosecg.Main;
import me.emsockz.rosecg.mechanics.Generate;


public class GUI extends JFrame {

	private static final long serialVersionUID = 5687351929024479739L;

	private JButton start = new JButton("Start");
	private JCheckBox checkAllSkins = new JCheckBox("You love cat?", false);
	
	public GUI() {
		super("Generate compass");
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension dimension = toolkit.getScreenSize();
		this.setBounds(dimension.width/2 - 250, dimension.height/2 - 250, 500, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setIconImage(new ImageIcon(this.getClass().getResource("/img/icon.png")).getImage());
		
		Container container = this.getContentPane();
		container.setLayout(new GridLayout(3, 2, 2, 2));
		container.add(checkAllSkins);
		container.add(start);
		
		start.addActionListener(new StartButtonListener());
		
	}
	
	class StartButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new File(Main.defaultPath+"output"+File.separator).delete();
			new File(Main.defaultPath+"output"+File.separator).mkdirs();
			Generate.generate();
			
			String message = "Successfully!";
			JOptionPane.showMessageDialog(null, message, "Info", JOptionPane.PLAIN_MESSAGE);
		}
	}
}
