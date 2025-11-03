package com.example.springwithgradle.controller.report;

import com.example.springwithgradle.dto.CarDTO;
import com.example.springwithgradle.dto.CarReportDTO;
import com.example.springwithgradle.entity.Car;
import com.example.springwithgradle.entity.Engine;
import com.example.springwithgradle.entity.User;
import com.example.springwithgradle.utils.ReportBuilder;
import net.sf.jasperreports.engine.JRException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/car-reports")
public class CarReportController {

    @GetMapping("/basic-report")
    public String basicReport() {
        try {
            User user = new User();
            user.setFirstName("John");
            user.setLastName("Doe");

            User user2 = new User();
            user2.setFirstName("Jane");
            user2.setLastName("Dove");

            Engine engine = new Engine();
            engine.setType("V8 Turbo");

            List<CarReportDTO> cars = new ArrayList<>();
            cars.add(new CarReportDTO("RED", 1L, "Toyota", "Supra", 2024, user.getFirstName(), engine.getType()));
            cars.add(new CarReportDTO("Blue", 2L, "BMW", "M4", 2021, user.getFirstName(), engine.getType()));
            cars.add(new CarReportDTO("Black", 3L, "Tesla", "Model S", 2023, user2.getFirstName(), engine.getType()));

            List<Map<String, List<CarReportDTO>>> jasperData = new ArrayList<>();
            jasperData.add(Map.of("cars", cars));

            ReportBuilder<Map<String, List<CarReportDTO>>> builder = new ReportBuilder<>("/static/reports/basic_report.jrxml");
            builder.generateReport(jasperData, null, "basic_report", "pdf");

            return "✅ Car report generated successfully!";

        } catch (Exception e) {
            e.printStackTrace();
            return "❌ Failed to generate car report: " + e.getMessage();
        }
    }

    @GetMapping("/car-sub-report")
    public String carSubReport() {
        try {
            User user = new User();
            user.setFirstName("John");
            user.setLastName("Doe");

            User user1 = new User();
            user1.setFirstName("Jane");
            user1.setLastName("Dove");

            Engine engine = new Engine();
            engine.setType("V8 Turbo");
            List<Map<String, Object>> jasperData = new ArrayList<>();
            jasperData.add(Map.of(
                    "ownerFirstName", user1.getFirstName(),
                    "cars", List.of(
                            new CarReportDTO("RED", 1L, "Toyota", "Supra", 2024, user1.getFirstName(), engine.getType()),
                            new CarReportDTO("Blue", 2L, "BMW", "M4", 2021, user1.getFirstName(), engine.getType())
                    )
            ));
            jasperData.add(Map.of(
                    "ownerFirstName", user1.getFirstName(),
                    "cars", List.of(
                            new CarReportDTO("Black", 3L, "Tesla", "Model S", 2023, user1.getFirstName(), engine.getType())
                    )
            ));

            return "✅ Car report generated successfully!";

        } catch (Exception e) {
            e.printStackTrace();
            return "❌ Failed to generate car report: " + e.getMessage();
        }
    }
}
