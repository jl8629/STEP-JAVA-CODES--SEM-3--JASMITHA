interface Discount {
    double apply(double amount);
}

class Payment {
    void processTransaction(double amount) {
        class Validator {
            boolean isValid(double amt) {
                return amt > 0;
            }
        }

        Validator validator = new Validator();
        if (!validator.isValid(amount)) {
            System.out.println("Invalid payment amount: " + amount);
            return;
        }

        Discount discount = new Discount() {
            public double apply(double amt) {
                return amt * 0.9; // 10% discount
            }
        };

        double finalAmount = discount.apply(amount);
        System.out.println("Original Amount: " + amount);
        System.out.println("Amount after Discount: " + finalAmount);
    }
}

public class LocalAnonymousInnerDemo {
    public static void main(String[] args) {
        Payment payment = new Payment();
        payment.processTransaction(500);
        payment.processTransaction(-50);
    }
}
