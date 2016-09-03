package backend.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by amwentzel on 2016/08/25.
 */
@javax.persistence.Entity
public class Homework implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long homework_ID;
    private String subject;
    private String description;
    private Date due_date;

    private Homework(){}

    public Long getHomework_ID() {
        return homework_ID;
    }

    public String getDescription() {
        return description;
    }

    public String getSubject() {
        return subject;
    }

    public Date getDue_date() {
        return due_date;
    }

    public Homework(Builder builder) {
        this.homework_ID = builder.homework_ID;
        this.subject = builder.subject;
        this.description = builder.description;
        this.due_date = builder.due_date;
    }

    public static class Builder
    {
        private Long homework_ID;
        private String subject;
        private String description;
        private Date due_date;

        public Builder homework_ID(Long homework_ID)
        {
            this.homework_ID = homework_ID;
            return this;
        }

        public Builder subject(String subject)
        {
            this.subject = subject;
            return this;
        }

        public Builder due_date(Date due_date)
        {
            this.due_date = due_date;
            return this;
        }

        public Builder description(String description)
        {
            this.description = description;
            return this;
        }

        public Builder copy(Homework value){
            this.homework_ID = value.homework_ID;
            this.subject = value.subject;
            this.due_date = value.due_date;
            this.description = value.description;
            return this;
        }

        public Homework build()
        {
            return new Homework(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Homework homework = (Homework) o;

        return homework_ID == homework.homework_ID;

    }

    @Override
    public int hashCode() {
        return (int) (homework_ID ^ (homework_ID >>> 32));
    }
}
