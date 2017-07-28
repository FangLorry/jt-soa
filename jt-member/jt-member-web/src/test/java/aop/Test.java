package aop;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

	public static void main(String[] args) {
		//Eat eat = new JDKDynProxy(new EatImpl()).getProxy();
//		Eat eat = CGLibDynProxy.getInstance().getProxy(EatImpl.class);
//		eat.eatFood();
		
		BeanFactory factory = new ClassPathXmlApplicationContext("config/spring-mvc.xml","classpath*:/config/spring/spring-*.xml");
		Eat eat = new EatImpl();
		eat.eatFood();
	}

}
