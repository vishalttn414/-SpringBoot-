package Practice.Project1.Components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Car {
    private final Engine engine;

    @Autowired
    public Car(Engine engine) {
        this.engine = engine;
    }

    public void move() {
        System.out.println(engine.start() + ", Car is moving");
    }
}
