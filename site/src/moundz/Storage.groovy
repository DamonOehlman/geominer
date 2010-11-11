import com.google.appengine.api.datastore.Entity

public class Storage {
    public static void createPerson() {
        Entity entity = new Entity("person");

        // subscript notation, like when accessing a map
        entity['name'] = "Damon Oehlman";
        entity.save();
    }
}
