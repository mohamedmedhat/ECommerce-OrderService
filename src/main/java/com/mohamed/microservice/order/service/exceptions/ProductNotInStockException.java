package com.mohamed.microservice.order.service.exceptions;

public class ProductNotInStockException extends RuntimeException{
    public ProductNotInStockException(String msg){
        super(msg);
    }
}
