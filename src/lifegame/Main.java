package lifegame;

public class Main{
	public static void main(String[] args){
		BoardModel model1 = new BoardModel(12,10);
		System.out.print(System.identityHashCode(model1));
		System.out.print(",Cols=" + model1.getCols());
		System.out.println(",Rows=" + model1.getRows());
		BoardModel model2 = new BoardModel(20,15);
		System.out.print(System.identityHashCode(model2));
		System.out.print(",Cols=" + model2.getCols());
		System.out.println(",Rows=" + model2.getRows());
	}
}
