package org.example.domain;

public class User extends Entity<Long> {

    public String username;

    public String parola;


    {
        setId(0L);
    }
    public User( String username, String parola) {

        this.username = username;
        this.parola = parola;

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }


}
