package com.talkedu.card.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@Rollback
@Transactional
//@Ignore //全部忽略
@ActiveProfiles("dev")
public class CardServiceTest {

    @Autowired
    private CardSignService cardSignService;

    @Test
    public void testCardSignService(){
        Map<String,Object> param = new HashMap<>();
        param.put("stdId",1433223);
        Instant currTime = Instant.now();
        System.out.println("currTime : " + currTime);
        Instant startTime = currTime.plus(Duration.ofSeconds(-60*30));
        System.out.println("startTime : " + startTime);
        Instant endTime = currTime.plus(Duration.ofSeconds(60*30));
        param.put("startDate",startTime);
        param.put("endDate",endTime);
        cardSignService.selectCurrentStudentCourseInfo(param);
       /* System.out.println("endTime : " + endTime);
        System.out.println("Difference in seconds : " + Duration.between(startTime, endTime).getSeconds());

        Instant instant = Instant.ofEpochMilli(startTime.toEpochMilli());
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime time = LocalDateTime.ofInstant(instant, zone);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(time);*/
    }
}
