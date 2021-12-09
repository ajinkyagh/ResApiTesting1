package JsonParsing;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SumValidation {

    //        Verify if sum of all course prices matches with purchase amount
    @Test
    public void sumOfCourses(){
        int actualAmount=0;
        JsonPath js=new JsonPath(Payload.coursePrice());
        int count=js.get("courses.size()");
        for (int i=0;i<count;i++){
            int price=js.get("courses["+i+"].price");
            int copies=js.getInt("courses["+i+"].copies");
            int amount=price*copies;
            System.out.println(amount);
            actualAmount+=amount;

        }

        int expectedAmount=js.getInt("dashboard.purchaseAmount");
        Assert.assertEquals(actualAmount,expectedAmount);
    }
}
