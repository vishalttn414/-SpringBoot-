package Practice.Project1.CustomAnnotation;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import java.io.IOException;

public class PiiMaskSerializer extends JsonSerializer<Object> implements ContextualSerializer {

    private final MaskType maskType;

    public PiiMaskSerializer() {
        this.maskType = MaskType.GENERIC;
    }

    public PiiMaskSerializer(MaskType maskType) {
        this.maskType = maskType;
    }

    @Override
    public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (value == null) {
            gen.writeNull();
            return;
        }

        String strValue = value.toString();
        String masked = switch (maskType) {
            case EMAIL -> maskEmail(strValue);
            case name -> maskName(strValue);
            case FULL -> "*".repeat(strValue.length());
            default -> maskGeneric(strValue);
        };

        gen.writeString(masked);
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property) {
        if (property != null) {
            PiiMask annotation = property.getAnnotation(PiiMask.class);
            if (annotation != null) {
                return new PiiMaskSerializer(annotation.type());
            }
        }
        return this;
    }

    private String maskGeneric(String value) {
        int length = value.length();
        return "*".repeat(Math.max(0, length - 4)) + value.substring(Math.max(0, length - 4));
    }

    private String maskEmail(String email) {
        int atIndex = email.indexOf("@");
        if (atIndex <= 1) return "*".repeat(email.length());
        return email.charAt(0) + "****" + email.substring(atIndex - 1);
    }

    private String maskName(String phone) {
        if (phone.length() < 4) return "*".repeat(phone.length());
        return phone.substring(0, 2) + "****" + phone.substring(phone.length() - 2);
    }
}
