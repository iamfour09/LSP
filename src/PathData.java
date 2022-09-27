
public class PathData implements Comparable<PathData> {

	int pathLength;
	int startCell;
	int endCell;
	int nextCellColumn;
	int nextCellRow;

	public PathData(int length, int startValue, int endValue, int nextCellRow, int nextCellColumn) {
		this.pathLength = length;
		this.startCell = startValue;
		this.endCell = endValue;
		this.nextCellColumn = nextCellColumn;
		this.nextCellRow = nextCellRow;
	}

	@Override
	public int compareTo(PathData nextPath) {
		if (this.pathLength < nextPath.pathLength || (this.pathLength == nextPath.pathLength
				&& this.startCell - this.endCell < nextPath.startCell - nextPath.endCell)) {
			return -1;
		}

		if (this.pathLength > nextPath.pathLength || (this.pathLength == nextPath.pathLength
				&& this.startCell - this.endCell > nextPath.startCell - nextPath.endCell)) {
			return 1;
		}

		return 0;
	}

	@Override
	public String toString() {
		return this.startCell + " -> " + this.endCell + " == " + this.pathLength + " [" + this.nextCellRow + "]["
				+ this.nextCellColumn + "]";
	}
}
