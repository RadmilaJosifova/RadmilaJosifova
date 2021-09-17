
import client.PeopleApiClient;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class InitialTest {

    PeopleApiClient peopleApiClient = new PeopleApiClient();
    HttpResponse response;


    @Test
    public void InitialTest() throws Exception{


        //HttpResponse welcomeRequest = peopleApiClient.getWelcomeRequest();
       response=peopleApiClient.putOnePerson();

     String body = EntityUtils.toString(response.getEntity());
      ;

    }

    @Test
    public void putOnePerson() throws Exception{
        response = peopleApiClient.postOnePerson();
        String body1 =EntityUtils.toString(response.getEntity());

        JSONObject bodyAsObject = new JSONObject(body1);

        String messageAsString = bodyAsObject.get("message").toString();

        Assert.assertEquals(messageAsString,"Person succesfully inserted");
    }
}
