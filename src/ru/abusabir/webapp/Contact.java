package ru.abusabir.webapp;

/**
 * Oleg
 * 02.10.2016
 */
public class Contact {
    private final String type;
    private final String value;

    public Contact(String type, String value) {
        this.type = type;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public String getValue() {
        return value;
    }
}
