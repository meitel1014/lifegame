package lifegame;

import java.awt.FileDialog;

import javax.swing.JFrame;

public class FileManager {
	private JFrame frame;
	private BoardModel model;

	public FileManager(JFrame frame, BoardModel model) {
		this.frame=frame;
		this.model=model;
	}

	public String save() {
		FileDialog dialog=new FileDialog(frame);
		dialog.setVisible(true);

		return dialog.getFile();
	}

	public boolean[] load(String filename) {

	}
}
