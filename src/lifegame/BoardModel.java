package lifegame;

public class BoardModel{
	private int cols;
	private int rows;
	private boolean[][] cells;

	public BoardModel(int c,int r){
		cols = c;
		rows = r;
		cells = new boolean[rows][cols];
	}

	public int getCols(){
		return cols;
	}

	public int getRows(){
		return rows;
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

	public void changeCellState(int x,int y){// (x, y) で指定されたセルの状態を変更する．
		cells[y][x]=!cells[y][x];
	}
}

