package com.example.springwithgradle.utils;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JRException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JasperCompiler {

    public static String compileJasper(String inputJrxmlPath, String outputJasperPath) {
        try {
            Path outputDir = Paths.get(outputJasperPath).getParent();
            if (outputDir != null && !Files.exists(outputDir)) {
                Files.createDirectories(outputDir);
            }

            JasperCompileManager.compileReportToFile(inputJrxmlPath, outputJasperPath);

            System.out.println("✅ Successfully compiled report:");
            System.out.println("   JRXML: " + inputJrxmlPath);
            System.out.println("   JASPER: " + outputJasperPath);

            return outputJasperPath;

        } catch (JRException e) {
            System.err.println("❌ JasperReports error: " + e.getMessage());
            e.printStackTrace();
            return "";
        } catch (Exception e) {
            System.err.println("❌ Unexpected error: " + e.getMessage());
            e.printStackTrace();
            return "";
        }
    }
}
