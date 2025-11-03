package com.example.springwithgradle.utils.report;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

import java.io.File;
import java.io.FileNotFoundException;

public interface ReportExporter {
    void export(JasperPrint jasperPrint, String outputFileName) throws JRException, FileNotFoundException;
}
