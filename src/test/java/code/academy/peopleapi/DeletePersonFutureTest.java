package code.academy.peopleapi;

import client.PeopleApiClient;
import model.requests.PostNewPersonRequest;
import model.responses.PostNewPersonResponse;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.*;
import payloads.PostNewPersonPayload;

import static utils.ConversionUtils.jsonStringToObject;
import static utils.ConversionUtils.objectToJsonString;

public class DeletePersonFutureTest {

    public DeletePersonFutureTest() throws Exception {
    }

    PeopleApiClient peopleApiClient = new PeopleApiClient();
    HttpResponse response;
    PostNewPersonPayload postNewPersonPayload = new PostNewPersonPayload() ;
    PostNewPersonRequest postNewPersonRequest = new PostNewPersonRequest();
    String createPersonID;

    @BeforeClass
    public void beforeClass() throws Exception {
        HttpResponse postResponse = peopleApiClient.httpPost("https://people-api1.herokuapp.com/api/person", objectToJsonString(postNewPersonPayload.createNewPersonPayload()));

        String postResponseBodyAsString = EntityUtils.toString(postResponse.getEntity());
        PostNewPersonResponse postNewPersonResponse = jsonStringToObject(postResponseBodyAsString,PostNewPersonResponse.class);

        String createPersonID= postNewPersonResponse.getPersonalData().getId();


    }

    @BeforeTest
    public void beforeTest(){

    }

    @Test
    public void deletePersonTest() throws Exception{


        response = peopleApiClient.httpDelete("https://people-api1.herokuapp.com/api/person/"+createPersonID);


        String body = EntityUtils.toString(response.getEntity());
    }

    @AfterTest
    public void afterTest(){

    }
    @AfterClass
    public void afterClass(){

    }

}
