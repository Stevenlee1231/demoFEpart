import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@SpringBootApplication
@ComponentScan({"com.stevenLee"})
@MapperScan("com.stevenLee.cms.mapper")
@EnableDiscoveryClient
public class cmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(cmsApplication.class, args);
    }
}
