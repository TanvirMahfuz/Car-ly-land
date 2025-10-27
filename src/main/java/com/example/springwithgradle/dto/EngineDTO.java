package com.example.springwithgradle.dto;

import com.example.springwithgradle.entity.Engine;
import lombok.Data;

@Data
public class EngineDTO {

    private Long id;
    private String type; // Petrol, Diesel, Electric, Hybrid
    private int horsepower; // Engine power in HP
    private double displacement; // in liters
    private int cylinders; // number of cylinders
    private double torque; // Torque in Nm
    private String fuelSystem; // Direct Injection, Carburetor
    private double fuelEfficiency; // in km/l or mpg
    private boolean turbocharged; // true if turbocharged

    public EngineDTO(Engine e) {
        if(e != null){
            this.id = e.getId();
            this.type = e.getType();
            this.horsepower = e.getHorsepower();
            this.displacement = e.getDisplacement();
            this.cylinders = e.getCylinders();
            this.torque = e.getTorque();
            this.fuelSystem = e.getFuelSystem();
            this.fuelEfficiency = e.getFuelEfficiency();
            this.turbocharged = e.isTurbocharged();
        }

    }

}
