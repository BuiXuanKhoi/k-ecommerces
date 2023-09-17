package com.ecommerce.kgateway.controllers.test;

import com.ecommerce.kgateway.models.dtos.response.ProductResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.grpc.Channel;
import io.grpc.ManagedChannelBuilder;
import org.example.proto.generated.ProductDetail;
import org.example.proto.generated.ProductGRPCGrpc;
import org.example.proto.generated.ProductGetDetailRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static java.nio.charset.StandardCharsets.UTF_8;

@RestController
@RequestMapping("/test")
public class TestController {

    ObjectMapper objectMapper;

    public TestController() {
        this.objectMapper = new ObjectMapper();
    }

    @GetMapping("/grpc")
    public TestResponse<ProductResponse> testSpeedForGRPC(){
        System.out.println("Start establish connection");
        Channel channel = ManagedChannelBuilder.forTarget("localhost:8082")
                .usePlaintext()
                .build();
        ProductGRPCGrpc.ProductGRPCBlockingStub stub = ProductGRPCGrpc.newBlockingStub(channel);
        long startRequest = System.currentTimeMillis();


        ProductGetDetailRequest productGetDetailRequest = ProductGetDetailRequest.newBuilder()
                .setId("1")
                .build();

        ProductDetail productDetail = stub.getProductDetailById(productGetDetailRequest);
        long endRequest = System.currentTimeMillis();
        Long cost =  endRequest - startRequest;
        return new TestResponse<>(
                new ProductResponse(
                        productDetail.getId(),
                        productDetail.getName(),
                        productDetail.getDescription(),
                        productDetail.getOwnerId(),
                        productDetail.getPrice()
                ),
                cost);
    }

    @GetMapping("/http")
    public TestResponse<Detail> testSpeedForHttp() throws IOException, InterruptedException {
        long startRequest = System.currentTimeMillis();
        HttpClient httpClient = HttpClient.newHttpClient();
        TestRequest testRequest = new TestRequest("1");
        String payload = objectMapper.writeValueAsString(testRequest);

        HttpRequest.BodyPublisher bodyPublisher = HttpRequest.BodyPublishers.ofString(payload, UTF_8);
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8083/test"))
                .POST(bodyPublisher)
                .header("Content-Type", "application/json")
                .build();


        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        Detail detail = objectMapper.readValue(response.body(), Detail.class);
        long endRequest = System.currentTimeMillis();
        long timeCost =  endRequest - startRequest;

        return new TestResponse<>(
                detail,
                timeCost
        );

    }


}
