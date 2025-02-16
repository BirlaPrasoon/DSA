package designPatterns.chain_of_responsibility.handlers;

public class RoleCheckHandler extends Handler {


    @Override
    public boolean handle(String username, String password) {
        if("admin_username".equals(username)) {
            System.out.println("Loading admin page");
            return true;
        }

        System.out.println("Loading default page");
        return this.handleNext(username, password);
    }
}
