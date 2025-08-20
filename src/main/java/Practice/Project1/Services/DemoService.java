package Practice.Project1.Services;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Scope(value = "session",proxyMode = ScopedProxyMode.TARGET_CLASS)
public class DemoService {
    public DemoService() {
        System.out.println("DemoService created: " + this);
    }

    public String getScopeInfo() {
        return "DemoService instance: " + this;
    }
}
