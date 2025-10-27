package com.example.springwithgradle.dto.parameters;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EngineSearchParams {
    private Long id;

    private String type; // Petrol, Diesel, Electric, Hybrid
    private int horsepower; // Engine power in HP
    private double displacement; // in liters
    private int cylinders; // number of cylinders
    private double torque; // Torque in Nm
    private String fuelSystem; // Direct Injection, Carburetor
    private double fuelEfficiency; // in km/l or mpg
    private boolean turbocharged; // true if turbocharged

    public void resolveEngineParams(EngineSearchParams params) {
        this.id = params.getId() != null ? params.getId() : 0L;
        this.type = params.getType() != null ? params.getType() : "";
        this.horsepower = params.getHorsepower();
        this.displacement = params.getDisplacement();
        this.cylinders = params.getCylinders();
        this.torque = params.getTorque();
        this.fuelSystem = params.getFuelSystem() != null ? params.getFuelSystem() : "";
        this.fuelEfficiency = params.getFuelEfficiency();
        this.turbocharged = params.isTurbocharged();
    }

}
