package designPatterns.visitor;

import designPatterns.visitor.models.Bank;
import designPatterns.visitor.models.Company;
import designPatterns.visitor.models.Resident;
import designPatterns.visitor.models.Restaurant;

public class Main {
    public static void main(String[] args) {
        InsuranceMessagingVisitor visitor = new InsuranceMessagingVisitor();
        visitor.sendInsuranceMails(
                new Bank("bank_name", "bank_address", "bank_number", 10),
                new Resident("resident_name", "resident_address", "resident_number", "A"),
                new Company("company_name", "company_address", "company_number", 1000),
                new Restaurant("resto_name", "resto_address", "resto_number", true)
        );
    }
}
