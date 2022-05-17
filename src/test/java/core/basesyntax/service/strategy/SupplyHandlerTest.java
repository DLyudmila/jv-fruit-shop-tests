package core.basesyntax.service.strategy;

import core.basesyntax.db.Storage;
import core.basesyntax.model.Fruit;
import core.basesyntax.model.FruitTransaction;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class SupplyHandlerTest {
    private static OperationHandler supplyHandler;
    private FruitTransaction fruitTransaction;

    @BeforeClass
    public static void beforeClass() {
        supplyHandler = new SupplyHandler();
    }

    @Before
    public void setUp() {
        fruitTransaction = new FruitTransaction();
    }

    @After
    public void tearDown() {
        Storage.fruits.clear();
    }

    @Test
    public void handle_validData_Ok() {
        fruitTransaction.setFruit("apple");
        fruitTransaction.setQuantity(20);
        supplyHandler.handle(fruitTransaction);
        Assert.assertTrue(Storage.fruits.containsKey(new Fruit("apple")));
        Integer amountActual = Storage.fruits.get(new Fruit("apple"));
        Integer amountExpected = 20;
        Assert.assertEquals(amountExpected, amountActual);
    }

    @Test(expected = RuntimeException.class)
    public void handle_null_notOk() {
        fruitTransaction = null;
        supplyHandler.handle(fruitTransaction);
    }
}
