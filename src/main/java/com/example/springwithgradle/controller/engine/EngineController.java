package com.example.springwithgradle.controller.engine;

import com.example.springwithgradle.entity.Engine;
import com.example.springwithgradle.dto.EngineDTO;
import com.example.springwithgradle.dto.parameters.EngineSearchParams;
import com.example.springwithgradle.service.engine.EngineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/engine")
public class EngineController {

    @Autowired
    private EngineService engineService;

    @PostMapping("/create-engine")
    public EngineDTO createNewEngine(@RequestBody Engine engine){
        EngineDTO newEngine = engineService.createNewEngine(engine);
        return newEngine;
    }

    @GetMapping("/find/{id}")
    public EngineDTO findEngine(@PathVariable Long id){
        EngineDTO engineDTO = engineService.getOneEngine(id);
        return engineDTO;
    }

    @GetMapping("/find")
    public List<EngineDTO> findEngine(@ModelAttribute  EngineSearchParams params){
        return engineService.getEnginesByParams(params);
    }
}
