package com.pojo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EntityClass {

    public String formatRow(ResultSet rs, boolean firstline) {

        List<String> row = new ArrayList<String>();
        try {
            row.add(rs.getString(1));
            row.add(rs.getString(2));
            row.add(rs.getString(3));
            row.add(rs.getString(4).toString());
            row.add(rs.getDate(5).toString());
            row.add(rs.getTimestamp(6).toString());
            row.add(rs.getString(7));
            row.add(rs.getString(8));
            row.add(rs.getString(9));
            row.add(rs.getString(10));
            row.add(rs.getString(11));
            row.add(rs.getString(12));
            row.add(rs.getString(13));
            row.add(rs.getString(14));

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if (firstline) {
            return String.join("\t", row);
        } else {
            return "\n" + String.join("\t", row);
        }

    }

}
