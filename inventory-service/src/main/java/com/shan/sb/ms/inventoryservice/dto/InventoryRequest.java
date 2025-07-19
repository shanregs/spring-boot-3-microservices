package com.shan.sb.ms.inventoryservice.dto;

public record InventoryRequest(Long id,   String skuCode,
                                Integer quantity) {
}
