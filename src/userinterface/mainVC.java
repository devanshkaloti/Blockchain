package userinterface;

import blockchain.Block;
import blockchain.Blockchain;
import blockchain.Transaction;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;


public class mainVC extends Application {
    private Stage window; private Blockchain blockchain;
    private ArrayList<Transaction> transactions = new ArrayList<>();
    private TableView table = new TableView();

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;

        // Create new Blockchain
        Block genesisBlock = new Block(0);
        blockchain = new Blockchain(genesisBlock);

        launchV1();

    }

    public void launchV1() {
        window.setTitle("Dashboard");
        window.setOnCloseRequest(e -> { e.consume(); window.close();});

        // Create new Blockchain
        Block genesisBlock = new Block(0);
        blockchain = new Blockchain(genesisBlock);


        VBox layout1 = new VBox(10);
        GridPane gridLayout = new GridPane();
        gridLayout.setHgap(20);
        gridLayout.setVgap(20);

        // Textfields
        TextField from = new TextField();
        TextField to = new TextField();
        TextField amount = new TextField();

        // Labels
        Label fromL = new Label();
        Label toL = new Label();
        Label amountL = new Label();

        fromL.setText("From: ");
        fromL.setLabelFor(from);

        toL.setText("To: ");
        toL.setLabelFor(to);

        amountL.setText("Amount: ");
        amountL.setLabelFor(amount);


        // Send
        Button send = new Button();
        send.setText("Send Money");
        send.setOnAction(e -> {
            Transaction transaction = new Transaction(from.getText(), to.getText(), Double.parseDouble(amount.getText()));
            transactions.clear(); // TEMP
            transactions.add(transaction);
            Block Gblock = blockchain.getNextBlock(transactions);
            blockchain.addBlock(Gblock);

            System.out.println("TRANSACTION");
        });


        gridLayout.add(fromL, 1, 1);
        gridLayout.add(toL, 2, 1);
        gridLayout.add(amountL, 3, 1);

        gridLayout.add(from, 1, 2);
        gridLayout.add(to, 2, 2);
        gridLayout.add(amount, 3, 2);
        gridLayout.add(send, 4, 2);

        // View Transactions...
        Button viewVC2 = new Button();
        viewVC2.setText("View Blockchain");
        viewVC2.setOnAction(e->launchV2());

        layout1.getChildren().addAll(gridLayout, viewVC2);

        // Scene Setup and Launch
        Scene mainScreen = new Scene(layout1, 800, 600);
        window.setScene(mainScreen);
        window.show();
    }

    public void launchV2() {
        window.setTitle("Transactions");
        window.setOnCloseRequest(e -> { e.consume(); window.close();});

        GridPane gridLayout = new GridPane();
        gridLayout.setHgap(20);
        gridLayout.setVgap(20);


        TableColumn<Block, String> index = new TableColumn("Index");
        index.setCellValueFactory(new PropertyValueFactory<>("index"));
        TableColumn<Block, String> hash = new TableColumn("Hash");
        hash.setCellValueFactory(new PropertyValueFactory<>("hash"));
        TableColumn<Block, String> previousHash = new TableColumn("Previous Hash");
        previousHash.setCellValueFactory(new PropertyValueFactory<>("previousHash"));
        TableColumn<Block, String> nonce = new TableColumn("Nonce");
        nonce.setCellValueFactory(new PropertyValueFactory<>("nonce"));
        TableColumn<Block, String> dateTime = new TableColumn("Date-Time");
        dateTime.setCellValueFactory(new PropertyValueFactory<>("datetime"));
        TableColumn<Block, String> transactionJson = new TableColumn("TransactionJSON");
        transactionJson.setCellValueFactory(new PropertyValueFactory<>("transactions"));


        table.setItems(getBlocks());
        table.getColumns().addAll(index, hash, previousHash, nonce, dateTime, transactionJson);


        Button goBack = new Button();
        goBack.setText("Dashboard");
        goBack.setOnAction(e -> launchV1());

        VBox vBOX = new VBox(20);
        vBOX.getChildren().addAll(table, goBack);

        // Scene Setup and Launch
        Scene mainScreen = new Scene(vBOX, 800, 600);
        window.setScene(mainScreen);
        window.show();

    }

    public ObservableList<Block> getBlocks() {
        ObservableList<Block> blocks = FXCollections.observableArrayList();
        for (Block block: blockchain.blocks) {
            blocks.add(block);
            System.out.println("BLOCk");
        }

        return blocks;
    }

}
