package backend.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by amwentzel on 2016/08/25.
 */
@Entity
public class Subject implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long subject_code;
    private String title;
    private String description;
    private String prescribed_book;

    private Subject(){}

    public Long getSubject_code() {
        return subject_code;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getPrescribed_book() {
        return prescribed_book;
    }

    public Subject(Builder builder) {
        this.subject_code = builder.subject_code;
        this.title = builder.title;
        this.description = builder.description;
        this.prescribed_book = builder.prescribed_book;
    }

    public static class Builder
    {
        private Long subject_code;
        private String title;
        private String description;
        private String prescribed_book;

        public Builder subject_code(Long subject_code)
        {
            this.subject_code = subject_code;
            return this;
        }

        public Builder title(String title)
        {
            this.title = title;
            return this;
        }

        public Builder description(String description)
        {
            this.description = description;
            return this;
        }

        public Builder prescribed_book(String prescribed_book)
        {
            this.prescribed_book = prescribed_book;
            return this;
        }

        public Builder copy(Subject value){
            this.subject_code = value.subject_code;
            this.title = value.title;
            this.description = value.description;
            this.prescribed_book = value.prescribed_book;
            return this;
        }

        public Subject build()
        {
            return new Subject(this);
        }
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Subject subject = (Subject) o;

        return subject_code == subject.subject_code;

    }

    @Override
    public int hashCode() {
        return (int) (subject_code ^ (subject_code >>> 32));
    }
}
