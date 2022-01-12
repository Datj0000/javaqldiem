package View.Component;

import View.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.io.*;
import java.util.Vector;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XuLyFileExcel {

    public static void changLNF(String nameLNF) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if (nameLNF.equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
        }
    }

    public void xuatExcel(JTable tbl) {
        changLNF("Windows");
        try {
            TableModel dtm = tbl.getModel();

            JFileChooser chooser = new MyFileChooser("export/");
            chooser.setDialogTitle("Lưu vào");
            FileNameExtensionFilter fnef = new FileNameExtensionFilter("Excel Files", "xls", "xlsx", "xlsm");
            chooser.setFileFilter(fnef);
            if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                // Lấy đường dẫn vừa chọn + tên tệp
                String path = chooser.getSelectedFile().getPath();
                if (!path.contains(".xlsx")) {
                    path += ".xlsx";
                }

                XSSFWorkbook workbook = new XSSFWorkbook();
                Sheet sheet = workbook.createSheet("Sheet 1");

                Font headerFont = workbook.createFont();
                headerFont.setBold(true);
                headerFont.setFontHeightInPoints((short) 14);
                headerFont.setColor(IndexedColors.WHITE.getIndex());
                CellStyle headerCellStyle = workbook.createCellStyle();
                headerCellStyle.setFont(headerFont);
                headerCellStyle.setFillBackgroundColor(IndexedColors.GREEN.getIndex());
                headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                headerCellStyle.setBorderTop(BorderStyle.THIN);
                headerCellStyle.setBorderBottom(BorderStyle.THIN);
                headerCellStyle.setBorderLeft(BorderStyle.THIN);
                headerCellStyle.setBorderRight(BorderStyle.THIN);
                headerCellStyle.setAlignment(HorizontalAlignment.CENTER);

                Row headerRow = sheet.createRow(0);

                // Tạo header
                for (int i = 0; i < dtm.getColumnCount(); i++) {
                    Cell cell = headerRow.createCell(i);
                    cell.setCellValue(dtm.getColumnName(i));
                    cell.setCellStyle(headerCellStyle);
                }

                Font contentFont = workbook.createFont();
                contentFont.setBold(false);
                contentFont.setFontHeightInPoints((short) 13);
                contentFont.setColor(IndexedColors.BLACK.getIndex());
                CellStyle contentCellStyle = workbook.createCellStyle();
                contentCellStyle.setFont(contentFont);
                contentCellStyle.setBorderTop(BorderStyle.THIN);
                contentCellStyle.setBorderBottom(BorderStyle.THIN);
                contentCellStyle.setBorderLeft(BorderStyle.THIN);
                contentCellStyle.setBorderRight(BorderStyle.THIN);

                for (int i = 0; i < dtm.getRowCount(); i++) {
                    Row row = sheet.createRow(i + 1);
                    for (int j = 0; j < dtm.getColumnCount(); j++) {
                        Cell cell = row.createCell(j);
                        cell.setCellValue(dtm.getValueAt(i, j) + "");
                        cell.setCellStyle(contentCellStyle);
                        sheet.autoSizeColumn(j);
                    }
                }

                // Ghi file
                FileOutputStream fileOut = new FileOutputStream(path);
                workbook.write(fileOut);
                fileOut.close();
                workbook.close();
                Menu.showMessage("Xuất file thành công!");
            }
        } catch (Exception e) {
            Menu.showMessage("Xuất file thất bại!");
        }
    }

    public void xuatExcel2(JTable tbl, String tenKhoi, String tenLop, String tenHocSinh, String tenNamHoc, String tenHocKy) {
        changLNF("Windows");
        try {
            TableModel dtm = tbl.getModel();

            JFileChooser chooser = new MyFileChooser("export/");
            chooser.setDialogTitle("Lưu vào");
            FileNameExtensionFilter fnef = new FileNameExtensionFilter("Excel Files", "xls", "xlsx", "xlsm");
            chooser.setFileFilter(fnef);
            if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                // Lấy đường dẫn vừa chọn + tên tệp
                String path = chooser.getSelectedFile().getPath();
                if (!path.contains(".xlsx")) {
                    path += ".xlsx";
                }

                XSSFWorkbook workbook = new XSSFWorkbook();
                Sheet sheet = workbook.createSheet("Sheet 1");

                Font headerFont = workbook.createFont();
                headerFont.setBold(true);
                headerFont.setFontHeightInPoints((short) 14);
                headerFont.setColor(IndexedColors.WHITE.getIndex());
                CellStyle headerCellStyle = workbook.createCellStyle();
                headerCellStyle.setFont(headerFont);
                headerCellStyle.setFillBackgroundColor(IndexedColors.GREEN.getIndex());
                headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                headerCellStyle.setBorderTop(BorderStyle.THIN);
                headerCellStyle.setBorderBottom(BorderStyle.THIN);
                headerCellStyle.setBorderLeft(BorderStyle.THIN);
                headerCellStyle.setBorderRight(BorderStyle.THIN);
                headerCellStyle.setAlignment(HorizontalAlignment.CENTER);

                Row row1 = sheet.createRow(0);
                Cell cell1 = row1.createCell(0, CellType.STRING);
                Cell cellc1 = row1.createCell(1, CellType.STRING);
                cell1.setCellValue("Năm học:");
                cellc1.setCellValue(tenNamHoc);
                Row row2 = sheet.createRow(1);
                Cell cell2 = row2.createCell(0, CellType.STRING);
                Cell cellc2 = row2.createCell(1, CellType.STRING);
                cell2.setCellValue("Học kỳ:");
                cellc2.setCellValue(tenHocKy);
                Row row3 = sheet.createRow(2);
                Cell cell3 = row3.createCell(0, CellType.STRING);
                Cell cellc3 = row3.createCell(1, CellType.STRING);
                cell3.setCellValue("Khối:");
                cellc3.setCellValue(tenKhoi);
                Row row4 = sheet.createRow(3);
                Cell cell4 = row4.createCell(0, CellType.STRING);
                Cell cellc4 = row4.createCell(1, CellType.STRING);
                cell4.setCellValue("Lớp:");
                cellc4.setCellValue(tenLop);
                Row row5 = sheet.createRow(4);
                Cell cell5 = row5.createCell(0, CellType.STRING);
                Cell cellc5 = row5.createCell(1, CellType.STRING);
                cell5.setCellValue("Họ và tên:");
                cellc5.setCellValue(tenHocSinh);

                Row headerRow = sheet.createRow(6);

//              Tạo header
                for (int i = 0; i < dtm.getColumnCount(); i++) {
                    Cell cell = headerRow.createCell(i);
                    cell.setCellValue(dtm.getColumnName(i));
                    cell.setCellStyle(headerCellStyle);
                }

                Font contentFont = workbook.createFont();
                contentFont.setBold(false);
                contentFont.setFontHeightInPoints((short) 13);
                contentFont.setColor(IndexedColors.BLACK.getIndex());
                CellStyle contentCellStyle = workbook.createCellStyle();
                contentCellStyle.setFont(contentFont);
                contentCellStyle.setBorderTop(BorderStyle.THIN);
                contentCellStyle.setBorderBottom(BorderStyle.THIN);
                contentCellStyle.setBorderLeft(BorderStyle.THIN);
                contentCellStyle.setBorderRight(BorderStyle.THIN);

                for (int i = 0; i < dtm.getRowCount(); i++) {
                    Row row = sheet.createRow(i + 7);
                    for (int j = 0; j < dtm.getColumnCount(); j++) {
                        Cell cell = row.createCell(j);
                        cell.setCellValue(dtm.getValueAt(i, j) + "");
                        cell.setCellStyle(contentCellStyle);
                        sheet.autoSizeColumn(j);
                    }
                }

                // Ghi file
                FileOutputStream fileOut = new FileOutputStream(path);
                workbook.write(fileOut);
                fileOut.close();
                workbook.close();
                Menu.showMessage("Xuất file thành công!");
            }
        } catch (Exception e) {
            Menu.showMessage("Xuất file thất bại!");
        }
    }

    public void nhapExcel(JTable tbl) {
        changLNF("Windows");
        try {
            TableModel dtm = tbl.getModel();

            JFileChooser chooser = new MyFileChooser("export/");
            chooser.setDialogTitle("Chọn file");
            FileNameExtensionFilter fnef = new FileNameExtensionFilter("Excel Files", "xls", "xlsx", "xlsm");
            chooser.setFileFilter(fnef);

            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                File fileSelected = chooser.getSelectedFile();
                FileInputStream fis = new FileInputStream(fileSelected);
                BufferedInputStream bis = new BufferedInputStream(fis);

                XSSFWorkbook wb = new XSSFWorkbook(bis);
                Sheet sheet = wb.getSheetAt(0);

                DefaultTableModel dtmtbl = (DefaultTableModel) dtm;
                dtmtbl.setRowCount(0);
                for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                    Row row = sheet.getRow(i);
                    Vector vec = new Vector();
                    for (int j = 0; j < row.getLastCellNum(); j++) {
                        if (dtmtbl.getColumnCount() != row.getLastCellNum()) {
                            return;
                        }
                        Cell cell = row.getCell(j);
                        vec.add(cell.getStringCellValue());
                    }
                    dtmtbl.addRow(vec);
                    Menu.showMessage("Nhập file thành công!");
                }
            }
        } catch (Exception e) {
            Menu.showMessage("Nhập file thất bại!");
        }
    }

}
