package payloads;

import model.requests.PostNewPersonRequest;

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
}
