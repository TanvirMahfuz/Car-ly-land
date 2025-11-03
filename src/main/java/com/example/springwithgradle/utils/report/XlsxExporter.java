package com.example.springwithgradle.utils.report;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class XlsxExporter implements ReportExporter {

    @Override
    public void export(JasperPrint jasperPrint, String outputFileName) throws JRException {
        try {
            Path xlsxDir = Paths.get("src/main/resources/reports/xlsx");
            if (!Files.exists(xlsxDir)) Files.createDirectories(xlsxDir);

            File xlsxFile = xlsxDir.resolve(outputFileName + ".xlsx").toFile();

            JRXlsxExporter exporter = new JRXlsxExporter();
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(xlsxFile));
            exporter.exportReport();

            System.out.println("✅ XLSX exported to: " + xlsxFile.getAbsolutePath());
        } catch (IOException e) {
            throw new JRException("Failed to export XLSX: " + e.getMessage(), e);
        }
    }
}
