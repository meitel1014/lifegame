package lifegame;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class AutoRunner extends Thread implements ChangeListener{
	private BoardModel model;
	private JSlider slider;
	private int period;

	public AutoRunner(BoardModel model, JSlider slider){
		this.model = model;
		this.slider = slider;
		period = 1000 - slider.getValue();
		slider.addChangeListener(this);
	}

	@Override
	public void run(){
		while(true){
			try{
				Thread.sleep(period);
				model.next();
			}catch(InterruptedException e){
				break;
			}
		}
	}

	@Override
	public synchronized void stateChanged(ChangeEvent e){
		period = 1000 - slider.getValue();
	}

}
