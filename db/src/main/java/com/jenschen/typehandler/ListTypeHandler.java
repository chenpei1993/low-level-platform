package com.jenschen.typehandler;

import com.jenschen.enumeration.BaseEnum;
import com.jenschen.util.EnumUtil;
import org.apache.ibatis.type.*;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

@MappedJdbcTypes(JdbcType.VARCHAR)
@MappedTypes({List.class})
public class ListTypeHandler extends BaseTypeHandler<List<String>> {

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, List<String> strings, JdbcType jdbcType) throws SQLException {
        StringBuffer sb = new StringBuffer();
        for (String s : strings) {
            sb.append(s).append(",");
        }
        preparedStatement.setString(i, sb.toString().substring(0, sb.toString().length() - 1));
    }

    @Override
    public List<String> getNullableResult(ResultSet resultSet, String s) throws SQLException {
        String[] arr = resultSet.getString(s).split(",");
        return Arrays.asList(arr);
    }

    @Override
    public List<String> getNullableResult(ResultSet resultSet, int i) throws SQLException {
        String[] arr = resultSet.getString(i).split(",");
        return Arrays.asList(arr);
    }

    @Override
    public List<String> getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        String[] arr  = callableStatement.getString(i).split(",");
        return Arrays.asList(arr);
    }


}
