package com.learning.user.services.entity;

import com.learning.user.services.dto.UserDetails;
import java.util.Objects;
import java.util.function.Function;

public class UserQuery {

    public static Function<UserDetails, String> createQueryToFetchUserDetails = (userDetails) -> {
        String query = "";
        query = "SELECT u.user_id, u.username," +
                " u.emailaddress, " +
                " u.password, u.department_Id" +
                " from users u where ";
        if (!(Objects.isNull(userDetails.getUsername().trim())))
            query += " u.username= '" + userDetails.getUsername() + "'" ;
        if (!(Objects.isNull(userDetails.getEmailaddress().trim()))){
            query += " AND u.emailaddress= '" + userDetails.getEmailaddress() + "'" ;
        }
        return query;
    };

}
