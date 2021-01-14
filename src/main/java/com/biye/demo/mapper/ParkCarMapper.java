package com.biye.demo.mapper;

import com.biye.demo.entity.ParkCar;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ParkCarMapper {
    @Select("select * from parkcar")
    public List<ParkCar> getPark();
    @Select("select * from parkcar where id=#{id}")
    public ParkCar getParkById(String id);
    @Insert("insert into parkcar values(#{id}, #{name})")
    public int insertParkcar(ParkCar parkCar);
    @Update("update parkcar set name=#{name} where id=#{id}")
    public int updateParkcar(ParkCar parkCar);
    @Delete("delete from parkcar where id=#{id}")
    public int delParkById(String id);
}
