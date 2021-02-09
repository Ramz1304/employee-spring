package employee.service;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import employee.spring.SpringConfig;


@Configuration
@ComponentScan(//
        basePackages = {"employee"}, //
        excludeFilters = @Filter(type = FilterType.ASSIGNABLE_TYPE, value = {SpringConfig.class})//
)
@PropertySources({//
        @PropertySource(value = "classpath:./employee/test.properties", ignoreResourceNotFound = true) //
})

public class QaConfig {

}
