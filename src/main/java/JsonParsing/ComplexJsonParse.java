package JsonParsing;

import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {
    public static void main(String[] args){
        JsonPath js=new JsonPath(Payload.coursePrice());

//        Print no of courses returned by api
        int count=js.getInt("courses.size()");
        System.out.println(count);

//        Print purchase amount
        int totalAmount=js.getInt("dashboard.purchaseAmount");
        System.out.println(totalAmount);

//        Print title of the firstcourse
        String title=js.getString("courses[0].title");
        System.out.println(title);

//        Print all course title and their respective prices
        for (int i=0;i<count;i++){
            String courseTitle=js.get("courses["+i+"].title");
            int price=js.getInt("courses["+i+"].price");
            System.out.println(courseTitle+" "+price);
        }

//        Print no of copies sold by rpa course
        for (int i=0;i<count;i++){
            String courseTitles=js.get("courses["+i+"].title");
            if (courseTitles.equalsIgnoreCase("RPA")){
//                return copies sold
                int copies=js.getInt("courses["+i+"].copies");
                System.out.println(copies);
                break;
            }
        }





    }
}
