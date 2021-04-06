package com.rajat.projecta.Helper;

public class ServiceProviderBookHelper {
    public String Name;
    public Long Cost,Duration,Time;
    public  String Paymentid;
    public String Status;


    public ServiceProviderBookHelper(String Name, Long Cost, Long Duration, String Paymentid, String Status,Long Time){
        this.Name= Name;
        this.Cost = Cost;
        this.Duration = Duration;
        this.Paymentid =Paymentid;
        this.Status = Status;
        this.Time = Time;
    }
    public ServiceProviderBookHelper(){

    }
}
