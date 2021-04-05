package com.rajat.projecta;

public class Artist {
    public String Name;
    public Long Cost,duration,paymentId;
    public String status;

    public Artist(String Name, Long Cost,Long duration,Long paymentId,String status){
        this.Name= Name;
        this.Cost = Cost;
        this.duration = duration;
        this.paymentId = paymentId;
        this.status = status;
    }
    public Artist(){

    }
}
