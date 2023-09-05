# takeout
外卖项目服务器开发

### 第一天

1.搭建一个openresty的nginx服务器，放入html进行测试（完成）

2.理解nginx的反向代理的一些基础
	反向代理作用：隐藏服务器的真是地址
	配置文件简单版本
	/API {
		proxy http://xxxxxxxx
	}
	其中 proxy http://xxxxxxxx 当请求/API的时候就会跳转到代理的网址，这样就可以隐藏你的真是地址

	location / { 
            root  html; 
            index  index.html index.htm; 
            proxy_pass http://linuxidc; //反向代理 群组
	}

	upstream linuxidc { 
      		server 10.0.6.108:7080; 
      		server 10.0.0.85:8980; 
	}
	
    群主，根据规则进行
	（1）权重配置
	upstream linuxidc { 
      		server 10.0.6.108:7080; weight = 5;
      		server 10.0.0.85:8980;  weight = 10;
	}
	问题：切换了网站以后可能登陆的session保存会出现问题
	
    （2）ip_hash（可以通过哈希绑定一个服务器，这样就可以操作session了）1比1的绑定关系
	upstream favresin{ 
      		ip_hash; 
      		server 10.0.0.10:8080; 
      		server 10.0.0.11:8080; 
	}

	(3) fair 根据响音时间来
	upstream favresin{      
      		server 10.0.0.10:8080; 
      		server 10.0.0.11:8080; 
      		fair; 
	}
	（4）为每一个设备设置状态值
	down 表示单前的server临时不參与负载.
	weight 默觉得1.weight越大，负载的权重就越大。
	max_fails ：同意请求失败的次数默觉得1.当超过最大次数时，返回proxy_next_upstream 模块定义的错误.
	fail_timeout : max_fails次失败后。暂停的时间。
	backup： 其他全部的非backup机器down或者忙的时候，请求backup机器。所以这台机器压力会最轻。
	upstream bakend{ #定义负载均衡设备的Ip及设备状态 
    		ip_hash; 
    		server 10.0.0.11:9090 down; 
    		server 10.0.0.11:8080 weight=2; 
    		server 10.0.0.11:6060; 
    		server 10.0.0.11:7070 backup; 
	}
	
3.登陆模块相关，md5，SpringMVC 搭建，mysql数据库的相关操作
	问题1：mysql的配置在哪里。resources->application-dev.yml配置中
	问题2：登陆模块的控制器在哪里 controller.admin下面
	问题3：为什么登陆模块要写在员工的模块下
	问题4：相关的注解
	@RestController
	@RestController是Spring MVC框架中的一个注解，它结合了@Controller和	@ResponseBody两个注解的功能，用于标记一个类或者方法，表示该类或方法用于处理HTTP请求，并将响应的结果直接返回给客户端，而不需要进行视图渲染。
	@RequestMapping
	value：定义处理方法的请求的 URL 地址
	method：定义处理方法的 http method 类型，如 GET、POST 等。
	demo:@RequestMapping("/example") 类似于 localhost/example

	@Slf4j 一个日志的注解
	可以直接使用log的操作
	
	@PostMapping 就是一个post的方式进行请求这个api函数

	问题5：vo，dto的区别 vo是服务器返回给客户端的数据，dto是客户端对服务器发送的数据

	问题6：vo 对象 继承Serializable

### 第二天任务

导入json到yapi https://yapi.pro/group/108293 通过这个把接口导入里面

swagger.io

框架 knife4j

1.导入到manve

	<dependency>
        <groupId>com.github.xiaoymin</groupId>
        <artifactId>knife4j-spring-boot-starter</artifactId>
        <version>${knife4j}</version>
    </dependency>
	
2.加入想入的配置
	
3.设置静态的映射
	
4.demo：WebMvcConfiguration类作为参考

5.做出员工添加的功能，请参考 Control.admin.EmployeeController的方法 save方法，EmployeeServiceImpl的save写执行的逻辑

6.使用pagehelp 进行分页

	<dependency>
        <groupId>com.github.pagehelper</groupId>
        <artifactId>pagehelper-spring-boot-starter</artifactId>
        <version>${pagehelper}</version>
    </dependency>

7.时间格式化

(1)使用注解@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss");

(2)扩展操作