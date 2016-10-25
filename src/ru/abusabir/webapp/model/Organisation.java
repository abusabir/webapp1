package ru.abusabir.webapp.model;

import java.util.Date;
import java.util.List;

/**
 * Oleg
 * 02.10.2016
 */
public class Organisation {
    private Link link;
    private List<Period> periods;

    public Organisation() {
    }

    public Organisation(Link link, List<Period> periods) {
        this.link = link;
        this.periods = periods;
    }

    public static class Period {
        private Date startDate, endDate;
        private String position, content;

        public Period(Date startDate, Date endDate, String position, String content) {
            this.startDate = startDate;
            this.endDate = endDate;
            this.position = position;
            this.content = content;
        }

        public Period() {
        }
    }

}
