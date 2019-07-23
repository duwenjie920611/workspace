package com.dz.l8023.project.cglib;

import java.lang.reflect.Method;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class TeacherMethodInterceptor implements MethodInterceptor {
	public Object intercept(Object arg0, Method method, Object[] arg2,
			MethodProxy proxy) throws Throwable {
		System.out.println("i am teacher, please attention!");
		return proxy.invokeSuper(arg0, arg2);
	}
}