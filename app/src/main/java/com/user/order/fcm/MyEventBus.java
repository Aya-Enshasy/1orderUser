package com.user.order.fcm;

public class MyEventBus {
    String type ;
    String status;
    String order_id,destination_address,pickup_address_en,public_order_id,store_name,type_of_receive;

    public MyEventBus(String type, String status, String order_id, String destination_address, String pickup_address_en, String public_order_id, String store_name, String type_of_receive) {
        this.type = type;
        this.status = status;
        this.order_id = order_id;
        this.destination_address = destination_address;
        this.pickup_address_en = pickup_address_en;
        this.public_order_id = public_order_id;
        this.store_name = store_name;
        this.type_of_receive = type_of_receive;
    }

    public String getType_of_receive() {
        return type_of_receive;
    }

    public void setType_of_receive(String type_of_receive) {
        this.type_of_receive = type_of_receive;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public String getPublic_order_id() {
        return public_order_id;
    }

    public void setPublic_order_id(String public_order_id) {
        this.public_order_id = public_order_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String store) {
        this.status = store;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getDestination_address() {
        return destination_address;
    }

    public void setDestination_address(String destination_address) {
        this.destination_address = destination_address;
    }

    public String getPickup_address_en() {
        return pickup_address_en;
    }

    public void setPickup_address_en(String pickup_address_en) {
        this.pickup_address_en = pickup_address_en;
    }
}
