package Practice.Project1.Services;

import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.eventbridge.EventBridgeClient;
import software.amazon.awssdk.services.eventbridge.model.PutEventsRequest;
import software.amazon.awssdk.services.eventbridge.model.PutEventsRequestEntry;

@Service
public class EventService {

    private final EventBridgeClient eventBridgeClient;

    public EventService(EventBridgeClient eventBridgeClient) {
        this.eventBridgeClient = eventBridgeClient;
    }

    public void publishOrderPlacedEvent(String orderId) {
        PutEventsRequestEntry entry = PutEventsRequestEntry.builder()
                .eventBusName("default")   // use "default" or custom bus
                .source("myapp.orders")
                .detailType("OrderPlaced")
                .detail("{\"orderId\":\"" + orderId + "\"}")
                .build();

        PutEventsRequest request = PutEventsRequest.builder()
                .entries(entry)
                .build();

        eventBridgeClient.putEvents(request);
    }
}
