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
public class Mark implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long mark_ID;
    private String title;
    private Date date;
    private int mark;

    private Mark(){}

    public Mark(Builder builder) {
        this.mark_ID = builder.mark_ID;
        this.title = builder.title;
        this.date = builder.date;
        this.mark = builder.mark;
    }

    public Long getMark_ID() {
        return mark_ID;
    }

    public String getTitle() {
        return title;
    }

    public Date getDate() {
        return date;
    }

    public int getMark() {
        return mark;
    }

    public static class Builder
    {
        private Long mark_ID;
        private String title;
        private Date date;
        private int mark;

        public Builder mark_ID(Long mark_ID)
        {
            this.mark_ID = mark_ID;
            return this;
        }

        public Builder title(String title)
        {
            this.title = title;
            return this;
        }

        public Builder date(Date date)
        {
            this.date = date;
            return this;
        }

        public Builder mark(int mark)
        {
            this.mark = mark;
            return this;
        }

        public Builder copy(Mark value){
            this.mark_ID = value.mark_ID;
            this.title = value.title;
            this.date = value.date;
            this.mark = value.mark;
            return this;
        }

        public Mark build()
        {
            return new Mark(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Mark mark = (Mark) o;

        return mark_ID == mark.mark_ID;

    }

    @Override
    public int hashCode() {
        return (int) (mark_ID ^ (mark_ID >>> 32));
    }
}
