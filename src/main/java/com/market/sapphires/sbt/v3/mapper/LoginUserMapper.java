package com.market.sapphires.sbt.v3.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.market.sapphires.sbt.v3.entity.LoginUser;
import com.market.sapphires.sbt.v3.form.datatables.request.UsersDataTablesRequest;

@Mapper
public interface LoginUserMapper {

    int count();

    LoginUser selectOne(long id);

    LoginUser selectByUsername(String username);

    int count4DataTables(UsersDataTablesRequest request);

}
