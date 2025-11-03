package com.example.springwithgradle.utils;

import com.example.springwithgradle.utils.report.ReportExporter;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.util.JRSaver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportBuilder<T> {

    private final JasperReport jasperReport;
    private final String jasperFilePath;

    public ReportBuilder(String jrxmlPathInResources) throws JRException, IOException {

        InputStream jrxmlStream = getClass().getResourceAsStream(jrxmlPathInResources);

        if (jrxmlStream == null) {
            throw new JRException("JRXML not found in resources: " + jrxmlPathInResources);
        }

        Path jasperDir = Paths.get("src/main/resources/jasper-reports");
        if (!Files.exists(jasperDir)) Files.createDirectories(jasperDir);

        String jrxmlFileName = Paths.get(jrxmlPathInResources).getFileName().toString();
        Path jasperPath = jasperDir.resolve(jrxmlFileName.replace(".jrxml", ".jasper"));

        JasperReport jasperReport = JasperCompileManager.compileReport(jrxmlStream);
        JRSaver.saveObject(jasperReport, jasperPath.toFile());

        this.jasperFilePath = jasperPath.toString();
        this.jasperReport = jasperReport;

        System.out.println("✅ Compiled JRXML to: " + jasperPath.toAbsolutePath());
    }


    public void generateReport(List<T> dataList, Map<String,Object> params,  String outputFileName, String fileType) throws JRException, IOException {
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(dataList);

        ReportExporter exporter = ExporterFactory.getExporter(fileType);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
        exporter.export(jasperPrint, outputFileName);
    }



    public String getJasperFilePath() {
        return jasperFilePath;
    }
}