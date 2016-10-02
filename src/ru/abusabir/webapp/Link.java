package ru.abusabir.webapp;


/**
 * Oleg
 * 30.09.2016
 */
public class Link {
    private static Link EMPTY = new Link();

    private final String name;
    private final String url;

    public Link(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public Link(Link link) {
        this(link.name, link.url);
    }

    public Link() {
        this("", null);
    }

    public Link empty() {
        return EMPTY;
    }
}
