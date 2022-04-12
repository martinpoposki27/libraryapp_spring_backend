package mk.ukim.finki.emt.service;


import mk.ukim.finki.emt.model.User;

public interface AuthService {

    User login(String username, String password);
}
