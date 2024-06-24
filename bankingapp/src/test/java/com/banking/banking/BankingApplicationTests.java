package com.banking.banking;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;

@SpringBootTest
class BankingApplicationTests {

	@Test
	void contextLoads() {
		 String character="0123456789";
		Random random=new Random();
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<5;i++)
		{

			sb.append(character.charAt(random.nextInt(character.length())));

		}
		System.out.println(sb.toString());
	}

}
