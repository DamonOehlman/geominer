import com.google.appengine.api.urlfetch.HTTPResponse;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.JsonNode;

URL gowallaSpots = new URL('http://api.gowalla.com/spots');

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

/*
rootNode.spots.each { spot ->
    response.outputStream << spot.lat
}
*/
    
response.outputStream << rootNode