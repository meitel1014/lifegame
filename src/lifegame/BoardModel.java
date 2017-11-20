package lifegame;

import java.util.ArrayList;
import java.util.LinkedList;

public class BoardModel{
	private static final int HISTORYSIZE=32;
	private int cols;
	private int rows;
	private boolean[][] cells;
	private ArrayList<BoardListener> listeners;
	private LinkedList<boolean[][]> history;

	public BoardModel(int c,int r){
		cols = c;
		rows = r;
		cells = new boolean[rows][cols];
		listeners=new ArrayList<BoardListener>();
		history=new LinkedList<boolean[][]>();
	}

	public int getCols(){
		return cols;
	}

	public int getRows(){
		return rows;
	}

	public void addListener(BoardListener listener) {
		listeners.add(listener);
	}

	private void fireUpdate() {
		for(BoardListener listener:listeners) {
			listener.updated(this);
		}
	}

	public void changeCellState(int x,int y){// (x, y) で指定されたセルの状態を変更する．
		record();
		cells[y][x]=!cells[y][x];
		fireUpdate();
	}

	public void changeCellStateNoRecord(int x,int y){// 変更前を記録せずにセルの状態を変更する．
		cells[y][x]=!cells[y][x];
		fireUpdate();
	}

	public boolean isAlive(int x, int y){
		return cells[y][x];
	}

	public void next() {
		record();
		boolean[][] nextGen = new boolean[rows][cols];

		for(int i = 0; i < rows; i++){
			for(int j = 0; j < cols; j++){
				nextGen[i][j]=nextGenJudge(i,j);
			}
		}

		cells=nextGen;
		fireUpdate();
	}

	private boolean nextGenJudge(int y,int x) {
		int alive=0;//周辺の生きているセルの数
		final int dx[]= {-1,-1,-1,0,1,1,1,0};
		final int dy[]= {-1,0,1,1,1,0,-1,-1};
		for(int i=0;i<8;i++) {
			try {
				if(cells[y+dy[i]][x+dx[i]]) {
					alive++;
				}
			}catch (ArrayIndexOutOfBoundsException e) {
				continue;
			}
		}
		//次世代の生存判定を返す
		if(cells[y][x]) {
			if(alive==2||alive==3) {
				return true;
			}else {
				return false;
			}
		}else {
			if(alive==3) {
				return true;
			}else {
				return false;
			}
		}
	}

	private void record() {
		if(history.size()==HISTORYSIZE) {
			history.removeFirst();
		}
		boolean[][] now=new boolean[rows][cols];
		for(int y=0;y<rows;y++) {
			for(int x=0;x<cols;x++) {
				now[y][x]=cells[y][x];
			}
		}
		history.add(now);
	}

	public void undo() {
		cells=history.removeLast();
		fireUpdate();
	}

	public boolean isUndoable() {
		return !history.isEmpty();
	}
}

