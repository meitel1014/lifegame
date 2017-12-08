package lifegame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;

public class Main implements Runnable{
	private static int games = 0;
	public String filename=null;

	public static void main(String[] args){
		SwingUtilities.invokeLater(new Main());
	}

	public void run(){
		Initializer init = new Initializer();
		if(!init.initialize(filename)){
			return;
		}

		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		games++;
		frame.setTitle("Lifegame" + games);

		BoardModel model = new BoardModel(init.getRows(), init.getCols());
		JPanel base = new JPanel();
		frame.setContentPane(base);
		base.setPreferredSize(new Dimension(550, 400));// 希望サイズの指定
		frame.setMinimumSize(new Dimension(550, 200));// 最小サイズの指定
		base.setLayout(new BorderLayout());

		BoardView view = new BoardView(model);
		base.add(view, BorderLayout.CENTER);
		model.addListener(view);

		MenuManager menu=new MenuManager(frame,model);

		JPanel buttonPanel = new JPanel(); // ボタン用パネルを作成し
		base.add(buttonPanel, BorderLayout.SOUTH); // base の下端に配置する
		buttonPanel.setLayout(new FlowLayout()); // java.awt.FlowLayout を設定

		JSlider slider = new JSlider(0, 1000, 500);
		ButtonManager buttons = new ButtonManager(model, buttonPanel, slider);
		model.addListener(buttons);
		String[] buttonNames = { "New Game", "Next", "Undo", "Auto" };
		for(String name: buttonNames){
			buttons.add(name);
		}

		buttonPanel.add(slider);
		frame.addWindowListener(buttons);

		frame.pack();// ウィンドウに乗せたものの配置を確定する
		frame.setVisible(true);// ウィンドウを表示する
	}
}
