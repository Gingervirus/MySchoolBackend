package backend.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by amwentzel on 2016/08/25.
 */

@javax.persistence.Entity
public class Users implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long user_id;
    private String password;
    private String role;

    private Users(){}



    public long getUser_id() {
        return user_id;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public Users(Builder build)
    {
        this.user_id = build.user_id;
        this.password = build.password;
        this.role = build.role;
    }

    public static class Builder
    {
        private long user_id;

        private String password;
        private String role;

        public Builder user_id(long value)
        {
            this.user_id = value;
            return this;
        }

        public Builder password(String value)
        {
            this.password = value;
            return this;
        }

        public Builder role(String role)
        {
            this.role = role;
            return this;
        }


        public Builder copy(Users value)
        {
            this.user_id = value.user_id;
            this.password = value.password;
            this.role = value.role;
            return this;
        }

        public Users build(){return new Users(this);}
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Users users = (Users) o;

        return user_id == users.user_id;

    }

    @Override
    public int hashCode() {
        return (int) (user_id ^ (user_id >>> 32));
    }
}
