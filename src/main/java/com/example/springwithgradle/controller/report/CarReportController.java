package com.example.springwithgradle.controller.report;

import com.example.springwithgradle.dto.CarDTO;
import com.example.springwithgradle.dto.CarReportDTO;
import com.example.springwithgradle.entity.Car;
import com.example.springwithgradle.entity.Engine;
import com.example.springwithgradle.entity.User;
import com.example.springwithgradle.utils.ReportBuilder;
import jakarta.persistence.metamodel.Type;
import net.sf.jasperreports.engine.JRException;
import org.apache.poi.ss.formula.functions.T;
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
            Engine engine = new Engine();
            engine.setType("V8 Turbo");

            List<CarReportDTO> cars = new ArrayList<>();

            // Generate 50 sample rows
            for (int i = 1; i <= 15; i++) {
                User owner = new User();
                if (i<=5){
                    owner.setFirstName("John");
                    owner.setLastName("Dove");
                }else if (i>5 && i<=10){
                    owner.setFirstName("bran");
                    owner.setLastName("Dove");
                }else{
                    owner.setFirstName("arya");
                    owner.setLastName("Snow");
                }


                String[] brands = {"Toyota", "BMW", "Tesla", "Honda", "Audi"};
                String[] models = {"Supra", "M4", "Model S", "Civic", "A6"};

                cars.add(new CarReportDTO(
                        i % 3 == 0 ? "Black" : (i % 2 == 0 ? "Blue" : "Red"),
                        (long) i,
                        brands[i % brands.length],
                        models[i % models.length],
                        2018 + (i % 7),
                        owner.getFirstName(),
                        engine.getType()
                ));
            }

            List<Map<String, List<CarReportDTO>>> jasperData = new ArrayList<>();
            jasperData.add(Map.of("cars", cars));

            ReportBuilder<Map<String, List<CarReportDTO>>> builder =
                    new ReportBuilder<>("/static/reports/basic_report.jrxml");

            builder.generateReport(jasperData, null, "basic_report", "pdf");

            return "✅ Car report generated successfully with large dataset!";

        } catch (Exception e) {
            e.printStackTrace();
            return "❌ Failed to generate car report: " + e.getMessage();
        }
    }


    @GetMapping("/car-sub-report")
    public String carSubReport() {
        try {
            Engine engine = new Engine();
            engine.setType("V8 Turbo");

            List<CarReportDTO> cars = List.of(
                    new CarReportDTO("Red", 1L, "Toyota", "Supra", 2024, "John Doe", engine.getType()),
                    new CarReportDTO("Blue", 2L, "BMW", "M4", 2021, "John Doe", engine.getType()),
                    new CarReportDTO("Black", 3L, "Tesla", "Model S", 2023, "Jane Dove", engine.getType()),
                    new CarReportDTO("White", 4L, "Honda", "Civic", 2022, "Jane Dove", engine.getType()),
                    new CarReportDTO("Silver", 5L, "Audi", "A6", 2020, "Arya Snow", engine.getType())
            );

            List<Map<String, Object>> jasperData = new ArrayList<>();

            for (CarReportDTO car : cars) {
                Map<String, Object> row = new HashMap<>();
                row.put("owner", car.getOwner());
                row.put("engine", car.getEngine());
                row.put("model", car.getModel());
                row.put("maker", car.getMaker());
                row.put("color", car.getColor());
                row.put("carId", String.valueOf(car.getCarId()));
                jasperData.add(row);
            }

            ReportBuilder<Map<String, Object>> builder =
                    new ReportBuilder<>("/static/reports/group_report.jrxml");

            builder.generateReport(jasperData, null, "group_report", "pdf");
            return "✅ Car report generated successfully!";

        } catch (Exception e) {
            e.printStackTrace();
            return "❌ Failed to generate car report: " + e.getMessage();
        }
    }

}
