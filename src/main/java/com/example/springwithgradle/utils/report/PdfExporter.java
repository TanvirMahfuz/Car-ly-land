package com.example.springwithgradle.utils.report;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class PdfExporter implements ReportExporter {
    @Override
    public void export(JasperPrint jasperPrint, File outputFile) throws JRException, FileNotFoundException {
        JasperExportManager.exportReportToPdfStream(jasperPrint, new FileOutputStream(outputFile));
    }
}


