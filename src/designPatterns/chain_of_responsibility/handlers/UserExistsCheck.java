package designPatterns.chain_of_responsibility.handlers;

import designPatterns.chain_of_responsibility.Database;

public class UserExistsCheck extends Handler {

    private final Database database;

    public UserExistsCheck(Database database) {
        this.database = database;
    }

    @Override
    public boolean handle(String username, String password) {
        if(!this.database.isValidUser(username)) {
            System.out.println("This username is not registered!");
            System.out.println("Sign Up to our app now!");
            return false;
        }
        return handleNext(username, password);
    }
}
