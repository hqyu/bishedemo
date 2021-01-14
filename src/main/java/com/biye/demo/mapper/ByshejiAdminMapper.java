package com.biye.demo.mapper;

import com.biye.demo.entity.ByshejiAdmin2;
import com.biye.demo.entity.ByshejiUser;
import org.apache.ibatis.annotations.Mapper;
import com.biye.demo.entity.ByshejiAdmin;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface ByshejiAdminMapper {
    @Select("select password from admin where id=#{id}")
    public String isAdmin(ByshejiAdmin2 byshejiAdmin2);
    @Select("select * from admin")
    public List<ByshejiAdmin> getAdmin();
    @Select("select * from admin where id=#{id}")
    public  ByshejiAdmin getAdminById(String id);
    @Insert("insert into admin(id,password,name)values(#{id},#{password},#{name})")
    public int addAdmin(ByshejiAdmin byshejiAdmin);
    @Delete("delete from admin where id=#{id}")
    public int delAdminById(String id);
    @Update("update admin set password=#{password},name=#{name} where id=#{id}")
    public int updateAdmin(ByshejiAdmin byshejiAdmin);
}
