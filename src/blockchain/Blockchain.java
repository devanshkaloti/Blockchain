package blockchain;

import java.util.ArrayList;

public class Blockchain {

    public ArrayList<Block> blocks = new ArrayList<>();


    public Blockchain(Block genesisBlock) {
        addBlock(genesisBlock);
    }

    public void addBlock(Block block) {
        if (blocks.isEmpty()) {
            block.previousHash = "000000000000";
            block.hash = generateHash(block);
            blocks.add(block);
        } else {
            // Next block
            blocks.add(block);
        }

    }

    public String generateHash(Block block) {
        String hash;
        String prefix = "00";

        do {
            block.nonce += 1;
            hash = DKHelpers.applySha256(block.getKey());
//            System.out.println(hash + " " + block.nonce) ;
//            System.out.println(prefix);
        } while (!hash.startsWith(prefix));

        return hash;
    }

    public Block getNextBlock(ArrayList<Transaction> transactions) {
        Block block = new Block(2);

        for (Transaction transaction: transactions) {
            block.addTransction(transaction);
        }
        Block previousBlock = getPreviousBlock();
        block.index = blocks.size();
        block.previousHash = previousBlock.hash;
        block.hash = generateHash(block);
        return block;
    }

    public Block getPreviousBlock() {
        return blocks.get(blocks.size()-1);
    }
}
