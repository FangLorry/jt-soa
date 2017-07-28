package aop;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

public class CGLibDynProxy implements MethodInterceptor {
	private static CGLibDynProxy instance = new CGLibDynProxy();

	private CGLibDynProxy() {
	}

	public static CGLibDynProxy getInstance() {
		return instance;
	}

	@SuppressWarnings("unchecked")
	public <T> T getProxy(Class<T> cls) {
		return (T) Enhancer.create(cls, this);
	}

	@Override
	public Object intercept(Object target, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		before();
		Object result = proxy.invokeSuper(target, args);
		after();
		return result;
	}

	private void before() {
		System.out.println("Before");
	}

	private void after() {
		System.out.println("After");
	}

}
