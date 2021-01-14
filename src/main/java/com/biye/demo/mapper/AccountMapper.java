package com.biye.demo.mapper;

import com.biye.demo.entity.Account;
import com.biye.demo.entity.Account2;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AccountMapper {
    @Select("select * from account")
    public List<Account> getAccount();
    @Insert("insert into account values(#{id},#{balance})")
    public int insertAccount(Account2 account);
    @Delete("delete from account where id=#{id}")
    public int delAccount(String id);
    @Update("update account set balance=#{balance}+balance where id=#{id}")
    public int updateAccount(Account2 account);
    @Select("select * from account where id=#{id}")
    public List<Account> getAccountById(String id);
    @Select("select balance from account where id=#{id}")
    public int getBalanceById(String id);
    @Update("update account set balance=balance-#{balance} where id=#{id}")
    public int payAccount(Account2 account);

}
