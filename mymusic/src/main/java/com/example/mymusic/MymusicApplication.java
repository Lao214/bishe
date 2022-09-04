package com.example.mymusic;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**@SpringBootApplication: 标注这个类是一个springboot的应用  作用：启动类下的所有资源被导入 **/
@SpringBootApplication

public class MymusicApplication {

	public static void main(String[] args) {
		/**将springboot应用启动  通过反射加载 MymusicApplication.class对象 就可以直接启动**/
		SpringApplication.run(MymusicApplication.class, args);
	}

}

/** 解析1：
 *  注解
 *  @springBootConfiguration   核心注解： springboot的配置
 *     **由 @Configuration  （spring配置类） 配置起来的
 *     ********@Component    说明这也是一个spring组件
 *
 *  @EnableAutoConfiguration      核心注解：自动配置
 *     **@AutoConfigurationPackage   自动配置包   里面有@Import({Registrar.class})      包注册
 *     ******** 这里面导入了一个选择器 @Import({AutoConfigurationImportSelector.class})   自动导入选择
 *               这里面有环境     Environment
 *                 类加载器     ClassLoader
 *               资源加载器     ResourceLoader
 *               获取所有的配置   List<String> configurations = this.getCandidateConfigurations(annotationMetadata, attributes);
 *      核心方法：获取候选的配置     getCandidateConfigurations
 *              META-INF/spring。factories： 自动配置的核心文件
 *                   会把所有的资源加载到配置类中 Properties properties =PropertiesLoaderUtils.loadProperties(resource);
 *
 *
 *  @ComponentScan               包扫描 :扫描当前主启动类同级的包
 *  @ConfigurationPropertiesScan 配置扫描
 * **/