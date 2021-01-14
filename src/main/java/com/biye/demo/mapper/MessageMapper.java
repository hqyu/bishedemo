package com.biye.demo.mapper;

import com.biye.demo.entity.Message;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MessageMapper {
    @Select("select * from message where id not in(select id from message where id='已删除')")
    public List<Message> getMessage();
    @Select("select * from message where id=#{id}")
    public List<Message> getMessageById(String id);
    @Insert("insert into message values(#{id},#{openid},#{messagetime},#{messagetext},#{messageid},#{state})")
    public int insertMessage(Message message);
    @Delete("delete from message where messageid=#{messageid}")
    public int delMessage(String messageid);
    @Select("select count(*) from message")
    public int count();
}
