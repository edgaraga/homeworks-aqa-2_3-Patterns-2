package ru.netology.delivery.data;

public class RegistrationByCardInfo {
    private final String login;
    private final String password;
    private final String status;

    public RegistrationByCardInfo(String login, String password, String status) {
        this.login = login;
        this.password = password;
        this.status = status;
    }

    public RegistrationByCardInfo(String login, String password) {
        this.login = login;
        this.password = password;
        status = null;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getStatus() {
        return status;
    }
}
