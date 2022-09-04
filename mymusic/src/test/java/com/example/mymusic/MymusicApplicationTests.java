package com.example.mymusic;

import com.example.mymusic.service.AdminService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Time;
import java.util.Date;

@SpringBootTest
class MymusicApplicationTests {

	@Autowired
	private AdminService adminService;

	@Test
	void contextLoads() {
     adminService.deleteAdminByAdminNo("admin2022011615413265800000001");
        Date date =new Date();
		Time time = new Time(date.getTime());//会丢失年月日
		System.out.println(time);
	}

}
