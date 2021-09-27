package code.academy.peopleapi;

import client.PeopleApiClient;
import model.requests.PostNewPersonRequest;
import model.responses.PostNewPersonResponse;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import payloads.PostNewPersonPayload;

import static org.apache.http.HttpStatus.SC_CREATED;
import static utils.ConversionUtils.jsonStringToObject;
import static utils.ConversionUtils.objectToJsonString;

public class PostNewPersonTest {

    public PostNewPersonTest() throws Exception {
    }


    PostNewPersonRequest postNewPersonRequest = new PostNewPersonRequest();
    PostNewPersonPayload postNewPersonPayload = new PostNewPersonPayload();
    HttpResponse response;
    PeopleApiClient peopleApiClient = new PeopleApiClient();



    @Test
    public void PostPersonTest() throws Exception {
        // String expectedMessage = "Person succesfully inserted";
        //  JSONObject payLoadAsObject = new JSONObject();
//        payLoadAsObject.put("name", "Mila");
//        payLoadAsObject.put("surname", "Elenova");
//        payLoadAsObject.put("age", 25);
//        payLoadAsObject.put("isEmployed", true);
//        payLoadAsObject.put("location", "Kavadarci");

        postNewPersonRequest=postNewPersonPayload.createNewPersonPayload();
        //postNewPersonRequest.setName(null);
        String newPersonPayLoadAsString = objectToJsonString(postNewPersonRequest);
        response = peopleApiClient.httpPost("https://people-api1.herokuapp.com/api/person", newPersonPayLoadAsString);
        PostNewPersonResponse postNewPersonResponse;
        String body = EntityUtils.toString(response.getEntity());

        postNewPersonResponse = jsonStringToObject(body,PostNewPersonResponse.class);

        Assert.assertEquals(response.getStatusLine().getStatusCode(),SC_CREATED);

//        Assert.assertEquals(postNewPersonResponse.getCode(),"200");
//        Assert.assertEquals(postNewPersonResponse.getMessage(),"List5 of people successfully fetched");
//        Assert.assertEquals(postNewPersonResponse.getPersonalData().getName(),"Milena");
        //  JSONObject bodyAsObject = new JSONObject(body);
        // String messageAsString = bodyAsObject.get("message").toString();

        // Assert.assertEquals(messageAsString, expectedMessage);
    }

}
