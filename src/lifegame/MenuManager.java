package lifegame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuManager implements ActionListener {
	BoardModel model;
	FileManager file;

	public MenuManager(JFrame frame, BoardModel model,FileManager file) {
		this.model = model;
		this.file=file;
		JMenuBar menubar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		menubar.add(fileMenu);

		JMenuItem save = new JMenuItem("Save");
		save.setActionCommand("Save");
		save.addActionListener(this);
		JMenuItem load = new JMenuItem("Load");
		load.setActionCommand("Load");
		load.addActionListener(this);
		fileMenu.add(save);
		fileMenu.add(load);

		frame.setJMenuBar(menubar);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "Save":
			file.save();
			break;
		case "Load":
			file.load();
			break;
		default:
			System.out.println("Menu Error");
			break;

		}
	}
}
