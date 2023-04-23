```
import java.io.IOException;
import java.net.URI;

class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    String serverString = "";

    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            return serverString;
        } else if (url.getPath().contains("/add-message")) {
            String[] parameters = url.getQuery().split("=");
            if (parameters[0].equals("s")) {
                serverString += parameters[1] + "\n";
                return serverString;
            }
        }
        return "404 Not Found!";
    }
}

class StringServer {
    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}
```

![Image](https://github.com/nathanieleh/wavelet/blob/master/First_Saved_Message%20(2).png))
![Image](https://github.com/nathanieleh/wavelet/blob/master/Second_Saved_Message.png)
