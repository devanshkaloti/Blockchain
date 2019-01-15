package blockchain;

public class Transaction {

    private String from;
    private String to;
    private Double amount;

    public Transaction(String from, String to, Double amount) {
        this.from = from;
        this.to = to;
        this.amount = amount;
    }


}
