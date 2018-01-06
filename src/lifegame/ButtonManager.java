package lifegame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class ButtonManager implements ActionListener, BoardListener, WindowListener{
	private BoardModel model;
	private int autonum=0;
	private int stopnum=0;
	private JPanel buttonPanel;
	private JSlider slider;
	private HashMap<String, JButton> buttons;
	private AutoRunner auto;
	private String[] buttonNames = { "New Game", "Next", "Undo", "Auto" };

	public ButtonManager(BoardModel model, JPanel panel, JSlider slider){
		this.model = model;
		this.buttonPanel = panel;
		buttons = new HashMap<String, JButton>();
		this.slider = slider;

		for(String name: buttonNames){
			this.add(name);
		}
	}

	public void add(String label){
		JButton button = new JButton(label);
		button.setActionCommand(label);
		button.addActionListener(this);
		buttons.put(label, button);
		buttonPanel.add(button);
		if(label.equals("Undo")){
			button.setEnabled(model.isUndoable());
		}
	}

	@Override
	public void updated(BoardModel m){
		JButton undoButton=buttons.get("Undo");
		undoButton.setEnabled(model.isUndoable());
	}

	@Override
	public void actionPerformed(ActionEvent e){
		switch(e.getActionCommand()){
		case "New Game":
			Main.main(null);
			break;
		case "Next":
			model.next();
			break;
		case "Undo":
			model.undo();
			break;
		case "Auto":
			stopnum++;
			JButton autoButton = buttons.get("Auto");
			autoButton.setText("Stop"+stopnum);
			autoButton.setActionCommand("Stop");
			autoButton.repaint();

			auto = new AutoRunner(model, slider);
			auto.start();

			break;
		case "Stop":
			autonum++;
			JButton stopButton = buttons.get("Auto");
			stopButton.setText("Auto"+autonum);
			stopButton.setActionCommand("Auto");
			stopButton.repaint();

			auto.interrupt();

			break;
		default:
			System.out.println("Button error");
		}
	}

	@Override
	public void windowClosing(WindowEvent e){
		if(auto != null){
			auto.interrupt();
		}
	}

	public void windowOpened(WindowEvent e){}
	public void windowClosed(WindowEvent e){}
	public void windowIconified(WindowEvent e){}
	public void windowDeiconified(WindowEvent e){}
	public void windowActivated(WindowEvent e){}
	public void windowDeactivated(WindowEvent e){}
}
