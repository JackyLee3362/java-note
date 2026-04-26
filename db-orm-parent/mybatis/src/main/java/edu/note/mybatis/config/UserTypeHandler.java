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
 * @date 2025-09-28 19:42
 */
public class UserTypeHandler extends BaseTypeHandler<User> {

  @Override
  public void setNonNullParameter(PreparedStatement ps, int i, User parameter, JdbcType jdbcType) throws SQLException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'setNonNullParameter'");
  }

  @Override
  public User getNullableResult(ResultSet rs, String columnName) throws SQLException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getNullableResult'");
  }

  @Override
  public User getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getNullableResult'");
  }

  @Override
  public User getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getNullableResult'");
  }

}