package com.rajat.projecta.Helper;

public class ServiceProviderBookHelper {
    public String Name;
    public Long Cost,Duration;
    public  String Paymentid;
    public String Status;

    public ServiceProviderBookHelper(String Name, Long Cost, Long Duration, String Paymentid, String Status){
        this.Name= Name;
        this.Cost = Cost;
        this.Duration = Duration;
        this.Paymentid =Paymentid;
        this.Status = Status;
    }
    public ServiceProviderBookHelper(){

    }
}
