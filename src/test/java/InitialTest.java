
import client.PeopleApiClient;

//import model.request.PersonData;
import model.requests.PostNewPersonRequest;
import model.responses.PostNewPersonResponse;
import org.apache.http.HttpResponse;
import org.apache.http.util.Asserts;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import payloads.PostNewPersonPayload;

import static org.apache.http.HttpStatus.SC_CREATED;
import static utils.ConversionUtils.*;

public class InitialTest {

    PeopleApiClient peopleApiClient = new PeopleApiClient();
    HttpResponse response;
    PostNewPersonPayload postNewPersonPayload = new PostNewPersonPayload() ;
    PostNewPersonRequest postNewPersonRequest = new PostNewPersonRequest();

    public InitialTest() throws Exception {
    }


    @Test
    public void WelcomePeopleApiFirstTest() throws Exception {
        String expectedMessage = "Welcome to People API";

        //HttpResponse welcomeRequest = peopleApiClient.getWelcomeRequest();
         response = peopleApiClient.httpGet("https://people-api1.herokuapp.com");
        String body = EntityUtils.toString(response.getEntity());
        JSONObject bodyAsObject = new JSONObject(body);
        String messageAsString = bodyAsObject.get("message").toString();

        Assert.assertEquals(messageAsString, expectedMessage);
        ;
        //  Assert.assertEquals(5,4);
    }

    //    @Test
    // public void getOnePerson() throws Exception {
    //     response = peopleApiClient.getOnePerson();
    //     String body1 = EntityUtils.toString(response.getEntity());

    //     JSONObject bodyAsObject = new JSONObject(body1);

    //     String messageAsString = bodyAsObject.get("message").toString();

    //      Assert.assertEquals(messageAsString, "Person succesfully fetched");
    //  }

    @Test
    public void getOnePersonTest() throws Exception {
         response = peopleApiClient.httpGet("https://people-api1.herokuapp.com/api/person/61463f92ef846100040ed67e");
        String body = EntityUtils.toString(response.getEntity());

        JSONObject bodyAsObject = new JSONObject(body);

        String messageAsString = bodyAsObject.get("person").toString();
        JSONObject personalData = new JSONObject(messageAsString);
        String name = personalData.getString("name").toString();

        Assert.assertEquals(name, "Milena");


    }

    @Test
    public void httpGetTest() throws Exception {
        String expectedMessage = "List of people successfully fetched";
        response = peopleApiClient.httpGet("https://people-api1.herokuapp.com/api/people");
        String body = EntityUtils.toString(response.getEntity());
        JSONObject bodyAsObject = new JSONObject(body);
        String messageAsString = bodyAsObject.get("message").toString();

        Assert.assertEquals(messageAsString, expectedMessage);

    }

    @Test
    public void httpPostTest() throws Exception {
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

    @Test
    public void httpPostWithoutNameTest() throws Exception {
        String expectedMessage = "Person's name cannot be empty";
       JSONObject payLoadAsObject = new JSONObject();
       payLoadAsObject.put("surname", "Elenova");
       payLoadAsObject.put("age", 25);
       payLoadAsObject.put("isEmployed", true);
        payLoadAsObject.put("location", "Kavadarci");


       // response = peopleApiClient.httpPost("https://people-api1.herokuapp.com/api/person", payLoadAsObject);
        String body = EntityUtils.toString(response.getEntity());
        JSONObject bodyAsObject = new JSONObject(body);
        String messageAsString = bodyAsObject.get("message").toString();

        Assert.assertEquals(messageAsString, expectedMessage);

    }
    @Test
    public void httpPut() throws Exception{
        String expectedMessage = "Person's location succesfully updated !";
        JSONObject payLoadAsObject = new JSONObject();
        payLoadAsObject.put("location","Bitola");

        //response = peopleApiClient.httpPut("https://people-api1.herokuapp.com/api/person/6139dd31a4656113d05c4d42", payLoadAsObject);
        String body = EntityUtils.toString(response.getEntity());
        JSONObject bodyAsObject = new JSONObject(body);
        String messageAsString = bodyAsObject.get("message").toString();

        Assert.assertEquals(messageAsString, expectedMessage);
    }

}