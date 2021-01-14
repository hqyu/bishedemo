package com.biye.demo.mapper;

import com.biye.demo.entity.ByshejiAdmin2;
import com.biye.demo.entity.ByshejiUser;
import com.biye.demo.entity.User2;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ByshejiUserMapper {


    //查询用户列表
    @Select("select * from USER")
    public List<ByshejiUser> getUserList();

    //查询指定id用户对象
    @Select("select * from USER where id=#{id}")
    public  ByshejiUser getUserById(String id) ;
    @Select("select id from user where openid=#{openid}")
    public String getUserIdByOpenid(String openid);
    @Select("select carid from user where id=#{id}")
    public String getCaridById(String id);
    //删除指定id用户对象
    @Delete("delete from USER where id=#{id}")
    public int delUserById(String id);
    //修改用户信息
    @Update("update USER set openid=#{openid},name=#{name},carid=#{carid},password=#{password} where id=#{id}")
    public int updateUser(ByshejiUser user);
    //添加用户
    @Insert("insert into USER values(#{id},#{openid},#{carid},#{password},#{name},#{createtime})")
    public int addUser(ByshejiUser user);
    @Select("select openid from USER where carid=#{carid}")
    public String getOpenIDByCarId(String carid) ;
    @Select("select password from user where id=#{id}")
    public String isUser(User2 user);
}
