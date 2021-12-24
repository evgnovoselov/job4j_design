package ru.job4j.ood.ocp.bad2;

public class Notification {
    private ServiceType serviceType;
    private String message;

    public Notification(ServiceType serviceType, String message) {
        this.serviceType = serviceType;
        this.message = message;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
