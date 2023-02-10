package work.worker.server.models;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class CustomListSerializer extends StdSerializer<CoverImg> {

    /**custom. */
    public CustomListSerializer() {
         this(null);
     }

     /**asdf.
      *
      * @param t
      */
     public CustomListSerializer(final Class<CoverImg> t) {
         super(t);
     }

     /**
      * asdf.
      * @param item
      * @param generator
      * @param provider
      * @throws IOException
      * @throws JsonProcessingException
      */
     @Override
     public void serialize(
        final CoverImg item,
       final JsonGenerator generator,
       final SerializerProvider provider)
       throws IOException, JsonProcessingException {

         generator.writeObject(item.getCoverID());
     }

}
