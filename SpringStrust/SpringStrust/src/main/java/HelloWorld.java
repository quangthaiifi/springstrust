import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Pa;
import model.Paofhour;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HelloWorld {
        private String name;
    String paValue0 ;
    public  String day;
    String paValue1;
    String paValue2 ;
    String daya;
    String PaOfDay;
    String hourses;
    String psp;

    public String getPs() {
        return psp;
    }

    public void setPs(String ps) {
        psp = ps;
    }

    public String getHourses() {
        return hourses;
    }

    public void setHourses(String hourses) {
        this.hourses = hourses;
    }

    public String getPaValue0() {
        return paValue0;
    }

    public void setPaValue0(String paValue0) {
        this.paValue0 = paValue0;
    }

    public String getPaValue1() {
        return paValue1;
    }

    public void setPaValue1(String paValue1) {
        this.paValue1 = paValue1;
    }

    public String getPaValue2() {
        return paValue2;
    }

    public void setPaValue2(String paValue2) {
        this.paValue2 = paValue2;
    }

    public String getDaya() {
        return daya;
    }

    public void setDaya(String daya) {
        this.daya = daya;
    }
    public String getName() {
        return name;
    }

    public String getPaOfDay() {
        return PaOfDay;
    }

    public void setPaOfDay(String paOfDay) {
        PaOfDay = paOfDay;
    }

    public String getDay() {
        return day;
    }

    @GetMapping(value="/{day}")
    public void setDay(String day) {
        this.day = day;
        System.out.println(this.day);
    }


    private String server = "http://localhost:8080";
    private RestTemplate rest;
    private HttpHeaders headers;
    private HttpStatus status;
    //dung de chuyen json sang object
    ObjectMapper mapper = new ObjectMapper();
    public HelloWorld() {


            this.rest = new RestTemplate();
            this.headers = new HttpHeaders();
            headers.add("Content-Type", "application/json");
            headers.add("Accept", "*/*");
        }


    public String execute() throws Exception {
        System.out.println("ok");
        if(day==null)
        {
            day="";
        }
        String Pajson0= get("/0"+day);
        String Pajson1= get("/1" +day);
        String Pajson2= get("/2" +day);
        List<Pa> participantJsonList0 = mapper.readValue(Pajson0, new TypeReference<List<Pa>>(){});
        List<Pa> participantJsonList1 = mapper.readValue(Pajson1, new TypeReference<List<Pa>>(){});
        List<Pa> participantJsonList2 = mapper.readValue(Pajson2, new TypeReference<List<Pa>>(){});
        List<String> listDaya = new ArrayList<String>();
        List<Double> paVale0 = new ArrayList<Double>();
        List<Double> paVale1 = new ArrayList<Double>();
        List<Double> paVale2 = new ArrayList<Double>();
      // sẽ sử lý ở đây nhé

        for(int i=0;i<participantJsonList0.size();i++){
            paVale0.add(participantJsonList0.get(i).getPaValue());
            listDaya.add(participantJsonList0.get(i).getDateTime());
        }
        daya = mapper.writeValueAsString(listDaya);
        for(int i=0;i<participantJsonList1.size();i++){
            paVale1.add(participantJsonList1.get(i).getPaValue());
        }
        for(int i=0;i<participantJsonList2.size();i++){
            paVale2.add(participantJsonList2.get(i).getPaValue());
        }
        paValue0 = mapper.writeValueAsString(paVale0);
        paValue1 = mapper.writeValueAsString(paVale1);
        paValue2 = mapper.writeValueAsString(paVale2);



       // System.out.println(participantJsonList0);
        return "success";
        }


    public String get(String url) {
        HttpEntity<String> requestEntity = new HttpEntity<String>("", headers);
        System.out.println(url);
        ResponseEntity<String> responseEntity = rest.exchange(server + url, HttpMethod.GET, requestEntity, String.class);
        this.setStatus(responseEntity.getStatusCode());
        System.out.println(requestEntity.getBody());
        return responseEntity.getBody();
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
