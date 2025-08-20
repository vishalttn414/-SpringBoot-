package Practice.Project1.Services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("prod")
public class ProdDataService implements DataService {
    @Value("${app.message}")
    private String message;

    @Override
    public String getData() {
        return message;
    }
}