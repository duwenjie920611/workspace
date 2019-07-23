package com.demo.auto;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

public class AutoCreateBean
{
  public static void main(String[] args)
  {
    InputStream in;
    try
    {
      in = ClassLoader.getSystemResourceAsStream("generator/mybatis-generator.xml");
      List warnings = new ArrayList();
      boolean overwrite = true;

      ConfigurationParser cp = new ConfigurationParser(warnings);

      Configuration config = cp.parseConfiguration(in);
      DefaultShellCallback callback = new DefaultShellCallback(overwrite);
      MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
      myBatisGenerator.generate(null);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}