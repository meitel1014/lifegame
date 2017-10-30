package lifegame;

public class Main{
	public static void main(String[] args){
		BoardModel model = new BoardModel(10, 10);
		model.addListener(new ModelPrinter());
		model.changeCellState(1, 1);
		model.changeCellState(2, 2);
		model.changeCellState(0, 3);
		model.changeCellState(1, 3);
		model.changeCellState(2, 3);
		model.changeCellState(4, 4);
		model.changeCellState(4, 4);
		for (int i=0; i<12; ++i) {
			model.next();
		}
		while (model.isUndoable()) {
			model.undo();
		}
	}
}
