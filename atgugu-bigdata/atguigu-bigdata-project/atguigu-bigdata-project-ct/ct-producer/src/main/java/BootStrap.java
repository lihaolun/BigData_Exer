import com.atguigu.bigdata.project.ct.common.bean.Producer;
import com.atguigu.bigdata.project.ct.producer.bean.LocalFileDataProducer;
import com.atguigu.bigdata.project.ct.producer.io.LocalFileDataIn;
import com.atguigu.bigdata.project.ct.producer.io.LocalFileDataOut;

public class BootStrap {

    public static void main(String[] args) {
        if (args.length!=2){
            System.out.println("参数个数不正确，请参考 java -jar ***.jar path1 path2");
        }
        //构建对象
        Producer producer = new LocalFileDataProducer();

      /*  String in = "E:\\资料\\18_尚硅谷大数据技术之电信客服\\2.资料\\辅助文档\\contact.log";
        String out = "E:\\contact.log";
*/
        String in = args[0];
        String out = args[1];
        producer.setIn(new LocalFileDataIn(in));
        producer.setOut(new LocalFileDataOut(out));
        //生产数据
        producer.producer();
    }

}
