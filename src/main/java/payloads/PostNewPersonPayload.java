package payloads;

import model.requests.PostNewPersonRequest;
import org.json.JSONObject;

import java.awt.*;

public class PostNewPersonPayload {
    public PostNewPersonRequest createNewPersonPayload(){
        return  PostNewPersonRequest.builder()
                .name("milena")
                .surname("Daneva")
                .age(25)
                .isEmployed(true)
                .location("bitola")
                .build();

    }

  //  public JSONObject creteNewPersonPayloadAsString(){
       // JSONObject personObject = new JSONObject();
//
   // }
}
