package Practice.Project1.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/apiproxy")
public class ApiForwarding {

    private final RestTemplate restTemplate;

    ApiForwarding(RestTemplate restTemplate){
        this.restTemplate=restTemplate;
    }

    @GetMapping("/add")
    public ResponseEntity<String> getData(){
         String url="http://localhost:8090/calculate/add/10/13";

        ResponseEntity<String> response=restTemplate.getForEntity(url,String.class);

        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

    @GetMapping("/mult")
    public ResponseEntity<String> getMult(){
        String url="http://localhost:8090/calculate/multiply";

        Map<String,Integer> requestBody=new HashMap<>();

        requestBody.put("a",10);
        requestBody.put("b",20);

        ResponseEntity<String> response=restTemplate.postForEntity(url,requestBody,String.class);

        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }
}
