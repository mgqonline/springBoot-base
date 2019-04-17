package com.talkedu.card.service;

import com.talkedu.card.bo.BizCardSign;
import com.talkedu.card.bo.StudentSignCourseVO;

import java.util.List;
import java.util.Map;

public interface CardSignService {

    void saveCardSign(StudentSignCourseVO obj);

    List<StudentSignCourseVO> selectCurrentStudentCourseInfo(Map<String,Object> param);

    void handleStudentSign(List<StudentSignCourseVO> courseVO);
}
