package com.biye.demo.controller;

import com.biye.demo.entity.ByshejiUser;
import com.biye.demo.entity.Car;
import com.biye.demo.entity.ParkCar;
import com.biye.demo.mapper.ParkCarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class ParkController {
    @Autowired
    ParkCarMapper parkCarMapper;
    @RequestMapping("/parkcar")
    public String getPark(Map<String,Object> map){
        List<ParkCar> list = parkCarMapper.getPark();
        map.put("parks",list);
        return "parkcar";

    }
    @RequestMapping("/addpark")
    public String addpark(ParkCar parkCar){

        parkCarMapper.insertParkcar(parkCar);

        return "parkcar";
    }
    @RequestMapping("/editpark")
    public String editPark(ParkCar parkCar){

        parkCarMapper.updateParkcar(parkCar);

        return "redirect:/parkcar";
    }
    @RequestMapping("/delpark/{id}")
    public String delPark(@PathVariable(value="id") String id){
        parkCarMapper.delParkById(id);
        return "redirect:/parkcar";
    }
    @RequestMapping("/editparkcar/{id}")
    public String edit(@PathVariable(value = "id") String id,Map<String,Object>map){
        List<ParkCar> list = new ArrayList<ParkCar>();
        ParkCar parkcar=parkCarMapper.getParkById(id);
        list.add(parkcar);
        map.put("user",list);
        return "editparkcar";
    }
}
