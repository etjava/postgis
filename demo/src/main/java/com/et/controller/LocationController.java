package com.et.controller;

import com.et.model.Location;
import com.et.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class LocationController {

    @Autowired
    private LocationRepository locationRepository;

    // 显示所有地点
    @GetMapping(value = {"/","/index"}, produces = MediaType.TEXT_HTML_VALUE)
    public String showAllLocations(Model model) {
        List<Location> locations = locationRepository.findAll();
        model.addAttribute("locations", locations);
        return "index";
    }

    // 根据名称搜索地点
    @GetMapping("/search")
    public String searchByName(@RequestParam String name, Model model) {
        List<Location> locations = locationRepository.findByNameContainingIgnoreCase(name);
        System.out.println("查询结果数量: " + locations.size());
        model.addAttribute("locations", locations);
        model.addAttribute("searchName", name);
        return "index";
    }

    // 查询附近地点
    @GetMapping("/nearby")
    public String findNearby(
            @RequestParam double lng,
            @RequestParam double lat,
            @RequestParam(defaultValue = "1000") double distance,
            Model model) {
        List<Location> locations = locationRepository.findWithinDistance(lng, lat, distance);
        model.addAttribute("locations", locations);
        model.addAttribute("lng", lng);
        model.addAttribute("lat", lat);
        model.addAttribute("distance", distance);
        return "index";
    }
}