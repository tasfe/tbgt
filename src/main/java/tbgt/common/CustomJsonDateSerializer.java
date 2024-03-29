package tbgt.common;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import tbgt.util.DateUtil;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomJsonDateSerializer extends JsonSerializer<Date> {
    @Override
    public void serialize(Date value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
        String formattedDate = DateUtil.format(value);
        jgen.writeString(formattedDate);
    }

}
