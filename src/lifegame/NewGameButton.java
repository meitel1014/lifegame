package lifegame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class NewGameButton extends JButton implements ActionListener {
	BoardModel model;

	public NewGameButton(){
		setText("New Game");
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Main.newGame();
	}
}
