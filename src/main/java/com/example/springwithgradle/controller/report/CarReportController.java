package com.example.springwithgradle.controller.report;

import com.example.springwithgradle.dto.CarDTO;
import com.example.springwithgradle.entity.Engine;
import com.example.springwithgradle.entity.User;
import com.example.springwithgradle.utils.ReportBuilder;
import net.sf.jasperreports.engine.JRException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/car-reports")
public class CarReportController {

    private final ReportBuilder<CarDTO> reportBuilder;

    public CarReportController() throws JRException {
        // Point to your JRXML file inside resources
        this.reportBuilder = new ReportBuilder<>("src/main/resources/reports/car");
    }

    @GetMapping("/basic-report")
    public String basicReport() {
        try {
            // Create sample data
            User user = new User();
            user.setFirstName("John Doe");

            Engine engine = new Engine();
            engine.setType("V8 Turbo");

            List<CarDTO> cars = List.of(
                    new CarDTO("Red", 1L, "Toyota", "Supra", 2022, user, engine),
                    new CarDTO("Blue", 2L, "BMW", "M4", 2021, user, engine),
                    new CarDTO("Black", 3L, "Tesla", "Model S", 2023, user, engine)
            );

            // Generate the report
            reportBuilder.generateReport(cars,"basic_report.jrxml","pdf");

            return "✅ Car report generated successfully!";
        } catch (Exception e) {
            e.printStackTrace();
            return "❌ Failed to generate car report: " + e.getMessage();
        }
    }
}
