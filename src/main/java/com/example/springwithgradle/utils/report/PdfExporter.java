package com.example.springwithgradle.utils.report;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PdfExporter implements ReportExporter {

    @Override
    public void export(JasperPrint jasperPrint, String outputFileName) throws JRException {
        try {
            Path pdfDir = Paths.get("src/main/resources/reports/pdf");
            if (!Files.exists(pdfDir)) Files.createDirectories(pdfDir);
            File pdfFile = pdfDir.resolve(outputFileName + ".pdf").toFile();
            JasperExportManager.exportReportToPdfFile(jasperPrint, pdfFile.getAbsolutePath());

            System.out.println("✅ PDF exported to: " + pdfFile.getAbsolutePath());
        } catch (IOException e) {
            throw new JRException("Failed to export PDF: " + e.getMessage(), e);
        }
    }
}
