package lifegame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

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
		BoardModel model=new BoardModel(10,10);
		model.addListener(new ModelPrinter());
		model.changeCellState(1, 1);
		model.changeCellState(2, 2);
		model.changeCellState(0, 3);
		model.changeCellState(1, 3);
		model.changeCellState(2, 3);
		model.changeCellState(4, 4);
		model.changeCellState(4, 4);
		// next と undo の呼び出しを取り除き，「グライダー」が設置された状態としておく．
		JFrame frame=new JFrame();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setTitle("Lifegame");
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
		NextButton nextButton=new NextButton(model);
		UndoButton undoButton=new UndoButton(model);
		buttonPanel.add(nextButton);
		buttonPanel.add(undoButton);
		model.addListener(undoButton);


		frame.pack();// ウィンドウに乗せたものの配置を確定する
		frame.setVisible(true);// ウィンドウを表示する
	}
}
