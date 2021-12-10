package LibraryApiTesting.main;

public class payload {
    public static String addBook(String aisle, String isbn){
        String details="{\n" +
                "\n" +
                "\"name\":\"Learn Appium Automation with Java\",\n" +
                "\"isbn\":\""+isbn+"\",\n" +
                "\"aisle\":\""+aisle+"\",\n" +
                "\"author\":\"John foe\"\n" +
                "}";
        return details;
    }
}
