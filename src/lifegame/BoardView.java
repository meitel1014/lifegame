package lifegame;

import java.awt.Graphics;

import javax.swing.JPanel;

public class BoardView extends JPanel implements BoardListener{
	BoardModel model;

	public BoardView(BoardModel model) {
		this.model=model;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		//縦線を引く
		for(int x=0;x<=model.getCols();x++) {
			g.drawLine(x*boxWidth(), 0, x*boxWidth(), boxHeight()*model.getRows());
		}

		//横線を引く
		for(int y=0;y<=model.getRows();y++) {
			g.drawLine(0, y*boxHeight(), boxWidth()*model.getCols(), y*boxHeight());
		}

		//各マスが生きていれば塗る
		for(int i=0;i<model.getRows();i++) {
			for(int j=0;j<model.getCols();j++) {
				if(model.isAlive(i,j)) {
					g.fillRect(getX(j),getY(i),boxWidth(),boxHeight());
				}
			}
		}
	}

	private int boxWidth() {
		return getWidth()/model.getCols();
	}

	private int boxHeight() {
		return getHeight()/model.getRows();
	}

	private int getX(int x) {
		return x*boxWidth();
	}

	private int getY(int y) {
		return y*boxHeight();
	}

	@Override
	public void updated(BoardModel m) {
		this.repaint();
	}
}
