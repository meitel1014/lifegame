package lifegame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class UndoButton extends JButton implements ActionListener,BoardListener{
	BoardModel model;

	public UndoButton(BoardModel model){
		setText("Undo");
		this.model=model;
		this.addActionListener(this);
		setEnabled(model.isUndoable());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		model.undo();
	}

	@Override
	public void updated(BoardModel m) {
		setEnabled(m.isUndoable());
	}
}
