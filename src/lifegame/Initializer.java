package lifegame;

import javax.swing.JOptionPane;

public class Initializer{
	private int rows, cols;

	public boolean initialize(){
		String rowInput = JOptionPane.showInputDialog("Rows(10-100):", "15");
		if(rowInput == null){
			return false;
		}
		try{
			rows = Integer.parseInt(rowInput);
			if(rows < 10 || rows > 100){
				throw new NumberFormatException();
			}
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(null,
					"Not appropriate number", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		String colInput = JOptionPane.showInputDialog("Cols(10-100):", "10");
		if(colInput == null){
			return false;
		}
		try{
			cols = Integer.parseInt(colInput);
			if(cols < 10 || cols > 100){
				throw new NumberFormatException();
			}
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(null,
					"Not appropriate number", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		return true;
	}

	public int getRows(){
		return rows;
	}

	public int getCols(){
		return cols;
	}
}
