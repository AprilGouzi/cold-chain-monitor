package com.chain.cold.jobs.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * 定时任务配置
 *
 *
 */
@Configuration
public class ScheduleConfig {

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(DataSource dataSource) {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setDataSource(dataSource);

        //quartz参数
        Properties prop = new Properties();
        // 实例名称，区分同一系统中多个不同的实例
        prop.put("org.quartz.scheduler.instanceName", "ColdChainScheduler");
        // 自动生成实例ID
        prop.put("org.quartz.scheduler.instanceId", "AUTO");
        //线程池配置
        prop.put("org.quartz.threadPool.class", "org.quartz.simpl.SimpleThreadPool");
        prop.put("org.quartz.threadPool.threadCount", "20");
        //Thread类将这些优先级类型定义为常量MIN_PRIORITY、NORM_PRIORITY和MAX_PRIORITY，值分别为1、5和10。NORM_PRIORITY是一个新线程的默认优先级。
        //1-10
        prop.put("org.quartz.threadPool.threadPriority", "5");
        //JobStore配置 任务存储的顶层接口类           不受应用容器事务管理的数据库存储实现类
        /**
         * org.quartz.spi.JobStore作为任务存储的顶层接口类，
         * 他定义了很多的接口方法，总共可归纳为四类，调度器类、任务类、触发器类和之前未提到的Calendar日期这一类，
         * Calendar主要是配合触发器一起设置一些特殊的触发时间而使用的。在项目开发中，
         * 我们无需调用JobStore实现类中的方法，但是了解还是很有必要的，因为可以让我们在项目应用中选择更加适合的存储类型。
         * 如何框架提供的存储机制不能满足要求，还可以自定义其他的存储方式，比如文件系统存储，如果真这么干，那就需求自己实现JobStore接口，
         * 并且实现大约40个接口方法，可以参考RAMJobStore类来看看框架内部具体做了什么再去实现自己的存储类。
         *
         * JobStoreTX
         * 就是不管我们的Service服务本身业务代码是否执行成功，只要代码中调用了Quartz API的数据库操作，
         * 那任务状态就永久持久化了，就算业务代码抛出运行时异常任务状态也不会回滚到之前的状态。
         */
        prop.put("org.quartz.jobStore.class", "org.quartz.impl.jdbcjobstore.JobStoreTX");
        //集群配置
        prop.put("org.quartz.jobStore.isClustered", "true");
        // 设置此实例检入与群集的其他实例的频率（以毫秒为单位），默认值为 15000
        prop.put("org.quartz.jobStore.clusterCheckinInterval", "15000");
        prop.put("org.quartz.jobStore.maxMisfiresToHandleAtATime", "1");

        //触发器超时触发的阈值，超过该值(有空闲线程能执行任务的实际时间-任务应该被执行的时间)将不会触发任务的执行；单位为毫秒，默认为 60000
        prop.put("org.quartz.jobStore.misfireThreshold", "12000");
        prop.put("org.quartz.jobStore.tablePrefix", "QRTZ_");
        prop.put("org.quartz.jobStore.selectWithLockSQL", "SELECT * FROM {0}LOCKS UPDLOCK WHERE LOCK_NAME = ?");

        factory.setQuartzProperties(prop);

        factory.setSchedulerName("ColdChainScheduler");
        //延时启动
        factory.setStartupDelay(30);
        factory.setApplicationContextSchedulerContextKey("applicationContextKey");
        //可选，QuartzScheduler 启动时更新己存在的Job，这样就不用每次修改targetObject后删除qrtz_job_details表对应记录了
        factory.setOverwriteExistingJobs(true);
        //设置自动启动，默认为true
        factory.setAutoStartup(true);

        return factory;
    }
}
