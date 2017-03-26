package Serializers;

import java.io.IOException;

import org.apache.commons.lang3.tuple.Pair;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class PairSerializer extends JsonSerializer<Pair>{
	
    @Override
    public void serialize(
            Pair pair,
            JsonGenerator jsonGenerator,
            SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartArray(2);
        jsonGenerator.writeObject(pair.getLeft());
        jsonGenerator.writeObject(pair.getRight());
        jsonGenerator.writeEndArray();
    }

}
