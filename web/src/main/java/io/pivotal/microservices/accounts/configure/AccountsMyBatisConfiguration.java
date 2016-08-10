package io.pivotal.microservices.accounts.configure;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;

import io.pivotal.microservices.common.CommonParams;

@Configuration
@EnableTransactionManagement
public class AccountsMyBatisConfiguration implements TransactionManagementConfigurer {
	protected static Logger logger = Logger.getLogger(AccountsMyBatisConfiguration.class.getName());

	private Properties duridSettings;

	@Autowired(required = true)
	DruidDataSource dataSource;
	
	@Autowired
	StatFilter sf;

	@Bean(name = "dataSource",initMethod = "init", destroyMethod = "close")
	public DruidDataSource dataSource() {
		if (dataSource == null) {
			logger.info("dataSource() invoked");
			try {
				dataSource = new DruidDataSource();
				dataSource.setFilters((String)duridSettings.get("filters"));
				dataSource.setInitialSize(Integer.parseInt((String)duridSettings.get("initialSize")));
				dataSource.setMaxActive(Integer.parseInt((String)duridSettings.get("maxActive")));
				dataSource.setMinIdle(Integer.parseInt((String)duridSettings.get("minIdle")));
				dataSource.setMaxWait(Long.parseLong((String)duridSettings.get("maxWait")));
				dataSource.setTimeBetweenEvictionRunsMillis(Long.parseLong((String)duridSettings.get("timeBetweenEvictionRunsMillis")));
				dataSource.setMinEvictableIdleTimeMillis(Long.parseLong((String)duridSettings.get("minEvictableIdleTimeMillis")));
				dataSource.setValidationQuery((String)duridSettings.get("validationQuery"));
				dataSource.setTestWhileIdle(Boolean.parseBoolean((String)duridSettings.get("testWhileIdle")));
				dataSource.setTestOnBorrow(Boolean.parseBoolean((String)duridSettings.get("testOnBorrow")));
				dataSource.setTestOnReturn(Boolean.parseBoolean((String)duridSettings.get("testOnReturn")));
				dataSource.setPoolPreparedStatements(Boolean.parseBoolean((String)duridSettings.get("poolPreparedStatements")));
				dataSource.setMaxPoolPreparedStatementPerConnectionSize(Integer.parseInt((String)duridSettings.get("maxPoolPreparedStatementPerConnectionSize")));
				dataSource.setRemoveAbandoned(Boolean.parseBoolean((String)duridSettings.get("removeAbandoned")));
				dataSource.setRemoveAbandonedTimeout(Integer.parseInt((String)duridSettings.get("removeAbandonedTimeout")));
				dataSource.setLogAbandoned(Boolean.parseBoolean((String)duridSettings.get("logAbandoned")));
				dataSource.setUrl((String)duridSettings.get("url"));
				dataSource.setUsername((String)duridSettings.get("username"));
				dataSource.setPassword((String)duridSettings.get("password"));
				dataSource.setDriverClassName((String)duridSettings.get("driverClassName"));
						
				dataSource.init();
			} catch (Exception e) {
				logger.log(Level.WARNING, e.getMessage(), e);
				e.printStackTrace();
				throw new RuntimeException(e);
			}
			logger.info("dataSource() initaled");
		}
		return dataSource;
	}
	
	@Bean(name="statfilter")
	public StatFilter statFilter(){
		sf=new StatFilter();
		sf.setLogSlowSql(Boolean.parseBoolean((String)duridSettings.get("logSlowSql")));
		sf.setMergeSql(Boolean.parseBoolean((String)duridSettings.get("mergeSql")));
		sf.setSlowSqlMillis(Long.parseLong((String)duridSettings.get("slowSqlMillis")));
		logger.info("StatFilter() initaled");
		return sf;
	}

	public AccountsMyBatisConfiguration() {
		duridSettings = new Properties();
		try {
			File file = new File(this.getClass().getClassLoader().getResource("").getPath() + File.separator
					+ CommonParams.druidPropPath);
			InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
			duridSettings.load(inputStream);
		} catch (Exception e) {
			logger.log(Level.WARNING, e.getMessage(), e);
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		logger.info("dataSource configuration initaled");
		logger.info(duridSettings.toString());
	}

	@PostConstruct
	public void checkConfigFileExists() {
		if (duridSettings == null) {
			throw new RuntimeException(
					"Cannot find config (please add config file or check your Mybatis configuration)");
		}
	}

	@Bean(name = "sqlSessionFactory")
	public SqlSessionFactory sqlSessionFactoryBean() {
		logger.info("inital SqlSessionFactory");
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		bean.setTypeAliasesPackage("io.pivotal.microservices.accounts.db.mybatis.model");

		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		try {
			bean.setMapperLocations(resolver.getResources("classpath:" + CommonParams.BASEPACKAGEPATH + File.separator + "*.xml"));
			logger.info("SqlSessionFactory Created");
			return bean.getObject();
		} catch (Exception e) {
			logger.log(Level.WARNING, e.getMessage(), e);
			throw new RuntimeException(e);
		}

	}

	@Bean
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}

	@Bean
	@Override
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		return new DataSourceTransactionManager(dataSource);
	}
//	@Bean
//    public DataSourceTransactionManager transactionManager() {
//        return new DataSourceTransactionManager(dataSource);
//	}	
}