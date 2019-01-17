import blockchain.Block;
import blockchain.Blockchain;
import blockchain.DKHelpers;
import blockchain.Transaction;
import com.google.gson.Gson;
import com.sun.tools.javac.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;

public class main {
    public static void main(String [] args) {

////         Create new Blockchain
//        Blockchain blockchain = new Blockchain(Blockchain.readBlock());
//
//        // Create Transactions
//        Transaction transaction = new Transaction("Mary", "John", 10.0);
//        ArrayList<Transaction> transactionsLIST = new ArrayList<>(Arrays.asList(transaction));
//
//        // Add first block
//        Block block = blockchain.getNextBlock(transactionsLIST);
//        blockchain.addBlock(block);
//
//        // Print in JSON
//        System.out.println(new Gson().toJson(blockchain));
//
//        String[] dt = {String.valueOf(block.index), String.valueOf(block.nonce), block.hash, block.previousHash, String.valueOf(block.datetime), block.getTransactions().replace(",", "---")};
//
//        DKHelpers.writeRow(dt);
////        for (String[] rows: DKHelpers.readCSV()) {
////            for (String column: rows) {
////                System.out.println(column);
////            }
////            System.out.println();
////        }

    }


}
