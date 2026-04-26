package edu.note.mybatis.config;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import edu.note.mybatis.model.User;

/**
 * @author jackylee
 * @date 2025-09-29 10:58
 */
public class GenericTypeHandler<E extends User> extends BaseTypeHandler<E> {

    private Class<E> type;

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setNonNullParameter'");
    }

    @Override
    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getNullableResult'");
    }

    @Override
    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getNullableResult'");
    }

    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getNullableResult'");
    }

}