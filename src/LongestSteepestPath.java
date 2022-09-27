import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class LongestSteepestPath {

	private int[][] matrix;
	private PathData[][] pathData;

	public LongestSteepestPath(String path) {
		ReadAndInitializeMatrixDataFromFile(path);
	}

	/**
	 * Find and return the best path
	 *
	 * @return CalculatedResultData return an object containing path data
	 */
	public CalculatedResultData findLongestAndSteepestPath() {
		int startFromRow = 0;
		int startFromColumn = 0;
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				if (getCellPathInformation(i, j)
						.compareTo(getCellPathInformation(startFromRow, startFromColumn)) == 1) {
					startFromRow = i;
					startFromColumn = j;
				}
			}
		}

		return obtainCalculatedPathInformation(startFromRow, startFromColumn);
	}

	/**
	 * Traverse the calculated path and store its data into on object
	 *
	 * @param rowNdx
	 * @param colNdx
	 * @return CalculatedResultData returns an object containing the result data
	 */
	private CalculatedResultData obtainCalculatedPathInformation(int rowNdx, int colNdx) {
		CalculatedResultData resultData = null;
		PathData currCellInfo = getCellPathInformation(rowNdx, colNdx);
		int[] path = new int[currCellInfo.pathLength];
		path[0] = matrix[rowNdx][colNdx];
		int idx = 1;

		while (currCellInfo.pathLength > 1) {
			path[idx] = matrix[currCellInfo.nextCellRow][currCellInfo.nextCellColumn];
			idx++;
			currCellInfo = getCellPathInformation(currCellInfo.nextCellRow, currCellInfo.nextCellColumn);
		}

		resultData = new CalculatedResultData(path);
		return resultData;
	}

	/**
	 * Get the path information of a cell if available, else calculate the best path
	 *
	 * @param rowNdx matrix row index
	 * @param colNdx matrix column index
	 * @return PathInfo This returns the PathInfo containing information of a cell
	 *         path
	 */
	private PathData getCellPathInformation(int rowNdx, int colNdx) {
		if (pathData[rowNdx][colNdx] == null) {
			pathData[rowNdx][colNdx] = findBestPath(rowNdx, colNdx);
		}

		return pathData[rowNdx][colNdx];
	}

	/**
	 * Navigate and Compare possible cell path value to it's neighbors and store the
	 * information into a list.
	 *
	 * @param rowNdx matrix row index
	 * @param colNdx matrix column index
	 * @return PathInfo This returns the best path based on the list of possible
	 *         paths
	 */
	private PathData findBestPath(int rowNdx, int colNdx) {
		List<PathData> possiblePathsFromCell = new ArrayList<PathData>();

		// check west side of cell
		if (colNdx != 0 && matrix[rowNdx][colNdx - 1] < matrix[rowNdx][colNdx]) {
			PathData p = getCellPathInformation(rowNdx, colNdx - 1);
			possiblePathsFromCell
					.add(new PathData(p.pathLength + 1, matrix[rowNdx][colNdx], p.endCell, rowNdx, colNdx - 1));
		}

		// check east side of cell
		if (colNdx != matrix[rowNdx].length - 1 && matrix[rowNdx][colNdx + 1] < matrix[rowNdx][colNdx]) {
			PathData p = getCellPathInformation(rowNdx, colNdx + 1);
			possiblePathsFromCell
					.add(new PathData(p.pathLength + 1, matrix[rowNdx][colNdx], p.endCell, rowNdx, colNdx + 1));
		}

		// check south side of cell
		if (rowNdx != matrix.length - 1 && matrix[rowNdx + 1][colNdx] < matrix[rowNdx][colNdx]) {
			PathData p = getCellPathInformation(rowNdx + 1, colNdx);
			possiblePathsFromCell
					.add(new PathData(p.pathLength + 1, matrix[rowNdx][colNdx], p.endCell, rowNdx + 1, colNdx));
		}

		// check north side of cell
		if (rowNdx != 0 && matrix[rowNdx - 1][colNdx] < matrix[rowNdx][colNdx]) {
			PathData p = getCellPathInformation(rowNdx - 1, colNdx);
			possiblePathsFromCell
					.add(new PathData(p.pathLength + 1, matrix[rowNdx][colNdx], p.endCell, rowNdx - 1, colNdx));
		}

		if (possiblePathsFromCell.isEmpty()) {
			return new PathData(1, matrix[rowNdx][colNdx], matrix[rowNdx][colNdx], -1, -1);
		}

		return Collections.max(possiblePathsFromCell);
	}

	/**
	 * Reads a file containing matrix data and stores it into a 2-d array
	 *
	 * @param filePath path of file
	 * @return nothing
	 */
	private void ReadAndInitializeMatrixDataFromFile(String filePath) {
		int rows = 0;
		int columns = 0;
		try (Scanner sc = new Scanner(new BufferedReader(new FileReader(filePath)))) {
			int fileNdx = 0;
			while (sc.hasNextLine()) {
				String[] line = sc.nextLine().trim().split(" ");

				if (fileNdx == 0) {
					// get the size of the matrix
					rows = Integer.parseInt(line[0]);
					columns = Integer.parseInt(line[1]);
					matrix = new int[rows][columns];
					pathData = new PathData[rows][];

				}

				for (int i = 0; fileNdx > 0 && i < line.length; i++) {
					matrix[fileNdx - 1][i] = Integer.parseInt(line[i]);
					pathData[fileNdx - 1] = new PathData[line.length];
				}

				fileNdx++;
			}
		} catch (Exception e) {

		}
	}

}
