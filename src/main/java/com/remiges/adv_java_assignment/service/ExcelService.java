package com.remiges.adv_java_assignment.service;

import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.remiges.adv_java_assignment.entity.Departments;
import com.remiges.adv_java_assignment.repo.DeptRepo;
import com.remiges.adv_java_assignment.repo.EmployeeRepository;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class ExcelService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DeptRepo deptRepo;

    public void generateExcel(HttpServletResponse response) throws IOException {
        List<Departments> depts = deptRepo.findAll();

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Employee Info");
        XSSFRow row = sheet.createRow(0);

        row.createCell(0).setCellValue("ID");
        row.createCell(1).setCellValue("DeptName");

        int dataRowIndex = 1;

        for (Departments dept : depts) {
            XSSFRow dataRow = sheet.createRow(dataRowIndex);
            dataRow.createCell(0).setCellValue(dept.getId());
            dataRow.createCell(1).setCellValue(dept.getDeptName());
            dataRowIndex++;
        }

        ServletOutputStream ops = response.getOutputStream();
        workbook.write(ops);
        workbook.close();
        ops.close();
    }

    public void generatePdf(HttpServletResponse response) throws Exception {

        Document pdfDoc = new Document(PageSize.A4);

        ServletOutputStream outputStream = response.getOutputStream();

        PdfWriter.getInstance(pdfDoc, outputStream);

        pdfDoc.open();

        // To set font style and size
        Font fontTitle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        fontTitle.setSize(20);

        // To set paragraph style and alignment
        Paragraph p = new Paragraph("Department Info", fontTitle);
        p.setAlignment(Paragraph.ALIGN_CENTER);
        pdfDoc.add(p);

        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setWidths(new int[] { 2, 2 });
        table.setSpacingBefore(5);

        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(CMYKColor.BLUE);
        cell.setPadding(5);
        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        font.setColor(CMYKColor.WHITE);

        cell.setPhrase(new Phrase("Department ID", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Depatment Name", font));
        table.addCell(cell);

        List<Departments> depts = deptRepo.findAll();

        for (Departments dept : depts) {
            table.addCell(String.valueOf(dept.getId()));
            table.addCell(dept.getDeptName());
        }

        pdfDoc.add(table);
        pdfDoc.close();
        outputStream.close();

    }
}
