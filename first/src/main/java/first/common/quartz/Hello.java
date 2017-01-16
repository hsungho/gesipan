package first.common.quartz;

import java.util.Calendar;

public class Hello 
{
	public void print()
	{
		Calendar date = Calendar.getInstance();
		String stamp = date.get(Calendar.HOUR_OF_DAY)+":"
				+date.get(Calendar.MINUTE)+":"
				+date.get(Calendar.SECOND)+":"
				+date.get(Calendar.MILLISECOND);
		System.out.println(stamp+" "+ "실행실행");
	}
}

// 스케줄러 비즈니스 컴포넌트 (내가 작성하는것!)