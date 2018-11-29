import org.junit.Assert;
import org.junit.Test;

public class MainClassTest extends MainClass {

    int number = this.getLocalNumber();
    int number2 = this.getClassNumber();

    @Test
    public void testGetLocalNumber() {
        Assert.assertTrue("The proposed algorithm is equal to 14", number == 14);
    }

    @Test
    public void testGetClassNumber() {
        Assert.assertTrue("The number returned by less 45", number2 > 45);
    }

    @Test
    public void testGetClassString() {
        Assert.assertTrue("The string does not contain the word Hello or hello", class_string.contains("Hello") || class_string.contains("hello"));
    }
}
