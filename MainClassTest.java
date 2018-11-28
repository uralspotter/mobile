import org.junit.Assert;
import org.junit.Test;

public class MainClassTest extends MainClass {

    int number = this.getLocalNumber();
    int number2 = this.getClassNumber();

    @Test
    public void myFirstTest() {
        Assert.assertTrue("The proposed algorithm is equal to 14", number == 14);
    }

    @Test
    public void mySecondTest() {
        Assert.assertTrue("The number returned by less 45", number2 > 45);
    }
}
