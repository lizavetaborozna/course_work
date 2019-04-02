package model;

public class User {
    private Integer id;
    private String login;
    private String password;
    private String role;

    public User(Integer id, String login, String password, String role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }


    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {
        private Integer id;
        private String login;
        private String password;
        private String role;

        private Builder() {
        }

        public Builder id(Integer val) {
            id = val;
            return this;
        }

        public Builder login(String val) {
            login = val;
            return this;
        }

        public Builder password(String val) {
            password = val;
            return this;
        }

        public Builder role(String val) {
            role = val;
            return this;
        }

        public User build() {
            return new User(this.id, this.login, this.password, this.role);
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", idrole=" + role +
                '}';
    }

//    enum Role {
//        ADMIN, USER;
//
//        public Role from(String role) {
//            if (role.toLowerCase().equals("admin")) {
//                return ADMIN;
//            }
//            throw new IllegalArgumentException();
//        }
//    }
}
