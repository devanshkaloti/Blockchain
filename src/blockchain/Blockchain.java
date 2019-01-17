package blockchain;

import java.util.ArrayList;
import java.util.Arrays;

public class Blockchain {

    // List of blocks
    public ArrayList<Block> blocks = new ArrayList<>();

    // Initializer
    public Blockchain(Block genesisBlock) {
        addBlock(genesisBlock);
    }

    // Add block to blockchain
    public void addBlock(Block block) {
        ArrayList<String[]> data = DKHelpers.readCSV();

        if (!data.get(1)[0].equals("1")) { // Incase first block isn't created
            block.previousHash = "000000000000";
            block.hash = generateHash(block);
        }
        blocks.add(block);
    }

    // Generate Hash
    public String generateHash(Block block) {
        String hash; String prefix = "00"; // Difficulty

        // Find match with correct difficulty
        do {
            block.nonce += 1;
            hash = DKHelpers.applySha256(block.getKey());
        } while (!hash.startsWith(prefix));

        return hash;
    }

    // Read last block appended in database.
    public static Block readBlock() {
        ArrayList<String[]> data = DKHelpers.readCSV();
        Block existingBlock = new Block(2);

        try {
            existingBlock.index = (data.size()-1)+1;
            existingBlock.previousHash = data.get(data.size()-1)[2];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Index out of bounds... Blockchain");
        }
        return existingBlock;

    }


    public static ArrayList<Block> readBlocks() {
        ArrayList<Block> blocks = new ArrayList<>();

        int i = 0;
        for (String[] rows: DKHelpers.readCSV()) {

            if (i == 0) {
                i++;
                continue;
            }
            Block newBlock = new Block(2, rows[5]);

            newBlock.index = Integer.parseInt(rows[0]);
            newBlock.nonce = Integer.parseInt(rows[1]);
            newBlock.hash = rows[2];
            newBlock.previousHash = rows[3];
            newBlock.datetime = 0000001;
            newBlock.previousHash = rows[3];

            blocks.add(newBlock);
        }

        return blocks;

    }


    // Get the next block
    public Block getNextBlock(ArrayList<Transaction> transactions) {
        ArrayList<String[]> data = DKHelpers.readCSV();
        Block block = new Block(2);

        for (Transaction transaction: transactions) {
            block.addTransction(transaction);
        }

        block.index = data.size()-1;
        block.previousHash = data.get(data.size()-1)[2];
        System.out.println(data.get(data.size()-1)[2]);
        block.hash = generateHash(block);
        return block;
    }

    // Not needed for now
//    public Block getPreviousBlock() {
//        return blocks.get(blocks.size()-1);
//    }
}
