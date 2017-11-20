package lifegame;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

public class BoardView extends JPanel implements BoardListener,MouseListener, MouseMotionListener{
	BoardModel m;

	public BoardView(BoardModel model) {
		this.m=model;
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		//縦線を引く
		for(int x=0;x<=m.getCols();x++) {
			g.drawLine(getXOnWindow(x), dy(), getXOnWindow(x), boxHeight()*m.getRows()+dy());
		}

		//横線を引く
		for(int y=0;y<=m.getRows();y++) {
			g.drawLine(dx(), getYOnWindow(y), boxWidth()*m.getCols()+dx(), getYOnWindow(y));
		}

		//各マスが生きていれば塗る
		for(int i=0;i<m.getRows();i++) {
			for(int j=0;j<m.getCols();j++) {
				if(m.isAlive(j,i)) {
					g.fillRect(getXOnWindow(j),getYOnWindow(i),boxWidth(),boxHeight());
				}
			}
		}
	}

	private int boxWidth() {
		return getWidth()/m.getCols();
	}

	private int boxHeight() {
		return getHeight()/m.getRows();
	}

	//盤面を画面中央に寄せるためx,y座標を端からずらす
	//x座標の左端からのずれ
	private int dx() {
		return (getWidth()%m.getCols())/2;
	}
	//ｙ座標の上端からのずれ
	private int dy() {
		return (getHeight()%m.getRows())/2;
	}

	//左からx番目のマスの画面上でのx座標
	private int getXOnWindow(int x) {
		return x*boxWidth()+dx();
	}
	//上からy番目のマスの画面上でのy座標
	private int getYOnWindow(int y) {
		return y*boxHeight()+dy();
	}

	//画面上でのx座標が左から何番目のマスにあるか
	private int getXOnBoard(int x) {
		return (x-dx())/boxWidth();
	}
	//画面上でのy座標が上から何番目のマスにあるか
	private int getYOnBoard(int y) {
		return (y-dy())/boxHeight();
	}

	@Override
	public void updated(BoardModel m) {
		this.repaint();
	}

	int prevX=-1;
	int prevY=-1;
	private enum PrevEvent{PRESS,DRAG,RELEASE}
	PrevEvent prevEvent;
	private void mouseRecord(int x,int y, PrevEvent e) {
		prevX=getXOnBoard(x);
		prevY=getYOnBoard(y);
		prevEvent=e;
	}

	private boolean isOutOfBounds(int x,int y) {
		return x<dx()||y<dy()||x>=getWidth()-dx()||y>=getHeight()-dy();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(isOutOfBounds(e.getX(),e.getY())){
			return;
		}
		m.changeCellState(getXOnBoard(e.getX()), getYOnBoard(e.getY()));
		mouseRecord(e.getX(),e.getY(),PrevEvent.PRESS);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if(isOutOfBounds(e.getX(),e.getY())){
			return;
		}

		if(isPrevCell(e.getX(),e.getY())) {
			m.changeCellStateNoRecord(getXOnBoard(e.getX()), getYOnBoard(e.getY()));
		}
		mouseRecord(e.getX(),e.getY(),PrevEvent.DRAG);
	}

	private boolean isPrevCell(int x,int y) {
		return prevX!=getXOnBoard(x)||prevY!=getYOnBoard(y);
	}

	public void mouseMoved(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
}
