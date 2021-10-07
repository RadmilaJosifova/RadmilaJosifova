package code.academy.peopleapi;

import client.PeopleApiClient;
import model.requests.PostNewPersonRequest;
import model.responses.GetSinglePersonResponse;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import payloads.PostNewPersonPayload;

import java.net.http.HttpResponse;

import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import static org.apache.http.HttpStatus.SC_OK;
import static utils.ConversionUtils.jsonStringToObject;

public class GetSinglePersonTest {
    public GetSinglePersonTest() throws Exception {
    }
    PostNewPersonRequest postNewPersonRequest = new PostNewPersonRequest();
    PeopleApiClient peopleApiClient = new PeopleApiClient();
    PostNewPersonPayload postNewPersonPayload = new PostNewPersonPayload();
    GetSinglePersonResponse getSinglePersonResponse;
    org.apache.http.HttpResponse response;
    String createdInvalidPersonID= "89987867567565687";


    @Test
    public void GetSinglePersonTest() throws Exception{

        response =  peopleApiClient.httpGet("https://people-api1.herokuapp.com/api/person/615eb34720af58000405ac6c");
        String body = EntityUtils.toString(response.getEntity());
        getSinglePersonResponse = jsonStringToObject(body, GetSinglePersonResponse.class);


        Assert.assertEquals(response.getStatusLine().getStatusCode(), SC_OK);
        Assert.assertEquals(getSinglePersonResponse.getCode(),"P200");
        Assert.assertEquals(getSinglePersonResponse.getMessage(),"Person succesfully fetched");
    }
    @Test
    public void InvalidIdGetSinglePerson()throws Exception{



        response =  peopleApiClient.httpGet("https://people-api1.herokuapp.com/api/person/"+createdInvalidPersonID);
        String body = EntityUtils.toString(response.getEntity());
        getSinglePersonResponse = jsonStringToObject(body, GetSinglePersonResponse.class);


        Assert.assertEquals(response.getStatusLine().getStatusCode(), SC_NOT_FOUND);
        Assert.assertEquals(getSinglePersonResponse.getCode(),"P404");
        Assert.assertEquals(getSinglePersonResponse.getMessage(),"Person with id "+createdInvalidPersonID+" not found");
    }
}
