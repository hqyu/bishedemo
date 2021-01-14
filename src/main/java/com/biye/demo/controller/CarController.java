package com.biye.demo.controller;

import com.biye.demo.entity.ByshejiUser;
import com.biye.demo.entity.Car;
import com.biye.demo.entity.Operator;
import com.biye.demo.mapper.CarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class CarController {
    @Autowired
    CarMapper carMapper;
    @RequestMapping("/car")
    public String getCars(Map<String,Object> map){
        List<Car> list=carMapper.getCars();
        map.put("cars",list);
        return "car";

    }
    @RequestMapping("/delcar/{carid}")
    public String delCar(@PathVariable(value = "carid") String carid){
        carMapper.deleteCarsByCarid(carid);
        return "redirect:/car";
    }
    @RequestMapping("/editcars")
    public String  editcars(Car car){
        carMapper.updateCars(car);
        return "redirect:/car";
    }
    @RequestMapping("/addcar")
    public String addCars(Car car){
        int i=carMapper.count();
        car.setCarsid(i);

        carMapper.insertCar(car);
        return "car";
    }
    @RequestMapping("carid")
    public String carID(String id,Map<String,Object> map){
        List<Car> list=carMapper.getCarsById(id);
        map.put("cars",list);
        return "car";
    }
    @RequestMapping("/editcar/{carid}")
    public String edit(@PathVariable(value = "carid") String carid,Map<String,Object>map){
        List<Car> list = new ArrayList<Car>();
        Car car=carMapper.getCarsByCarid(carid);
        list.add(car);
        map.put("user",list);
        return "editcar";
    }
}
