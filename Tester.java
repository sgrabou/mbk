package com.bnpparibas.mobilebanking.payment.canal.infrastructure.http.bills.repository;

import com.bnpparibas.mobilebanking.common.dto.bills.BillsToPayRequest;
import com.bnpparibas.mobilebanking.common.dto.bills.BillsToPayResponse;
import com.bnpparibas.mobilebanking.common.enums.APIErrorType;
import com.bnpparibas.mobilebanking.common.exception.MBBadRequestException;
import com.bnpparibas.mobilebanking.common.exception.MBException;
import com.bnpparibas.mobilebanking.common.utils.CommonUtils;
import com.bnpparibas.mobilebanking.common.utils.TimeCalculator;
import com.bnpparibas.mobilebanking.common.utils.URLBuilder;
import com.bnpparibas.mobilebanking.payment.canal.enums.BillsBundle;
import com.bnpparibas.mobilebanking.payment.canal.enums.PaymentCanalBundle;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;

public class ClassBam {
    public static void main(String[] args) {

        BillsToPayResponse bills = new BillsToPayResponse();
        URLBuilder urlBuilder = new URLBuilder("URL");
        String targetUrl = urlBuilder.toString();


        try {
            ResponseEntity<ObjectResponse> response;
            HttpHeaders httpHeaders = getHttpHeaders();
            HttpEntity<Body> entity = new HttpEntity<>(body, httpHeaders);
            TimeCalculator tcalc = new TimeCalculator();

            response = rt.exchange(targetUrl, HttpMethod.POST, entity, BillsToPayResponse.class);

            bills = response.getBody();

        } catch (HttpClientErrorException e) {
            System.out.println(""+ e);
        } catch (Exception e) {
            System.out.println(""+ e);
        }

        System.out.print("hello");
    }

    public static HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add(HttpHeaders.ACCEPT_LANGUAGE, "fr");
        try {
                headers.add("var1", "var1");
        } catch (Exception e) {
            System.out.println("Error while building http error"+ e);
            throw new MBException(PaymentCanalBundle.UNKNOWN_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return headers;
    }
}
