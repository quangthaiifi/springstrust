import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Paofhour;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetPaOfHoursAction {
    String hourses;
    String PaOfDay;
    public  String day;

    String psp;
    ObjectMapper mapper = new ObjectMapper();

    private RestTemplate rest;
    private HttpHeaders headers;
    private HttpStatus status;
    private String server = "http://localhost:8080";

    public HttpStatus getStatus() {
        return status;
    }

    public void setHourses(String hourses) {
        this.hourses = hourses;
    }

    public void setPaOfDay(String paOfDay) {
        PaOfDay = paOfDay;
    }

    public void setPsp(String psp) {
        this.psp = psp;
    }

    @GetMapping(value="/${day}")
    public void setDay( @PathVariable  String day) {
        this.day = day;
    }

    public String getHourses() {
        return hourses;
    }

    public String getPaOfDay() {
        return PaOfDay;
    }

    public String getDay() {
        return day;
    }

    public String getPsp() {
        return psp;
    }

    public GetPaOfHoursAction() {
        this.rest = new RestTemplate();
        this.headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Accept", "*/*");
    }

    public  String execute() throws IOException {
        PaOfDay = getdata(day);
        List<Paofhour> paofday =  mapper.readValue(PaOfDay, new TypeReference<List<Paofhour>>(){});
        List<String> hours = new ArrayList<String>() ;
        List<Double> paoOfHours = new ArrayList<Double>();
        List<Double> Ps = new ArrayList<Double>();
       // System.out.println( paofday);
        for(int i = 0; i < paofday.size(); i++)
        {
            hours.add(paofday.get(i).getHours());
            paoOfHours.add(paofday.get(i).getPaValues());
            Ps.add(paofday.get(i).getPsValue());
        }


        hourses =  mapper.writeValueAsString(hours);
        PaOfDay = mapper.writeValueAsString(paoOfHours);
        psp =mapper.writeValueAsString(Ps);

        return "success";

    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getdata(String url) {
        System.out.println("dang od day ");
        HttpEntity<String> requestEntity = new HttpEntity<String>("", headers);
        System.out.println(server + url);
        ResponseEntity<String> responseEntity = rest.exchange(server + url, HttpMethod.GET, requestEntity, String.class);

        this.setStatus(responseEntity.getStatusCode());

        return responseEntity.getBody();
    }

}
