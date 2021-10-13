package payloads;

import model.requests.PostNewPersonRequest;
import org.json.JSONObject;

import java.awt.*;

public class PostNewPersonPayload {
    public PostNewPersonRequest createNewPersonPayload() {
        return PostNewPersonRequest.builder()
                .name("milena")
                .surname("Daneva")
                .age(25)
                .isEmployed(true)
                .location("bitola")
                .build();

    }

    public JSONObject createNewPersonPayloadAsString() {
        JSONObject personObject = new JSONObject();
        personObject.put("name", "Milena");
        personObject.put("surname", "Daneva");
        personObject.put("age", 25);
        personObject.put("isEmployed", "OvaeString");
        personObject.put("location", "Bitola");
        return personObject;

    }

    public PostNewPersonRequest updateNewPersonPayload() {
        return PostNewPersonRequest.builder()
                .location("Hvar")
                .build();

    }

    public PostNewPersonRequest updateNewEmptyPersonPayload() {
        return PostNewPersonRequest.builder()
                .location("")
                .build();
    }
}