package lifegame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ButtonManager implements ActionListener, BoardListener {
	BoardModel model;
	JPanel buttonPanel;
	private HashMap<String, JButton> buttons;

	public ButtonManager(BoardModel model, JPanel panel) {
		this.model = model;
		this.buttonPanel = panel;
		buttons = new HashMap<String, JButton>();
	}

	public void add(String label) {
		JButton button = new JButton(label);
		button.setActionCommand(label);
		button.addActionListener(this);
		buttons.put(label, button);
		buttonPanel.add(button);
		if (label.equals("Undo")) {
			button.setEnabled(model.isUndoable());
		}
	}

	@Override
	public void updated(BoardModel m) {
		buttons.get("Undo").setEnabled(model.isUndoable());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "New Game":
			Main.main(null);
			break;
		case "Next":
			model.next();
			break;
		case "Undo":
			model.undo();
			break;
		default:
			System.out.println("Button error");
		}
	}
}
