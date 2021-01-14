package com.biye.demo.mapper;

import com.biye.demo.entity.History;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface HistoryMapper {
    @Select("select * from history where id not in(select id from history where id='已删除')")
    public List<History> getHistory();
    @Select("select * from history where id=#{id}")
    public List<History> getHistoryById(String id);
    @Select("select * from history where historyid=#{historyid}")
    public History getHistoryByHistoryId(String historyid);
    @Insert("insert into history values(#{id},#{time},#{carid},#{comeout},#{historyid})")
    public int insertHistory(History history);
    @Delete("delete from history where historyid=#{historyid}")
    public int delHistory(String historyid);
    @Select("select count(*) from history")
    public int count();
    @Update("update history set id=#{id},carid=#{carid},time=#{time},comeout=#{comeout} where historyid=#{historyid}")
    public int editHistory(History history);
}
