package com.dkm.gdpartifact;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

class AfGdpLog implements Serializable
{
    private final String text;
    private final String formattedDate;

    public AfGdpLog(String text)
    {
        this.text = text;
        Date date = new Date();
        String strDateFormat = "MM-dd-yyyy hh:mm:ss a";
        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
        formattedDate = dateFormat.format(date);

//        formattedDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a").format(new Date());
    }

//     Could have used the Lombok Annotation @Data
    @Override
    public String toString()
    {
        return "AfGDPLog{" +
                "text='" + text + '\'' +
                ", formattedDate='" + formattedDate + '\'' +
                '}';
    }
}
