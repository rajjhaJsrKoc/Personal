package DesignerPattern.ObserverPattern;

import java.util.ArrayList;
import java.util.List;

public class NewAgency implements Obserable {
    List<Observer> observers = new ArrayList<Observer>();
    private String news;
    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(news);
        }
    }

    @Override
    public void setObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void setNews(String news) {
        this.news = news;
        notifyObservers();
    }
}
