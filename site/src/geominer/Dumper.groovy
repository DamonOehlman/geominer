package geominer

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.MappingJsonFactory;
import org.codehaus.jackson.JsonGenerator;

public class Dumper {
    public static dump(target, obj, callbackFn, mapper = null) {
        // if a mapper is not provider, then create one
        if (! mapper) {
            mapper = new ObjectMapper();
        } // if
        
        // setup
        StringWriter sw = new StringWriter();   // serialize
        MappingJsonFactory jsonFactory = new MappingJsonFactory();
        JsonGenerator jsonGenerator = jsonFactory.createJsonGenerator(sw);

        // if we have a callback function then start writing that out
        if (callbackFn) {
            target << callbackFn + '(';
        } // if

        // write the value to to the string writer using Jackson
        mapper.writeValue(jsonGenerator, obj);
        sw.close();

        // write the buffer to the target stream
        target << sw.buffer;

        // close the callback function if specified
        if (callbackFn) {
            target << ');';
        } // if        
    } // dump
}