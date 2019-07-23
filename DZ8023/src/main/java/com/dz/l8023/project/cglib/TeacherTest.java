//package com.dz.l8023.project.cglib;
//
//import net.sf.cglib.proxy.Enhancer;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.junit.runners.JUnit4;
//
//@RunWith(JUnit4.class)
//public class TeacherTest
//{
//  @Test
//  public void test()
//  {
//    Enhancer enhancer;
//    try
//    {
//      enhancer = new Enhancer();
//      enhancer.setSuperclass(Teacher.class);
//      enhancer.setCallback(new TeacherMethodInterceptor());
//      Teacher teacher = (Teacher)enhancer.create();
//      teacher.teacing();
//    } catch (Exception e) {
//      e.printStackTrace();
//    }
//  }
//}