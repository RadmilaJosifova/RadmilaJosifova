package code.academy.peopleapi;

import client.PeopleApiClient;
import model.requests.PostNewPersonRequest;
import model.responses.DeleteExistingPersonResponse;
import model.responses.PostNewPersonResponse;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import payloads.PostNewPersonPayload;

import static org.apache.http.HttpStatus.*;
import static utils.ConversionUtils.jsonStringToObject;
import static utils.ConversionUtils.objectToJsonString;

public class DeleteExistingPerson {
    public DeleteExistingPerson() throws Exception {
    }

    PostNewPersonRequest postNewPersonRequest = new PostNewPersonRequest();
    PostNewPersonPayload postNewPersonPayload = new PostNewPersonPayload();
    PeopleApiClient peopleApiClient = new PeopleApiClient();
    HttpResponse response;
    PostNewPersonResponse postNewPersonResponse = new PostNewPersonResponse();
    DeleteExistingPersonResponse deleteExistingPersonResponse = new DeleteExistingPersonResponse();
    String createPersonID;
    String notExistingID ="2587456";




    @BeforeTest
    public void beforeTest() throws Exception {
           HttpResponse postResponse = peopleApiClient.httpPost("https://people-api1.herokuapp.com/api/person", objectToJsonString(postNewPersonPayload.createNewPersonPayload()));

            String postResponseBodyAsString = EntityUtils.toString(postResponse.getEntity());
            PostNewPersonResponse postNewPersonResponse = jsonStringToObject(postResponseBodyAsString,PostNewPersonResponse.class);

            createPersonID= postNewPersonResponse.getPersonData().getId();

            Assert.assertEquals(postResponse.getStatusLine().getStatusCode(), SC_CREATED);
    }

    @Test
    public  void DeleteExistingPerson() throws Exception{
        response = peopleApiClient.httpDelete("https://people-api1.herokuapp.com/api/person/"+createPersonID);
        String body = EntityUtils.toString(response.getEntity());

        deleteExistingPersonResponse = jsonStringToObject(body, DeleteExistingPersonResponse.class);

        Assert.assertEquals(response.getStatusLine().getStatusCode(),SC_OK);
        Assert.assertEquals(deleteExistingPersonResponse.getMessage(),"Person with id="+createPersonID+" has been succesfully deleted");


    }
    @Test
    public void DeleteNotExistingID()throws Exception{
        response = peopleApiClient.httpDelete("https://people-api1.herokuapp.com/api/person/"+notExistingID);
        String body = EntityUtils.toString(response.getEntity());

        deleteExistingPersonResponse = jsonStringToObject(body,DeleteExistingPersonResponse.class);


        Assert.assertEquals(response.getStatusLine().getStatusCode(),SC_NOT_FOUND);
        Assert.assertEquals(deleteExistingPersonResponse.getMessage(),"Person with id="+notExistingID+" not found");

    }

}
