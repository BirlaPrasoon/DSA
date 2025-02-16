package designPatterns.visitor;

import designPatterns.visitor.models.Bank;
import designPatterns.visitor.models.Company;
import designPatterns.visitor.models.Resident;
import designPatterns.visitor.models.Restaurant;

public interface Visitor {

    void visit(Bank bank);
    void visit(Company company);
    void visit(Resident resident);
    void visit(Restaurant restaurant);

}
