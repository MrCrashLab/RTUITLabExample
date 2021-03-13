import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpClient;

@RestController
@RequestMapping("/UI")
public class Interlayer {
    private HttpClient httpClient = HttpClient.newHttpClient();

}
