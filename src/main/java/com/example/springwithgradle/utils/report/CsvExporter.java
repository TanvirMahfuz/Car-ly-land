package com.example.springwithgradle.utils.report;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleWriterExporterOutput;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CsvExporter implements ReportExporter {

    @Override
    public void export(JasperPrint jasperPrint, String outputFileName) throws JRException {
        try {
            Path csvDir = Paths.get("src/main/resources/reports/csv");
            if (!Files.exists(csvDir)) Files.createDirectories(csvDir);

            File csvFile = csvDir.resolve(outputFileName + ".csv").toFile();

            JRCsvExporter exporter = new JRCsvExporter();
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleWriterExporterOutput(csvFile));
            exporter.exportReport();

            System.out.println("✅ CSV exported to: " + csvFile.getAbsolutePath());
        } catch (IOException e) {
            throw new JRException("Failed to export CSV: " + e.getMessage(), e);
        }
    }
}
