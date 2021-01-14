package com.biye.demo.mapper;

import clojure.lang.Compiler;
import com.biye.demo.entity.Car;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface CarMapper {
    @Select("select * from manangecar")
    public List<Car> getCars();
    @Select("select * from manangecar where id=#{id}")
    public List<Car> getCarsById(String id);
    @Delete("delete from manangecar where carid=#{carid}")
    public int deleteCarsByCarid(String carid);
    @Insert("insert into manangecar values(#{id},#{openid},#{carid},#{color},#{brand},#{carsid})")
    public int insertCar(Car car);
    @Update("update manangecar set openid=#{openid},carid=#{carid},color=#{color},brand=#{brand},carsid=#{carsid} where id=#{id}")
    public int updateCars(Car car);
    @Select("select * from manangecar where carid=#{carid}")
    public Car getCarsByCarid(String carid);
    @Select("select count(*) from manangecar")
    public int count();


}
