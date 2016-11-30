package ru.fopf_print.tehnothackhw1.auth;

public interface AuthorizationListener {
    void onComplete(String token);

    void onError(String error);
}