package DesignerPattern.ObserverPattern;

public interface Obserable {
    void notifyObservers();
    void setObserver(Observer observer);
    void removeObserver(Observer observer);
}
