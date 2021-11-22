package cn.bugstack.springframework.test;

import cn.bugstack.springframework.context.support.ClassPathXmlApplicationContext;
import cn.bugstack.springframework.test.bean.UserService;
import org.junit.Test;

/**
 * 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 * 公众号：bugstack虫洞栈
 * Create by 小傅哥(fustack)
 */
public class ApiTest {

    @Test
    public void test_xml() {
        // 1.初始化 BeanFactory
        // 初始化 InitializingBean 接口
        //      在调用bean = initializeBean(beanName, bean, beanDefinition)时,会调用InitializingBean中的afterPropertiesSet方法  反射:initMethod.invoke(bean);
        //      先填充属性,在执行initMethod.invoke(bean)初始化方法,在加入到singletonMap中
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        // 销毁: 虚拟机关闭钩子注册调用销毁方法
        applicationContext.registerShutdownHook();

        // 2. 获取Bean对象调用方法
        UserService userService = applicationContext.getBean("userService", UserService.class);
        String result = userService.queryUserInfo();
        System.out.println("测试结果：" + result);
    }

    @Test
    public void test_hook() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> System.out.println("close！")));
    }

}
