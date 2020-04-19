package resources;

import pojo.AddPlace;
import pojo.Location;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuild {

    public AddPlace addPlacePayLoad(String name, String language, String address) {
        AddPlace place = new AddPlace();
        Location l = new Location();
        l.setLat(-38.383494);
        l.setLng(33.427362);
        place.setAccuracy(50);
        place.setName(name);
        place.setPhone_number("(+91) 983 893 3937");
        place.setAddress(address);
        place.setWebsite("http://google.com");
        place.setLanguage(language);
        List<String> myList = new ArrayList<>();
        myList.add("shoe park");
        myList.add("shop");
        place.setTypes(myList);
        place.setLocation(l);
        return place;
    }

    public String deletePlacePayload(String placeId) {
        return "{\n" +
                "    \"place_id\":\""+placeId+"\"\n" +
                "}\n";
    }
}
