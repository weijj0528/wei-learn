package com.weiun.serialize.xml;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

/**
 * @author Administrator
 * @createTime 2019/5/19 11:28
 * @description
 */
public class JacksonXmlMapperTest {

    public static void main(String[] args) throws JsonProcessingException {
        XmlMapper xmlMapper = new XmlMapper();

        // 默认List属性是否包装
        xmlMapper.setDefaultUseWrapper(false);

        //忽略pojo中不存在的字段
        xmlMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES,true);


        TestBean testBean = new TestBean();
        testBean.setApid("String");
        testBean.setName("String");
        testBean.setAge("String");
        testBean.setNoList(Arrays.asList("X098w4324"));

        String s = xmlMapper.writeValueAsString(testBean);
        System.out.println(s);

        // 1、把注解放在成员变量上面，会解析出两个apid字段，一个是<Apid></Apid>,另一个是<apid><apid>
        // 这是因为Jackson的处理机制会自动从属性方法上获取成员变量名，
        // 然而在java中，要么以驼峰命名，要么前两个字母都大写，才能用get方法正确地获取属性，
        // 所以使用getApid获取的成员名称就是apid，被jackson解析了出来。又因为成员变量上也加了注解，
        // 所以也会被解析。这就造成了xml文件生成了两个apid标签。正确的做法是把注解写到get方法上面

        // 2、Jackson封装list问题 默认会给List再加一层包装层，请正确指定包装的标签名否则将看到如下
        // <NoList><NoList>X098w4324</NoList></NoList>
    }

    @Setter
    @JacksonXmlRootElement(localName = "Test")
    public static class TestBean {

        @JacksonXmlProperty
        private String Apid;

        private String Name;

        private String Age;

        private List<String> NoList;

        private String AppName;

        public String getApid() {
            return Apid;
        }

        @JacksonXmlProperty(localName = "Name")
        public String getName() {
            return Name;
        }

        @JacksonXmlProperty(localName = "Age")
        public String getAge() {
            return Age;
        }

        @JacksonXmlElementWrapper(localName = "NoList")
        @JacksonXmlProperty(localName = "No")
        public List<String> getNoList() {
            return NoList;
        }

        public String getAppName() {
            return AppName;
        }
    }

}
