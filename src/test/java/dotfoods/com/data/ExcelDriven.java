package dotfoods.com.data;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDriven {

	public String[] getData() throws IOException {

		ArrayList<String> dotNumbers = new ArrayList<String>();
		FileInputStream fis = new FileInputStream(
				"D:\\DotFoods\\DotFoods\\src\\test\\java\\dotfoods\\com\\data\\DotNumber(searchMultiple).xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheetAt(0);

		Iterator<Row> rows = sheet.iterator();

		Row firstRow = rows.next();
		Iterator<Cell> cellIerator = firstRow.iterator();
		int columnIndex = -1;
		while (cellIerator.hasNext()) {
			Cell cell = cellIerator.next();
			if (cell.getStringCellValue().equalsIgnoreCase("Dot Number")) {
				columnIndex = cell.getColumnIndex();
				break;
			}
		}

		while (rows.hasNext()) {
			Row r = rows.next();
			Cell c = r.getCell(columnIndex);
			if (c != null) {
				if (c.getCellType() == CellType.STRING) {
					dotNumbers.add(c.getStringCellValue());
				} else {
					dotNumbers.add(NumberToTextConverter.toText(c.getNumericCellValue()));
				}
			}
		}

		workbook.close();
		fis.close();
		String[] array = dotNumbers.toArray(new String[dotNumbers.size()]);
		return array;
	}
}
