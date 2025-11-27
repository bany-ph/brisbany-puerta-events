package bany.events.infrastructure.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI eventsOpenApi(){
        Server devServer = new Server();
        devServer.setUrl("http://localhost:8080");
        devServer.setDescription("development server");

        Contact contact = new Contact();
        contact.setEmail("bpuertaherrrera@gmail.com");
        contact.setName("Brisbany Puerta Herrera");

        Info info = new Info()
                .title("Events API")
                .version("1.0.0")
                .contact(contact)
                .description("API Rest for venues and events management ")
                .license(new License().name("MIT License").url("https://opensource.org/licenses/MIT"));

        return new OpenAPI()
                .info(info)
                .servers(List.of(devServer));
    }
}
