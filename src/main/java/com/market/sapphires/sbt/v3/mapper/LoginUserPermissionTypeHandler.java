package com.market.sapphires.sbt.v3.mapper;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.market.sapphires.sbt.v3.entity.LoginUserPermission;

public class LoginUserPermissionTypeHandler extends BaseTypeHandler<LoginUserPermission> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, LoginUserPermission parameter, JdbcType jdbcType)
            throws SQLException {
        ps.setObject(i, parameter.getAuthority());
    }

    @Override
    public LoginUserPermission getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return LoginUserPermission.getByName(rs.getString(columnName));
    }

    @Override
    public LoginUserPermission getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return LoginUserPermission.getByName(rs.getString(columnIndex));
    }

    @Override
    public LoginUserPermission getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return LoginUserPermission.getByName(cs.getString(columnIndex));
    }

}
