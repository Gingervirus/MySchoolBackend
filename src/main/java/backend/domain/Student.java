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
public class Student implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long student_id;
    private String first_name;
    private String surname;
    private String cellphone_nr;
    private String class_nr;
    private String e_mail;

    private Student(){}

    public String getClass_nr() {
        return class_nr;
    }

    public String getE_mail() {
        return e_mail;
    }

    public Long getStudent_id() {
        return student_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getSurname() {
        return surname;
    }

    public String getCellphone_nr() {
        return cellphone_nr;
    }

    public String getTelephone_nr() {
        return class_nr;
    }

    public Student(Student.Builder builder) {
        this.student_id = builder.student_id;
        this.first_name = builder.first_name;
        this.surname = builder.surname;
        this.cellphone_nr = builder.cellphone_nr;
        this.class_nr = builder.class_nr;
        this.e_mail = builder.e_mail;
    }

    public static class Builder
    {
        private Long student_id;
        private String first_name;
        private String surname;
        private String cellphone_nr;
        private String class_nr;
        private String e_mail;

        public Builder parent_ID(Long student_id)
        {
            this.student_id = student_id;
            return this;
        }

        public Builder first_name(String first_name)
        {
            this.first_name = first_name;
            return this;
        }

        public Builder surname(String surname)
        {
            this.surname = surname;
            return this;
        }

        public Builder cellphone_nr(String cellphone_nr)
        {
            this.cellphone_nr = cellphone_nr;
            return this;
        }

        public Builder class_nr(String class_nr)
        {
            this.class_nr = class_nr;
            return this;
        }

        public Builder e_mail(String e_mail)
        {
            this.e_mail = e_mail;
            return this;
        }

        public Builder copy(Student value){
            this.student_id = value.student_id;
            this.first_name = value.first_name;
            this.surname = value.surname;
            this.cellphone_nr = value.cellphone_nr;
            this.class_nr = value.class_nr;
            this.e_mail = value.e_mail;
            return this;
        }

        public Student build()
        {
            return new Student(this);
        }
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        return student_id == student.student_id;

    }

    @Override
    public int hashCode() {
        return (int) (student_id ^ (student_id >>> 32));
    }
}
