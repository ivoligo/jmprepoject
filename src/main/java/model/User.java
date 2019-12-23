package model;

import java.util.Objects;

public class User {

    private Long id;
    private String login;
    private int age;
    private String password;

    public User() {
    }

    public User(String login,   String password) {
        this.login = login;
        this.password = password;
    }
    public User(String login, int age,  String password) {
        this.login = login;
        this.age = age;
        this.password = password;
    }
    public User(Long id, String login, int age) {
        this.id = id;
        this.login = login;
        this.age = age;
    }

    public User(Long id, String login, int age,  String password) {
        this.id = id;
        this.login = login;
        this.age = age;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge(){
        return age;
    }
    public void setAge(int age){
        this.age = age;
    }

    //влияет на лист, но можно по другому: добавив list<String>
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", age=" + age +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (age != user.age) return false;
        if (!Objects.equals(id, user.id)) return false;
        if (!Objects.equals(login, user.login)) return false;
        return Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + age;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }
}
