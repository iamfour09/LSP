import java.util.Arrays;

public class CalculatedResultData {
	private int pathLength;
	private int dropLength;
	private int[] pathArray;

	public CalculatedResultData(int[] pathArray) {
		this.pathArray = pathArray;
		this.calculateLengths();
	}

	private void calculateLengths() {
		pathLength = pathArray.length;
		if (pathLength > 0) {
			dropLength = pathArray[0] - pathArray[pathLength - 1];
		}
	}

	@Override
	public String toString() {
		return "Length of calculated path: " + pathLength + "\n" + "Drop of calculated path: " + dropLength + "\n"
				+ "Calculated path: " + Arrays.toString(pathArray);

	}
}
