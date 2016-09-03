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
public class Teacher implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long teacher_id;
    private String name;
    private String lastName;
    private String dob;
    private String gender;
    private String e_mail;
    private String cell;

    private Teacher(){}

    public Long getTeacher_id() {
        return teacher_id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDob() {
        return dob;
    }

    public String getGender() {
        return gender;
    }

    public String getE_mail() {
        return e_mail;
    }

    public String getCell() {
        return cell;
    }

    Teacher(Builder builder)
    {
        this.teacher_id = builder.teacher_id;
        this.name = builder.name;
        this.lastName = builder.lastName;
        this.dob = builder.dob;
        this.gender = builder.gender;
        this.cell = builder.cell;
        this.e_mail = builder.e_mail;
    }

    public  static class Builder
    {
        private Long teacher_id;
        private String name;
        private String lastName;
        private String dob;
        private String gender;
        private String cell;
        private String e_mail;

        public Builder teacher_id(Long teacher_id)
        {
            this.teacher_id = teacher_id;
            return this;
        }

        public Builder name(String name)
        {
            this.name = name;
            return this;
        }

        public Builder lastName(String lastName)
        {
            this.lastName = lastName;
            return this;
        }
        public Builder dob(String dob)
        {
            this.dob = dob;
            return this;
        }
        public Builder gender(String gender)
        {
            this.gender = gender;
            return this;
        }

        public Builder cell(String cell) {
            this.cell = cell;
            return this;
        }

        public Builder e_mail(String e_mail)
        {
            this.e_mail = e_mail;
            return this;
        }

        public Builder copy(Teacher value){
            this.teacher_id = value.teacher_id;
            this.name = value.name;
            this.lastName = value.lastName;
            this.dob = value.dob;
            this.gender = value.gender;
            this.cell = value.cell;
            this.e_mail = value.e_mail;
            return this;
        }

        public Teacher build()
        {
            return new Teacher(this);
        }
    }

}
