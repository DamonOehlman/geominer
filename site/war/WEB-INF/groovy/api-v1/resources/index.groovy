import com.google.appengine.api.urlfetch.HTTPResponse;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.JsonNode;
import geominer.Dumper;
import geominer.v1.*;

URL gowallaSpots = new URL('http://api.gowalla.com/spots');
def callbackFn = params.callback;

if ((! params.lat) || (! params.lng)) {
    response.outputStream << 'error'
    return;
}

HTTPResponse gowallaResp = gowallaSpots.get(
    params: [
        lat: params.lat,
        lng: params.lng,
        radius: params.radius ? params.radius : '50',
        per_page: '100'
    ],
    headers: [
        'Accept': 'application/json',
        'X-Gowalla-API-Key': '527c5583d0304ffa8a02743d901a73c7'
    ]);
    
// parse the response using jackson

ObjectMapper mapper = new ObjectMapper();
JsonNode rootNode = mapper.readValue(gowallaResp.text, JsonNode.class);

// create the resource container object
ResourceType resType = new ResourceType();
resType.typeName = 'dirt';

rootNode.spots.each { spot ->
    // create the new resource
    Resource resource = new Resource();
    
    // initialise the resource details
    resource.name = spot.name.valueAsText;
    resource.lat = spot.lat.doubleValue;
    resource.lng = spot.lng.doubleValue;
    
    // add to the resource types list of resources
    resType.deposits << resource;
}

// dump the response to the specified stream
Dumper.dump(response.outputStream, resType, params.callback, mapper);
    
// response.outputStream << rootNode