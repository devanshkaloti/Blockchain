import blockchain.Block;
import blockchain.Blockchain;
import blockchain.Transaction;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;

public class main {
    public static void main(String [] args) {

        // Create new Blockchain
        Block genesisBlock = new Block(2);
        Blockchain blockchain = new Blockchain(genesisBlock);

        // Create Transactions
        Transaction transaction = new Transaction("Mary", "John", 10.0);
        ArrayList<Transaction> transactionsLIST = new ArrayList<>(Arrays.asList(transaction));

        // Add first block
        Block block = blockchain.getNextBlock(transactionsLIST);
        blockchain.addBlock(block);

        // Print in JSON
        System.out.println(new Gson().toJson(blockchain));


    }


}
