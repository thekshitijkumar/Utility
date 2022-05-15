import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CsvTest {


    static String filename = "abc";
    static String tempFileName = "temp";
    static String tempKeyword = "random";
    static String finalFileName = "final";

    // 31 files
    static int partition = 30;

    public static void main(String[] args) {

        int index = 61;
        int size = 61;

        for(int i=0 ; i<=index ;i++)
        {
            combineCsv(i, size);
        }

    }

    // index start from zero and till target
    static void  combineCsv(int index, int size) {


        System.out.println("Index ::"+ index+ " size::"+size );
        if(size==0)
        {
//            renameCSv
            System.out.println(finalFileName+".cav");
        }
        // not equal to total but compose limit reached
        else if (index !=0 && index % partition == 0 && index < (size ) ) {

//            System.out.println("Index :: "+ index);
            int startIndex =index -partition;

            List<String> inputCsvList = IntStream.range(startIndex, index+1)
                    .boxed()
                    .map(x -> tempFileName + "/" + filename + x + ".csv")
                    .collect(Collectors.toList());
            testCompose(inputCsvList, tempFileName.concat("/")
                    .concat(tempKeyword)
                    .concat(index + "")
                    .concat(".csv"));
            // rename created file
            System.out.println(" Renaming created file ::"+ tempFileName
                    .concat( "/")
                    .concat(filename+ index)
                    .concat(".csv"));
        }
        // final size reached
        else if(index== (size) ){

            List<String> inputCsvList = IntStream.range(index- (index%partition ==0?partition:index%partition), index+1)
                    .boxed()
                    .map(x -> tempFileName + "/" + filename + x + ".csv")
                    .collect(Collectors.toList());

            testCompose(inputCsvList, finalFileName
                    .concat(".csv"));
        }
        else
        {
            // do nothing
        }
    }

    static void testCompose(List<String> fileNames, String outputCsvFileName) {
        System.out.print("fileNames are ::");
        fileNames.forEach(x-> System.out.print(x+ " "));
        System.out.print("Size of Input list :: " + fileNames.size()+ " ");
        System.out.println("outputCsvFileName:: " + outputCsvFileName);
    }
}
