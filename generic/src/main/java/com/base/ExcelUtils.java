package com.base;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelUtils {

    HSSFWorkbook wb = null;
    HSSFSheet sheet = null;
    int numberOfRows, numberOfCol;

    public static void main(String[] args) throws IOException {
        ExcelUtils excelReader = new ExcelUtils();
        System.out.println("Specific Data********");
        String specificData = excelReader.getValueOf("src/test/resources/Book.xls", 0, 1);
        System.out.println(specificData);

        System.out.println("Specific Datas Of Row********");
        String[] specificRow = excelReader.getValueOf("src/test/resources/Book.xls", 0);
        for (int i = 0; i < specificRow.length; i++) {
            System.out.println(specificRow[i]);
        }
    }

    public String[] getValueOf(String path, int column) throws IOException {
        String[] data;
        File file = new File(path);
        FileInputStream fis = new FileInputStream(file);
        wb = new HSSFWorkbook(fis);
        sheet = wb.getSheetAt(0);
        numberOfRows = sheet.getLastRowNum();
        numberOfCol = sheet.getRow(column).getLastCellNum();
        data = new String[numberOfRows + 1];
        for (int i = 1; i < data.length; i++) {
            HSSFRow rows = sheet.getRow(i);
            for (int j = 0; j < numberOfCol; j++) {
                HSSFCell cell = rows.getCell(j);
                String cellData = getCellValue(cell);
                data[i] = cellData;
            }
        }
        return data;
    }

    public String getValueOf(String path, int column, int row) throws IOException {
        String data;
        File file = new File(path);
        FileInputStream fis = new FileInputStream(file);
        wb = new HSSFWorkbook(fis);
        sheet = wb.getSheetAt(0);
        numberOfRows = sheet.getLastRowNum();
        numberOfCol = sheet.getRow(0).getLastCellNum();
        HSSFRow rows = sheet.getRow(row);
        HSSFCell cell = rows.getCell(column);
        String cellData = getCellValue(cell);
        data = cellData;
        return data;
    }

    private String getCellValue(HSSFCell cell) {
        Object value = null;
        int dataType = cell.getCellType();
        switch (dataType) {
            case HSSFCell.CELL_TYPE_NUMERIC:
                value = cell.getNumericCellValue();
                break;
            case HSSFCell.CELL_TYPE_STRING:
                value = cell.getStringCellValue();
                break;
            case HSSFCell.CELL_TYPE_BOOLEAN:
                value = cell.getBooleanCellValue();
                break;
        }
        return value.toString();
    }
}