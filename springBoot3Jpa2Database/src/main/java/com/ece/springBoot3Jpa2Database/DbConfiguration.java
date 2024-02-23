/**
 * 
 */
package com.ece.springBoot3Jpa2Database;

import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.jdbc.JdbcRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jdbc.core.convert.BasicJdbcConverter;
import org.springframework.data.jdbc.core.convert.DefaultJdbcTypeFactory;
import org.springframework.data.jdbc.core.convert.JdbcConverter;
import org.springframework.data.jdbc.core.convert.JdbcCustomConversions;
import org.springframework.data.jdbc.core.convert.RelationResolver;
import org.springframework.data.jdbc.core.mapping.JdbcMappingContext;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.data.relational.core.dialect.Dialect;
import org.springframework.data.relational.core.dialect.SqlServerDialect;
import org.springframework.data.relational.core.mapping.NamingStrategy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.JdbcTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import com.zaxxer.hikari.HikariDataSource;

/**
 * @author nagendrappae
 *
 */
@Configuration
@EnableJdbcRepositories(basePackages = "com.ece.springBoot3Jpa2Database", transactionManagerRef = "dataSourceTransactionManager", jdbcOperationsRef = "dataSourceJdbcOperations")
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class, JdbcRepositoriesAutoConfiguration.class })
public class DbConfiguration {
//https://dba-presents.com/jvm/java/242-spring-data-jdbc-with-two-datasources
	@Autowired
	private Environment env;

	@Bean
	JdbcCustomConversions customConversions() {
		return new JdbcCustomConversions();
	}

	@Bean
	Dialect jdbcDialect() {
		return SqlServerDialect.INSTANCE;
	}

	@Bean
	JdbcMappingContext jdbcMappingContext(Optional<NamingStrategy> namingStrategy,
			JdbcCustomConversions customConversions) {
		JdbcMappingContext mappingContext = new JdbcMappingContext(
				(NamingStrategy) namingStrategy.orElse(NamingStrategy.INSTANCE));
		mappingContext.setSimpleTypeHolder(customConversions.getSimpleTypeHolder());
		return mappingContext;
	}

	@Bean
	JdbcConverter jdbcConverter(JdbcMappingContext mappingContext,
			@Qualifier("dataSourceJdbcOperations") NamedParameterJdbcOperations jdbcOperationsDataBase1,
			@Lazy RelationResolver relationResolver, JdbcCustomConversions conversions, Dialect dialect) {

		DefaultJdbcTypeFactory jdbcTypeFactory = new DefaultJdbcTypeFactory(
				jdbcOperationsDataBase1.getJdbcOperations());
		return new BasicJdbcConverter(mappingContext, relationResolver, conversions, jdbcTypeFactory,
				dialect.getIdentifierProcessing());
	}

	@Bean(name = "dataSourceTransactionManager")
	PlatformTransactionManager dataSourceTransactionManager(@Qualifier("dataSource") DataSource primarydataSource) {
		return new JdbcTransactionManager(primarydataSource);
	}

	@Bean
	@Primary
	NamedParameterJdbcOperations dataSourceJdbcOperations(@Qualifier("dataSource") DataSource dataSource) {
		return new NamedParameterJdbcTemplate(dataSource);
	}

	@Bean("dataSource")
	@Primary
	@ConfigurationProperties(prefix = "spring.datasource")
	public HikariDataSource primarydataSource() {
		return DataSourceBuilder.create().type(HikariDataSource.class).build();
	}

	@Bean("jdbcTemplate")
	@Primary
	public JdbcTemplate dataSource(DataSource dataSource) {
		return new JdbcTemplate(primarydataSource());
	}

	@Bean(name = "secondaryDb")
	@ConfigurationProperties(prefix = "spring.secondary")
	public DataSource seconddatasource() {
		HikariDataSource ds = new HikariDataSource();
		ds.setDriverClassName(env.getProperty("spring.secondary.datasource.driverClassName"));
		ds.setJdbcUrl(env.getProperty("spring.secondary.datasource.url"));
		ds.setUsername(env.getProperty("spring.secondary.datasource.username"));
		ds.setPassword(env.getProperty("spring.secondary.datasource.password"));
		return new HikariDataSource(ds);
	}

	@Bean(name = "secondaryDbNamedParameterJdbcTemplate")
	public NamedParameterJdbcTemplate secondaryDbNamedParameterJdbcTemplate(
			@Qualifier("secondaryDb") final DataSource dsSecondary) {
		return new NamedParameterJdbcTemplate(dsSecondary);
	}

	@Bean(name = "secondaryDbJdbcTemplate")
	public JdbcTemplate secondaryDbJdbcTemplate(@Qualifier("secondaryDb") final DataSource dsSecondary) {
		return new JdbcTemplate(dsSecondary);
	}

}
