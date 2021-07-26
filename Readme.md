1.先启动注册中eurekaserver  
2.再启动服务提供者eurekaclient 即provider  
3.启动网关zuul:zuul_gateway  
zuul自带了负载均衡,测试方式是先启动注册中心,再启动一个8010端口的provider
,启动后给provider端口改为8011,并创建一个新的启动类`ProviderApplication2`,再启动该启动类,
然后再启动zull,然后访问`http://localhost:8030/p/student/index`,刷新通过获取端口号的不同来测试负载均衡  

例子:访问provider中的接口时,同一路径  
无网关时:`http://localhost:8010/student/findAll`  
有网关时:`http://localhost:8030/p/student/findAll`  


报错:`The bean 'counterFactory', defined in class path resource [zuul***.class]`  
该报错的原因是springboot的版本高,换成2.0.7版本解决


**测试Ribbon**  
1.先启动eureka
2.分别用两个端口启动两个provider的实例
3.启动ribbon,通过访问ribbon的接口`http://localhost:8040/ribbon/index`,测试负载均衡  
  
**Feign**  
Feign比Ribbon更简单,Feign整合了Ribbon 和 Hystrix，具有可插拔、基于注解、负载均衡、服务熔断等⼀系列便捷功能,
在开发中可以用Feign替代Ribbon  
服务熔断:当项目中有多个微服务时,若一个微服务中断后,通过服务降级不影响大的业务,
从而保证业务还能进行下去  
通过创建 FeignProviderClient 接⼝的实现类 FeignError，定义容错处理逻辑，
通过 @Component 注解将 FeignError 实例注⼊ IoC 容器中。

**Hystrix**  
设计原则:
1、服务隔离机制:防止某个服务提供者出现问题,而影响到整个系统的运行;  
2、服务降级机制:服务出现问题时,向消费者返回一个fallback的降级处理;  
3、熔断机制:当服务消费者发送的请求达到一定的数值时,会迅速的熔断,并修复服务;  
4、提供实时的监控和报警功能 
5、提供实时的配置修改功能  

Hystrix 数据监控需要结合 Spring Boot Actuator 来使⽤，Actuator 提供了对服务的健康健康、数据统
计，可以通过 hystrix.stream 节点获取监控的请求数据，提供了可视化的监控界⾯。  
监控地址:`http://localhost:8060/actuator/hystrix.stream` 可以监控到请求数据   
可视化的监控界⾯:`http://localhost:8060/hystrix` 输⼊要监控的地址节点即可看到该节点的可视化数据监控  

**Config**  

管理各个微服务的配置中心,通过服务端可以为多个客户端提供配置服务。Spring Cloud Config 可以将配置⽂件存储在本地，
也可以将配置⽂件存储在远程 Git 仓库，创建 Config Server，通过它管理所有的配置⽂件。  

config不会在eureka中注册

**算法:**  
最小覆盖子串:`http://localhost:8020/test01/checkStr/asdfertgdafasda/atd`  

**Zipkin服务跟踪**  spring cloud zipkin  
zipkin不会在注册中心注册
方便跟踪服务请求经过的微服务的模块,能够方便分析系统的性能或者方便解决问题  
Zipkin包括:Zipkin server(收集数据)、Zipkin client(展示数据)