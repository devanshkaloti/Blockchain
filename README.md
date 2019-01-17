# Blockchain-Prototype
This is a prototype of a blockchain database setup in Java. You may get a better clarity of how Blockchain works in Java. All the blocks part of the one blockchain are stored in the ```database.csv``` file. Note: Error handling has not been implemented yet.


## Classes
### ```Blockchain.java```
This class stores the properties and methods for the blockchain. The initializer will add the genesis block (or whichever first block is passed) to the blockchain by default. The hash function uses ```MessageDigest.getInstance("SHA-256")``` from ```DKHelpers.java```


### ```Block.java```
This class stores the structure for the individual blocks. The properties for each block are: 
```
    public int index = 0;
    public int nonce;
    public String hash;
    public String previousHash = "";
    public long datetime; 

    private ArrayList<Transaction> transactions = new ArrayList<>();
```

There are two initializers for this class. 
- ```public Block(int nonce)``` is used by the console. Simply initializes a new block
- ```public Block(int nonce, String transactionsList)``` is used by the GUI. It takes the transactions from the CSV file.

### ```Transaction```
The transaction class stores the barebones for any transaction to occur. It contain's the properties for the data, which is initialized through the class initializer. 


## How to Use Prototype (From Console)
#### Create the blockchain
``` Blockchain blockchain = new Blockchain(Blockchain.readBlock()); ```

#### Add transaction
``` 
    Transaction transaction = new Transaction("Mary", "John", 10.0);
    ArrayList<Transaction> transactionsLIST = new ArrayList<>(Arrays.asList(transaction));
```

#### Add block
``` 
    Block block = blockchain.getNextBlock(transactionsLIST);
    blockchain.addBlock(block);
    
    System.out.println(new Gson().toJson(blockchain)); // Print in JSON
```

#### Add to database (CSV File)
```
    String[] dt = {String.valueOf(block.index), String.valueOf(block.nonce), block.hash, block.previousHash, String.valueOf(block.datetime), block.getTransactions().replace(",", "---")};
    DKHelpers.writeRow(dt);
```
