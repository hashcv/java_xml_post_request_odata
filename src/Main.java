import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws Exception {

        String url = "http://spirit.mriya.ua:2011/trainingbase/odata/standard.odata/Document_ОтпускаОрганизаций";

       // String parameters = "";
        String parameters = (new String (Files.readAllBytes(Paths.get("example.xml")), StandardCharsets.UTF_8));
        //System.out.println(parameters);
        //byte[] parameters = Files.readAllBytes(Paths.get("example.xml"));

        System.out.println("Please, wait...");
        sendPost(url, parameters);
    }


    private static void sendPost(String url, String parameters) throws Exception {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        HttpURLConnection conGet = (HttpURLConnection) obj.openConnection();

        String username = "odata";
        String password = "123457";

        byte[] message = (username+":"+password).getBytes("UTF-8");
        String encoded = javax.xml.bind.DatatypeConverter.printBase64Binary(message);
        //String encoded = Base64.getEncoder().encodeToString((username+":"+password).getBytes(StandardCharsets.UTF_8));

        con.setRequestMethod("POST");
        con.setRequestProperty("Authorization", "Basic " + encoded);
        con.setRequestProperty("Accept-Language", "ru-RU,ru;q=0.8,en-US;q=0.6,en;q=0.4");
        con.setRequestProperty("Content-Type", "application/atom+xml;type=feed;charset=utf-8");


        //conGet.setRequestMethod("GET");
        //conGet.setRequestProperty("Authorization", "Basic "+encoded);




//        int responseCode = conGet.getResponseCode();
//        System.out.println("\nSending 'GET' request to URL : " + url);
//        System.out.println("Response Code : " + responseCode);
//
//        BufferedReader in = new BufferedReader(
//                new InputStreamReader(conGet.getInputStream()));
//        String inputLine;
//        StringBuffer response = new StringBuffer();
//
//        while ((inputLine = in.readLine()) != null) {
//            response.append(inputLine);
//        }
//        in.close();
//
//        //print result
//        System.out.println(response.toString());



        con.setDoOutput(true);
        //DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        //DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream(), "UTF-8");
        //wr.writeBytes(parameters);
        wr.write(parameters);
        wr.flush();
        wr.close();

        System.out.println("\n\nURL : " + url);
        System.out.println("Request Method: " + con.getRequestMethod());
        System.out.println("Response Code: " + con.getResponseCode());
        System.out.println("Response Message: " + con.getResponseMessage());
        System.out.println(con.getErrorStream());

//        BufferedReader in = new BufferedReader(new InputStreamReader(con.getErrorStream()));
//        String inputLine;
//        StringBuffer response = new StringBuffer();
////
//        while ((inputLine = in.readLine()) != null) {
//            response.append(inputLine);
//        }
////
//        in.close();
//
//        System.out.println("Response: " + response.toString());
//        File file = new File ("rsp.txt");
//        PrintWriter out = new PrintWriter("rsp.txt");
//        out.println(response.toString());
//        System.out.println("Response copied to rsp.txt.");
    }


//    public static void main(String[] args) {
//        System.out.println("Hello World!");
//    }
}
