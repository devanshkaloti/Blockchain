package blockchain;

import com.google.gson.Gson;
import java.util.*;


public class Block {

    // Variables
    public int index = 0;
    public String hash;
    public String previousHash = "";
    public int nonce;
    public long datetime;

    // Store Transactions
    private ArrayList<Transaction> transactions = new ArrayList<>();

    // Initializer
    public Block(int nonce) {
        this.nonce = nonce;
        this.datetime = new Date().getTime();
    }

    public Block(int nonce, String transactionsList) {
        this.nonce = nonce;
        this.datetime = new Date().getTime();
        String[] nt = transactionsList.replaceAll("\"", "").split("---");
        transactions.add(
                new Transaction(
                        nt[0].split(":")[1],
                        nt[1].split(":")[1],
                        Double.parseDouble(nt[2].replaceAll("}]", "").split(":")[1]
                        )));
    }

    // Get all details
    public String getKey() {
        Gson gson = new Gson();
        String transactionsJSON = gson.toJson(transactions);
        return String.valueOf(index) + previousHash + String.valueOf(nonce) + datetime + transactionsJSON; /// ADD JSON OF ARRAY!!
    }

    // Get only transactions, JSON
    public String getTransactions() {
        Gson gson = new Gson();
        return gson.toJson(transactions);
    }

    // Add transaction
    public void addTransction(Transaction transaction) {
        transactions.add(transaction);
    }


    // Getters
    public int getIndex() {
        return index;
    }
    public String getPreviousHash() {
        return previousHash;
    }
    public String getHash() {
        return hash;
    }
    public int getNonce() {
        return nonce;
    }
    public long getDatetime() {
        return datetime;
    }

}


