package PR2.test.java;

import PR2.main.java.org.example.Transaction;
import PR2.main.java.org.example.TransactionCSVReader;
import org.junit.jupiter.api.Test;
import java.util.List;
import org.junit.jupiter.api.Assertions;

public class TransactionCSVReaderTest {

    @Test
    public void testReadTransactions() {
        String filePath = "https://informer.com.ua/dut/java/pr2.csv";
        List<Transaction> transactions = TransactionCSVReader.readTransactions(filePath);

        Assertions.assertNotNull(transactions, "Список транзакцій не повинен бути null");

        Assertions.assertEquals(26, transactions.size(), "Має бути 100 транзакцій");
    }
}
