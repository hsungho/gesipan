package first.common.quartz.spring;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import first.common.quartz.Hello;

public class MyJobBean extends QuartzJobBean
{
	private Hello hello;
	
	protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException
	{
		hello.print();
	}
	
	public void setHello(Hello hello)
	{
		this.hello = hello;
	}
}

//Job과 Job속성 파라미터 정보를 모아놓은 컴포넌트