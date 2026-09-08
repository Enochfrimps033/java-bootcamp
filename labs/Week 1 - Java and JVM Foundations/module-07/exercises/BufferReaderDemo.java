import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BufferReaderDemo {
    public static void main(String[] args) {
        try( BufferedReader reader =
                new BufferedReader(new FileReader("input.txt"))){
            String line;

            while((line = reader.readLine()) != null){
                System.out.println(line);
            }
        }catch(IOException e) {
            System.out.println("error reading file: " + e.getMessage());
        }
    }
        }