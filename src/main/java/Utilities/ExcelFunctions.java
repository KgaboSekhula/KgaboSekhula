package Utilities;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ExcelFunctions {

    public static FileOutputStream output_document;
    private Cell cell = null;
    public static int ScenarioCount =1;
    public ArrayList columnsNames = new ArrayList();
    public static XSSFWorkbook wb;
    private XSSFSheet sheet;
    public static int totalColumns =0;

    public  ExcelFunctions(FileInputStream input_document) {
        try {

            wb = new XSSFWorkbook(input_document);
            input_document.close();
            sheet = wb.getSheetAt(1);
            while (true){
                cell = sheet.getRow(0).getCell(totalColumns);
                if (cell == null) {
                    break;
                }
                String names = cell.getStringCellValue();
                columnsNames.add(names.trim());
                totalColumns++;

            }
            while (true){
                Row row = sheet.getRow(ScenarioCount);
                if (row == null) {
                    break;
                }
                ScenarioCount++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String ReadCell(int iScenario,int Column) throws IOException {

        cell = sheet.getRow(iScenario).getCell(Column);
        DataFormatter formatter = new DataFormatter();
        return formatter.formatCellValue(cell).trim();
    }

    public void WriteToCell(int Scenario, int Column, String Value ) throws IOException {

        Row row = sheet.getRow(Scenario);
        cell = sheet.getRow(Scenario).getCell(Column);
        if (cell == null) {
            cell = row.createCell(Scenario);
        }
        CellStyle cs = wb.createCellStyle();
        cs.setWrapText(true);   //Wrapping text
        cell.setCellStyle(cs);
        cell.setCellValue(Value);
        FileOutputStream out = new FileOutputStream(System.getProperty("user.dir")+"\\CC\\DataSheets\\testNew.xlsx");
        wb.write(out);
    }
}

