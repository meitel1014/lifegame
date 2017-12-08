package lifegame;

import javax.swing.JOptionPane;

public class Initializer {
	private int rows, cols;

	public boolean initialize(String filename) {
		if(filename==null) {
			return dialog();
		}else {
			return readFile(filename);
		}
	}

	private boolean dialog() {
		try {
			String rowst = JOptionPane.showInputDialog("Rows:", "15");
			if (rowst == null) {
				return false;
			}
			rows = Integer.parseInt(rowst);
			if (rows <= 0) {
				throw new NumberFormatException();
			}

			String colst = JOptionPane.showInputDialog("Cols:", "10");
			if (colst == null) {
				return false;
			}
			cols = Integer.parseInt(colst);
			if (cols <= 0) {
				throw new NumberFormatException();
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null,
					"Not appropriate number", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		return true;
	}

	private boolean readFile(String filename) {


		return true;
	}

	public int getRows() {
		return rows;
	}

	public int getCols() {
		return cols;
	}
}
