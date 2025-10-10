public class VotingSystem {
    public void processVote(String voterId, String candidate) {
        class VoteValidator {
            public boolean validate() {
                return voterId != null && voterId.startsWith("V") && voterId.length() == 5;
            }
        }

        VoteValidator validator = new VoteValidator();

        if (validator.validate()) {
            System.out.println("Vote accepted for candidate: " + candidate + " by voter ID: " + voterId);
        } else {
            System.out.println("Invalid voter ID: " + voterId + ". Vote rejected.");
        }
    }

    public static void main(String[] args) {
        VotingSystem system = new VotingSystem();
        system.processVote("V1234", "Alice");
        system.processVote("12345", "Bob");
        system.processVote("V12", "Charlie");
    }
}
