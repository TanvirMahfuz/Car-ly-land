package com.example.springwithgradle.utils;

import com.example.springwithgradle.utils.report.CsvExporter;
import com.example.springwithgradle.utils.report.PdfExporter;
import com.example.springwithgradle.utils.report.ReportExporter;
import com.example.springwithgradle.utils.report.XlsxExporter;

public class ExporterFactory {
    public static ReportExporter getExporter(String format) {
        return switch (format.toLowerCase()) {
            case "pdf" -> new PdfExporter();
            case "xlsx" -> new XlsxExporter();
            case "csv" -> new CsvExporter();
            default -> throw new IllegalArgumentException("Unsupported format: " + format);
        };
    }
}

