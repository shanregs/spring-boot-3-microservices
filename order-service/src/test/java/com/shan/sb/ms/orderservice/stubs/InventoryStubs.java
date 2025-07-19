package com.shan.sb.ms.orderservice.stubs;

import com.github.tomakehurst.wiremock.client.WireMock;
import lombok.experimental.UtilityClass;

@UtilityClass
public class InventoryStubs {
    public void stubInventoryCall(String skuCode, Integer quantity) {
        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/api/inventory?skuCode="+skuCode+ "&quantity=" + quantity))
                .willReturn(WireMock.aResponse().withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("true")));

    }
}
