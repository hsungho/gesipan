package first.common.util;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {
	
	@Scheduled(cron = "* 45 * * * *")
	public void cronTest()
	{
		System.out.println("매일 45분 실행");
	}
}
