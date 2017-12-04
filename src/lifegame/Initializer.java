package lifegame;

import javax.swing.JOptionPane;

public class Initializer {
	private int rows,cols;

	public boolean initialize() {
		String rowst = JOptionPane.showInputDialog("Rows:", "15");
		try{
			rows = Integer.parseInt(rowst);
			if(rows <= 0){
				throw new NumberFormatException();
			}
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(null,
					"Not appropriate number", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		String colst = JOptionPane.showInputDialog("Cols:", "10");
		try{
			cols = Integer.parseInt(colst);
			if(cols <= 0){
				throw new NumberFormatException();
			}
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(null,
					"Not appropriate number", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		return true;
	}

	public int getRows() {
		return rows;
	}

	public int getCols() {
		return cols;
	}
}
