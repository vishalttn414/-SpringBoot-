package Practice.Project1.Services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("dev")
public class DevDataService implements DataService {

    @Value("${app.message}")
    private String message;
    private static final Logger logger = LoggerFactory.getLogger(DevDataService.class);

    @Override
    public String getData() {
        logger.info("Test endpoint called");
        logger.debug("Debug message for /test");
        logger.error("This is a simulated error log");
        return "Logging test successful!";
    }
}