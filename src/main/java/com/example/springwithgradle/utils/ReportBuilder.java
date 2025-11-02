package com.example.springwithgradle.utils;

import com.example.springwithgradle.utils.report.CsvExporter;
import com.example.springwithgradle.utils.report.PdfExporter;
import com.example.springwithgradle.utils.report.ReportExporter;
import com.example.springwithgradle.utils.report.XlsxExporter;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ReportBuilder<T> {

    private final JasperReport jasperReport;
    private final String jasperFilePath;
    
    public ReportBuilder(String jrxmlFileName) throws JRException {
        this.jasperFilePath = compileJrxmlToJasper(jrxmlFileName);

        if (this.jasperFilePath == null || this.jasperFilePath.isEmpty()) {
            throw new JRException("❌ Failed to compile JRXML: " + jrxmlFileName);
        }

        File jasperFile = new File(this.jasperFilePath);
        if (!jasperFile.exists()) {
            throw new JRException("❌ Compiled Jasper file not found: " + this.jasperFilePath);
        }

        this.jasperReport = (JasperReport) JRLoader.loadObject(jasperFile);
    }


    private String compileJrxmlToJasper(String jrxmlFileName) throws JRException {
        try {
            Path jrxmlPath = Paths.get(jrxmlFileName);
            if (!Files.exists(jrxmlPath)) {
                throw new JRException("❌ JRXML file not found: " + jrxmlPath.toAbsolutePath());
            }
            Path reportsDir = Paths.get("src", "main", "resources", "reports");
            if (!Files.exists(reportsDir)) {
                Files.createDirectories(reportsDir);
            }

            // Compile and save
            String jasperFileName = jrxmlPath.getFileName().toString().replace(".jrxml", ".jasper");
            Path jasperPath = reportsDir.resolve(jasperFileName);

            JasperCompileManager.compileReportToFile(jrxmlPath.toString(), jasperPath.toString());
            System.out.println("✅ Compiled JRXML to: " + jasperPath.toAbsolutePath());

            return jasperPath.toString();

        } catch (Exception e) {
            System.err.println("❌ Error compiling JRXML: " + e.getMessage());
            throw new JRException(e);
        }
    }


    public void generateReport(List<T> dataList, String inputFileName, String outputFileType) {
        generateReport(dataList, Collections.singletonMap("createdBy", "Spring Boot + JasperReports"),inputFileName, ExporterFactory.getExporter(outputFileType));
    }

    public void generateReport(List<T> dataList, Map<String, Object> params, String inputFileName, ReportExporter exporter) {
        try {
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(dataList);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
            exporter.export(jasperPrint, notify(outputFileName));
            System.out.println("✅ Report exported successfully: " + outputFileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void exportToPdf(JasperPrint jasperPrint, String outputFileName) {
        File reportsDir = new File("reports");
        if (!reportsDir.exists()) {
            reportsDir.mkdirs();
        }

        File outputFile = new File(reportsDir, outputFileName);

        try (FileOutputStream outputStream = new FileOutputStream(outputFile)) {
            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
            System.out.println("✅ Report exported successfully: " + outputFile.getAbsolutePath());
        } catch (Exception e) {
            System.err.println("❌ Failed to export report: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public String getJasperFilePath() {
        return jasperFilePath;
    }
}
