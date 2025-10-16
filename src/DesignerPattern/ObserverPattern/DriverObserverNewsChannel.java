package DesignerPattern.ObserverPattern;

public class DriverObserverNewsChannel {
    public static void main(String[] args) {


        //// RAJAT this is one to many relationship
        NewsChannel channel1 = new NewsChannel("AAJ Tak");
        NewsChannel channel2 = new NewsChannel("NDTV");
        NewsChannel channel3 = new NewsChannel("zee");

        NewAgency newAgency = new NewAgency();
        newAgency.setObserver(channel1);
        newAgency.setObserver(channel2);
        newAgency.setObserver(channel3);
        newAgency.setNews("ciENA");
    }
}
