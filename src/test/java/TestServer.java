import org.junit.Assert;
import org.junit.Test;

public class TestServer {
    @Test
    public void testSearchCubeArea(){
        int a =2;
        int expected = 8;
        Assert.assertEquals(expected, ServerOk.searchCubeArea(a));
    }
    @Test
    public void testSearchCylinderArea(){
        String a = "2 3";
        int expected = 56;
        int result = (int)ServerOk.searchCylinderArea(a);
        Assert.assertEquals(expected,result);
    }
    @Test
    public void testSearchConeArea(){
        String a = "2 3";
        int expected = 5;
        int result = (int) ServerOk.searchConeArea(a);
        Assert.assertEquals(expected,result);
    }
    @Test
    public void testSplit(){
        String a = "2 3";
        int [] result = ServerOk.doSplit(a);
        int [] expected = {2,3};
        Assert.assertArrayEquals(expected,result);
    }


}
