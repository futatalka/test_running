package com.example.demo.Controller;

import com.example.demo.Services.MasterService;
import com.example.demo.model.Master;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/masters")
public class MasterController {

    private MasterService masterService;


    @Autowired
    public MasterController(MasterService masterService) {
        this.masterService = masterService;
    }

    @GetMapping("/lista")
    public Iterable<Master> list() {
        return masterService.list();
    }





}
