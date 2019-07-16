package com.github.brelok.controller;

import com.github.brelok.car.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    private CarService carService;

    @Autowired
    public DashboardController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public String dashboard(Model model) {
        model.addAttribute("car", carService.countAvailableCar());
        model.addAttribute("count", carService.countAllCars());
        model.addAttribute("avarage", carService.avarageRating());
        model.addAttribute("avarageYear", carService.AvarageYear());
        model.addAttribute("avaragePrice", carService.avaragePrice());
        return "dashboard";
    }
}