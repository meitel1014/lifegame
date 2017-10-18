package lifegame;

import java.util.ArrayList;

public class BoardModel{
	private int cols;
	private int rows;
	private boolean[][] cells;
	private ArrayList<BoardListener> listeners;
	private int dx[]= {-1,-1,-1,0,1,1,1,0};
	private int dy[]= {-1,0,1,1,1,0,-1,-1};

	public BoardModel(int c,int r){
		cols = c;
		rows = r;
		cells = new boolean[rows][cols];
		listeners=new ArrayList<BoardListener>();
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

	public void printForDebug(){
		String out="";
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < cols; j++){
				if(cells[i][j]){
					out+='*';
				}else{
					out+='.';
				}
			}
			System.out.println(out);
			out="";
		}
	}

	private void fireUpdate() {
		for(BoardListener listener:listeners) {
			listener.updated(this);
		}
	}

	public void changeCellState(int x,int y){// (x, y) で指定されたセルの状態を変更する．
		cells[y][x]=!cells[y][x];
		fireUpdate();
	}

	public void next() {
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
		for(int i=0;i<8;i++) {
			try {
				if(cells[y+dy[i]][x+dx[i]]) {
					alive++;
				}
			}catch (ArrayIndexOutOfBoundsException e) {
				continue;
			}
		}
		//次世代の生存判定を記録
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
}

