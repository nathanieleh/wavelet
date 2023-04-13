import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    ArrayList<String> strArray = new ArrayList<String>();

    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            return String.format(strArray.toString());
        } else if (url.getPath().equals("/add")) {
            String[] parameters = url.getQuery().split("=");
            if (parameters[0].equals("s")) {
                strArray.add(parameters[1]);
                return "Input Accepted!";
            }
        } else if (url.getPath().equals("/search")){
            String[] parameters = url.getQuery().split("=");
            String result = "";
            if (parameters[0].equals("s")) {
                for(String s:strArray){
                    if(s.contains(parameters[1]))
                        result+=s+" and ";
                }
            }
            if(result.length()==0)
                return "No string found";
            return result.substring(0,result.length()-5);
        }
        return "404 Not Found!";
    }
}

class SearchEngine {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}
