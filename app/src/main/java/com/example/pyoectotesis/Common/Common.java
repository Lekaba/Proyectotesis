package com.example.pyoectotesis.Common;

import com.example.pyoectotesis.Mode.User;
import com.example.pyoectotesis.Mode.Statusmedidor;

public class Common {
    public static User currentuser;
    public static Statusmedidor currentstatus;
    public static String convertCodeToStatus(String status)
    {
        if ("0".equals(status))
            return "Placed";
        else if ("1".equals(status))
            return "On my way";
        else if ("2".equals(status))
            return "Shipping";
        else
            return "Shipped";
    }

}
