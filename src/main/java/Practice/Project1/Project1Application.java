package Practice.Project1;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableAsync
@EnableTransactionManagement
public class Project1Application{

    public static void main(String[] args) {
        SpringApplication.run(Project1Application.class, args);
    }

    @Bean
    public PlatformTransactionManager getPlatformTransactionManager(MongoDatabaseFactory
                                                                    mongoDatabaseFactory){
        return new MongoTransactionManager(mongoDatabaseFactory);
    }

    @Bean
    ApplicationRunner applicationRunner(@Value("${my.abc}") String myFirstSecret){
        return args->{
            System.out.println("Secret retrieved Successfully"+myFirstSecret);
        };
    }
}
