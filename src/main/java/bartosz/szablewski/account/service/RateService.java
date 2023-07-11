package bartosz.szablewski.account.service;

import bartosz.szablewski.account.model.Tables;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class RateService {

    private String url = "http://api.nbp.pl/api/exchangerates/rates/c/{code}/?format=json";

    private RestTemplate restTemplate = new RestTemplate();

    public Tables getRateByCode(String code) {
        return callNBPRate(code);
    }

    private Tables callNBPRate(String code) {
        return restTemplate.getForObject(url, Tables.class, Map.of("code", code));
    }
}
