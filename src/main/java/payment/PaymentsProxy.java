package payment;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * Code for this package is based on Spring Start in Action chapter 11 for learning purposes. Will build into Proxy to
 * handle post requests for payment transactions.
 */
@FeignClient(name = "payments",
            url = "${name.service.url}") //defined in application.properties
public interface PaymentsProxy {

    @PostMapping("/payment")
    Payment createPayment(
            @RequestHeader String requestId,
            @RequestBody Payment payment
    );

}



