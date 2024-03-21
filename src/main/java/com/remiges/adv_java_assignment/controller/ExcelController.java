package com.remiges.adv_java_assignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.remiges.adv_java_assignment.service.ExcelService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class ExcelController {

    @Autowired
    private ExcelService excelService;

    @GetMapping("/excel")
    public void generateExcelReport(HttpServletResponse response) throws Exception {

        response.setContentType("application/octet-stream");

        String headerKey = "Content-Disposition";
        String headerValue = "attachment;filename=departments.xls";

        response.setHeader(headerKey, headerValue);

        excelService.generateExcel(response);
    }

    @GetMapping("/pdf")
    public void generatePdfReport(HttpServletResponse response) throws Exception {

        response.setContentType("application/pdf");

        String headerKey = "content-Disposition";
        String headerValue = "attachment;filename=departments.pdf";

        response.addHeader(headerKey, headerValue);

        excelService.generatePdf(response);
    }

}
