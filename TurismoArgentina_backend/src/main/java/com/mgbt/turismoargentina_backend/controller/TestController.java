package com.mgbt.turismoargentina_backend.controller;

import com.mgbt.turismoargentina_backend.model.entity.Province;
import com.mgbt.turismoargentina_backend.model.service.IProvinceService;
import com.mgbt.turismoargentina_backend.model.service.IPurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/test/")
public class TestController {

    @Autowired
    IProvinceService provinceService;

    @Autowired
    IPurchaseService purchaseService;

    @GetMapping("/")
    public List<Province> getTest() {
        return this.purchaseService.getAll();
    }
}
