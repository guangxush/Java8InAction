import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author: guangxush
 * @create: 2019/12/30
 */
public class AroundTest {
    public static String processFile1() throws IOException {
        try(BufferedReader br = new BufferedReader(new FileReader("data.txt"))){
            return br.readLine();
        }
    }

    public static void main(String[] args) throws IOException {
        //传递Lambda
        String result = processFile((BufferedReader br) -> br.readLine() + br.readLine());

        String oneLine = processFile((BufferedReader br)-> br.readLine());
        //读取两行
        String twoLine = processFile((BufferedReader br)-> br.readLine()+br.readLine());
    }

    //执行一个行为
    public static String processFile(BufferedReaderProcessor p) throws IOException{
        try(BufferedReader br = new BufferedReader(new FileReader("data.txt"))){
            return p.process(br);
        }
    }

}
