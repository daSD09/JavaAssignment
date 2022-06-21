package question2;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.Column;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class MatchMismatch {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the path of the source dataSource");
		String sourcePath = sc.nextLine();
		System.out.println("Enter the path of the target dataSource");
		String targetPath = sc.nextLine();
		Map<Integer, String> sourceData = new HashMap<Integer, String>();
		Map<Integer, String> targetData = new HashMap<Integer, String>();
		try {
			FileInputStream sourceFile = new FileInputStream(new File(sourcePath));
			HSSFWorkbook sourceWorkbook = new HSSFWorkbook(sourceFile);
			HSSFSheet sourceSheet = sourceWorkbook.getSheetAt(0);
			FormulaEvaluator formulaEvaluator1 = sourceWorkbook.getCreationHelper().createFormulaEvaluator();

			for (Row row : sourceSheet) {
				int cellNum = 0;
				for (Cell cell : row) {
					if (formulaEvaluator1.evaluateInCell(cell).getCellType() == Cell.CELL_TYPE_STRING) {

						sourceData.put(cellNum, cell.getStringCellValue());
						cellNum++;

					}
				}
			}

			FileInputStream targetFile = new FileInputStream(new File(targetPath));
			HSSFWorkbook targetWorkbook = new HSSFWorkbook(targetFile);
			HSSFSheet targetSheet = targetWorkbook.getSheetAt(0);
			FormulaEvaluator formulaEvaluator2 = targetWorkbook.getCreationHelper().createFormulaEvaluator();

			for (Row row : targetSheet) {
				int cellNum = 0;
				for (Cell cell : row) {
					if (formulaEvaluator1.evaluateInCell(cell).getCellType() == Cell.CELL_TYPE_STRING) {
						targetData.put(cellNum, cell.getStringCellValue());
						cellNum++;

					}
				}
			}

			List<String> matches = new ArrayList<String>();
			List<String> misMatches = new ArrayList<String>();
			List<String> listSource = new ArrayList<String>(sourceData.values());
			for (String val : targetData.values()) {
				int found = 0;
				if (listSource.contains(val)) {
					found = listSource.indexOf(val);
					if (sourceData.get(found) == targetData.get(found)) {
						matches.add(val);
					} else {
						misMatches.add(val);
						
					}
				}
			}

			System.out.println("Total matches in two data sources are:");
			System.out.println(matches);
			System.out.println();
			System.out.println("Total misMatches in two data sources are:");
			System.out.println(misMatches);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
