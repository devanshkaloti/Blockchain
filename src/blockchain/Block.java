package blockchain;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Date;

public class Block {

    public int index = 0;
    public String hash;
    public String previousHash = "";
    public int nonce;
    public long datetime;

    private ArrayList<Transaction> transactions = new ArrayList<>();

    public Block(int nonce) {
        this.nonce = nonce;
        this.datetime = new Date().getTime();
    }

    public String getKey() {
        Gson gson = new Gson();
        String transactionsJSON = gson.toJson(transactions);
        return String.valueOf(index) + previousHash + String.valueOf(nonce) + datetime + transactionsJSON; /// ADD JSON OF ARRAY!!
    }

    public String getTransactions() {
        Gson gson = new Gson();
        return gson.toJson(transactions);
    }

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

    public void addTransction(Transaction transaction) {
        transactions.add(transaction);
    }



}
