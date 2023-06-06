package ad.uda.tprats.workit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.jpa.datatables.repository.DataTablesRepositoryFactoryBean;

@SpringBootApplication
@EnableJpaRepositories(repositoryFactoryBeanClass = DataTablesRepositoryFactoryBean.class, basePackages={"ad.uda.tprats.workitdata.re", "ad.uda.tprats.workitbackend.repositories"})
@ComponentScan(basePackages = { "ad.uda.tprats.workitdata","ad.uda.tprats.workitbackend" })
@EntityScan(basePackages = "ad.uda.tprats.workitdata.entities")
public class WorkItBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(WorkItBackendApplication.class, args);
    }
}
