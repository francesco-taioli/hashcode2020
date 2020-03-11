import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

//        String [] filename =  new String[]{
//                "a_example.txt",
//                "b_read_on.txt",
//                "c_incunabula.txt",
//                "d_tough_choices.txt",
//                "e_so_many_books.txt",
//                "f_libraries_of_the_world.txt"};
        String [] filename =  new String[]{"e_so_many_books.txt"};
        try {
            new Handler(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
