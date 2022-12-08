import org.junit.Test;

public class StringTest {
    /**
     * 获取文件名
     */
    @Test
    public void testString() {
        String imgUrl = "http://rmdbflmfw.hb-bkt.clouddn.com/93484fc6-ee20-4e63-89ac-f7cda02ac8671.jpg";
        String fileName = imgUrl.substring(imgUrl.lastIndexOf("/") + 1);
        System.out.println(fileName);
    }
}
