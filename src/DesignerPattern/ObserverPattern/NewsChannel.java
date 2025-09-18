package DesignerPattern.ObserverPattern;

import java.util.Observable;

public class NewsChannel implements Observer {
    String channel;
    public NewsChannel(String channel) {
        this.channel = channel;
    }
    @Override
    public void update(String news) {
        System.out.println("NewsChannel: " + channel + " This is the news: " + news);
    }
}
