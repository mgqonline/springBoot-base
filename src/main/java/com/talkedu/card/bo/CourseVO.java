package com.talkedu.card.bo;

import java.io.Serializable;

@lombok.Data
public class CourseVO implements Serializable {

    public String className;
    public String studentName;
    public String classRoom;
    public String classTime;
    public String seatNo;
}
