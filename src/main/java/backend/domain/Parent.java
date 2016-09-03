package backend.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by amwentzel on 2016/08/25.
 */
@javax.persistence.Entity
public class Parent implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long parent_ID;
    private Long student_nr;
    private String first_name;
    private String surname;
    private String cellphone_nr;
    private String telephone_nr;
    private String e_mail;

    private Parent(){}

    public String getE_mail() {
        return e_mail;
    }

    public Long getParent_ID() {
        return parent_ID;
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
        return telephone_nr;
    }

    public Long getStudent_nr() {
        return student_nr;
    }

    public Parent(Builder builder) {
        this.parent_ID = builder.parent_ID;
        this.student_nr = builder.student_nr;
        this.first_name = builder.first_name;
        this.surname = builder.surname;
        this.cellphone_nr = builder.cellphone_nr;
        this.telephone_nr = builder.telephone_nr;
        this.e_mail = builder.e_mail;
    }

    public static class Builder
    {
        private Long parent_ID;
        private Long student_nr;
        private String first_name;
        private String surname;
        private String cellphone_nr;
        private String telephone_nr;
        private String e_mail;

        public Builder parent_ID(Long parent_ID)
        {
            this.parent_ID = parent_ID;
            return this;
        }

        public Builder student_nr(Long student_nr)
        {
            this.student_nr = student_nr;
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

        public Builder telephone_nr(String telephone_nr)
        {
            this.telephone_nr = telephone_nr;
            return this;
        }

        public Builder e_mail(String e_mail)
        {
            this.e_mail = e_mail;
            return this;
        }

        public Builder copy(Parent value){
            this.parent_ID = value.parent_ID;
            this.student_nr = value.student_nr;
            this.first_name = value.first_name;
            this.surname = value.surname;
            this.cellphone_nr = value.cellphone_nr;
            this.telephone_nr = value.telephone_nr;
            this.e_mail = value.e_mail;
            return this;
        }

        public Parent build()
        {
            return new Parent(this);
        }
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Parent parent = (Parent) o;

        return parent_ID == parent.parent_ID;

    }

    @Override
    public int hashCode() {
        return (int) (parent_ID ^ (parent_ID >>> 32));
    }
}
