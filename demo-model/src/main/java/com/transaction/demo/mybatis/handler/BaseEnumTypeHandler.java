package com.transaction.demo.mybatis.handler;

import com.transaction.util.EnumUtil;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 新枚举类型转换类
 * @author styzf
 * @date 2021/10/27 20:24
 */
public class BaseEnumTypeHandler extends BaseTypeHandler<EnumUtil.BaseEnum> {
    
    private final Class type;
    
    public BaseEnumTypeHandler(Class type) {
        if (type == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        this.type = type;
    }
    
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, EnumUtil.BaseEnum parameter, JdbcType jdbcType) throws SQLException {
        if (jdbcType == null) {
            ps.setString(i, String.valueOf(parameter.value()));
        } else {
            ps.setObject(i, parameter.value(), jdbcType.TYPE_CODE); // see r3589
        }
    }
    
    @Override
    public EnumUtil.BaseEnum getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return EnumUtil.parse(rs.getObject(columnName), type);
    }
    
    @Override
    public EnumUtil.BaseEnum getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return EnumUtil.parse(rs.getObject(columnIndex), type);
    }
    
    @Override
    public EnumUtil.BaseEnum getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return EnumUtil.parse(cs.getObject(columnIndex), type);
    }
}
