import com.itheima.utils.QiniuUtile;
import org.junit.Test;

public class TestQiniuUtil {
    @Test
    public void upload() {
        //上传文件地址
        String localFilePath = "E:\\img\\e1bbda9f-22da-4b11-8f17-8f234e2c67d4.jpeg";
        //文件名
        String fileName = "e1bbda9f-22da-4b11-8f234e2c67d4.jpeg";

        QiniuUtile.upload(localFilePath, fileName);
    }

    @Test
    public void delete() {
        //文件名
        String fileName = "e1bbda9f-22da-4b11-8f234e2c67d4.jpeg";

        QiniuUtile.delete(fileName);
    }
}
