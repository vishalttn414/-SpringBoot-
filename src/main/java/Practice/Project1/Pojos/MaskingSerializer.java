//package Practice.Project1.Pojos;
//
//import com.fasterxml.jackson.core.JsonGenerator;
//import com.fasterxml.jackson.databind.JsonSerializer;
//import com.fasterxml.jackson.databind.SerializerProvider;
//
//import java.io.IOException;
//
//public class MaskingSerializer extends JsonSerializer<String> {
//
//    @Override
//    public void serialize(String value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
//            throws IOException {
//        if (value == null) {
//            jsonGenerator.writeNull();
//            return;
//        }
//
//        int length = value.length();
//        String masked = "*".repeat(Math.max(0, length - 4)) + value.substring(length - 4);
//        jsonGenerator.writeString(masked);
//    }
//}
