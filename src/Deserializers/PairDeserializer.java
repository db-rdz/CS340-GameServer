package Deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import org.apache.commons.lang3.tuple.Pair;

import java.io.IOException;

/**
 * Created by natha on 3/21/2017.
 */

public class PairDeserializer extends JsonDeserializer<Pair> {

    @Override
    public Pair deserialize(
            JsonParser jsonParser,
            DeserializationContext deserializationContext) throws IOException {
        final Object[] array = jsonParser.readValueAs(Object[].class);
        return Pair.of(array[0], array[1]);
    }
}
