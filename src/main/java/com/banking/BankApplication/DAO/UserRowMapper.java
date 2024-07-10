package com.banking.BankApplication.DAO;

import com.banking.BankApplication.model.request.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setFirstName(rs.getString("first_Name"));
        user.setLastName(rs.getString("last_name"));
//        user.setAge(rs.getInt("age"));
//        user.setAddress(rs.getString("address"));
//        user.setPhoneNumber(rs.getLong("phone_Number"));
        return user;
    }
}
