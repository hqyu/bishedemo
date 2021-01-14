package com.biye.demo.mapper;

import com.biye.demo.entity.Operator;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OperatorMapper {
    @Select("select * from operator where id not in(select id from operator where id='已删除')")
    public List<Operator> getOperator();
    @Select("select * from operator where id=#{id}")
    public List<Operator> getOperatorById(String id);
    @Insert("insert into operator values(#{id},#{operatetime},#{operatorHistory},#{operateid})")
    public int insertOperator(Operator operator);
    @Delete("delete from operator where operateid=#{operateid}")
    public int delOperator(String operateid);
    @Select("select count(*) from operator")
    public int count();
}
