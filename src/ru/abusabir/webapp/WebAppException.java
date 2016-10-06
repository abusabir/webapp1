package ru.abusabir.webapp;

import ru.abusabir.webapp.model.Resume;

/**
 * Oleg
 * 06.10.2016
 */
public class WebAppException extends RuntimeException {
    private Resume resume = null;
    private String uuid = null;

    public WebAppException(String message) {
        super(message);
    }

    public WebAppException(String message, Throwable cause) {
        super(message, cause);
    }

    public WebAppException(String message, Resume resume) {
        super(message);
        this.resume = resume;
    }

    public WebAppException(String message, Throwable cause, Resume resume) {
        super(message, cause);
        this.resume = resume;
    }

    public WebAppException(String message, String uuid) {
        super(message);
        this.uuid = uuid;
    }

    public Resume getResume() {
        return resume;
    }

    public String getUuid() {
        return uuid;
    }
}
