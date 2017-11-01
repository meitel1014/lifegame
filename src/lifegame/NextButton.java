package lifegame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class NextButton extends JButton implements ActionListener {
	BoardModel model;

	public NextButton(BoardModel model){
		setText("Next");
		this.model=model;
		this.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		model.next();
	}
}
