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
public class Absent implements Serializable{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long absent_ID;
    private String student_nr;
    private String subject;
    private Date date;

    private Absent(){}

    public Absent(Builder builder) {
        this.absent_ID = builder.absent_ID;
        this.student_nr = builder.student_nr;
        this.subject = builder.subject;
        this.date = builder.date;
    }

    public Long getAbsent_ID() {
        return absent_ID;
    }

    public String getSubject() {
        return subject;
    }

    public Date getDate() {
        return date;
    }

    public String getStudent_nr() {
        return student_nr;
    }

    public static class Builder
    {
        private Long absent_ID;
        private String student_nr;
        private String subject;
        private Date date;

        public Builder absent_ID(Long absent_ID)
        {
            this.absent_ID = absent_ID;
            return this;
        }

        public Builder subject(String subject)
        {
            this.subject = subject;
            return this;
        }

        public Builder student_nr(String student_nr)
        {
            this.student_nr = student_nr;
            return this;
        }

        public Builder date(Date date)
        {
            this.date = date;
            return this;
        }

        public Builder copy(Absent value){
            this.absent_ID = value.absent_ID;
            this.student_nr = value.student_nr;
            this.subject = value.subject;
            this.date = value.date;
            return this;
        }

        public Absent build()
        {
            return new Absent(this);
        }

    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Absent absent = (Absent) o;

        return absent_ID == absent.absent_ID;

    }

    @Override
    public int hashCode() {
        return (int) (absent_ID ^ (absent_ID >>> 32));
    }
}

