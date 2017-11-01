package lifegame;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Main implements Runnable {
	public static void main(String[] args){
		SwingUtilities.invokeLater(new Main());
	}

	@Override
	public void run() {
		// BoardModel の作成と changeCellState の呼び出しを行う処理をここで実行．
		// next と undo の呼び出しを取り除き，「グライダー」が設置された状態としておく．
		JFrame frame=new JFrame();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setTitle("Lifegame");
		JPanel base=new JPanel();
		frame.setContentPane(base);
		base.setPreferredSize(new Dimension(400, 300));// 希望サイズの指定
		frame.setMinimumSize(new Dimension(300, 200));// 最小サイズの指定
		base.setLayout(new BorderLayout());
		BoardView view=new BoardView();
		base.add(view, BorderLayout.CENTER);
		frame.pack();// ウィンドウに乗せたものの配置を確定する
		frame.setVisible(true);// ウィンドウを表示する
	}
}
