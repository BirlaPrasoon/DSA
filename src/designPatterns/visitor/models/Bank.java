package designPatterns.visitor.models;

import designPatterns.visitor.Visitor;

public class Bank extends Client{

    private final int branchesInsured;

    public Bank(String name, String address, String number, int branchesInsured) {
        super(name, address, number);
        this.branchesInsured = branchesInsured;
    }

    public int getBranchesInsured() {
        return branchesInsured;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
