package lifegame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Main {
	private static int games=0;

	public static void main(String[] args){
		newGame();
	}

	public static void newGame() {
		
		// BoardModel の作成
		JFrame frame=new JFrame();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	
		
		if(!Init(frame)) {
			return;
		}
		games++;
		frame.setTitle("Lifegame"+games);
		
		BoardModel model=new BoardModel(15,10);
		JPanel base=new JPanel();
		frame.setContentPane(base);
		base.setPreferredSize(new Dimension(400, 300));// 希望サイズの指定
		frame.setMinimumSize(new Dimension(300, 200));// 最小サイズの指定
		base.setLayout(new BorderLayout());

		BoardView view=new BoardView(model);
		base.add(view, BorderLayout.CENTER);
		model.addListener(view);


		JPanel buttonPanel = new JPanel(); // ボタン用パネルを作成し
		base.add(buttonPanel, BorderLayout.SOUTH); // base の下端に配置する
		buttonPanel.setLayout(new FlowLayout()); // java.awt.FlowLayout を設定
		ButtonManager buttons=new ButtonManager(model,buttonPanel);	
		model.addListener(buttons);
		String[] buttonNames= {"New Game","Next","Undo"};
		for(String name:buttonNames) {
			buttons.add(name);
		}
		

		frame.pack();// ウィンドウに乗せたものの配置を確定する
		frame.setVisible(true);// ウィンドウを表示する
	}
	
	static private boolean Init(JFrame frame) {
		JDialog dialog=new JDialog(frame,"New Game");
		JPanel panel = new JPanel();
		dialog.setContentPane(panel);
		JTextField rowst=new JTextField(10);
		JTextField colst=new JTextField(10);
		panel.add(rowst);
		panel.add(colst);
		
		JPanel buttonPanel = new JPanel(); // ボタン用パネルを作成し
		panel.add(buttonPanel, BorderLayout.SOUTH); // base の下端に配置する
		buttonPanel.setLayout(new FlowLayout()); // java.awt.FlowLayout を設定
		
		
		dialog.pack();
		dialog.setVisible(true);
		
		return true;
	}
}
