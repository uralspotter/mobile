import org.junit.Assert;
import org.junit.Test;

public class MainClassTest extends MainClass {

    int number = this.getLocalNumber();

    @Test
    public void testGetLocalNumber() {
        Assert.assertTrue("The proposed algorithm is equal to 14", number == 14);
    }


}
