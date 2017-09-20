import java.io.*;
import java.util.StringTokenizer;

public class Insertion_Binary {
        public void sort() {
            String str;
            String _str = "";
            File infile = new File("./Best\\100000.txt");
            File outfile = new File("./201302469_output.txt");
            BufferedReader br = null;
            BufferedWriter bw = null;
            try {
                br = new BufferedReader(new FileReader(infile));
                bw = new BufferedWriter(new FileWriter(outfile));
                int index = 0;

                while( (str = br.readLine()) != null ) {
                    _str += str;
                }
                StringTokenizer tokens = new StringTokenizer(_str);
                int[] sdata = new int[tokens.countTokens()];

                while (tokens.hasMoreTokens()) {
                    String token = tokens.nextToken();
                    sdata[index++] = Integer.parseInt(token);
                }

                long startT= System.nanoTime();
                insertionSort(sdata);
                long endT = System.nanoTime();

                for(int count = 0; count < sdata.length; count++) {
                    bw.write(count == sdata.length-1 ? sdata[count]+"": sdata[count]+ " " );
                }
                bw.flush();
                System.out.println(endT - startT);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        public void insertionSort(int[] sdata) {
            for (int p = 1; p < sdata.length; p++) {
                int tmp = sdata[p];
                int i = p - 1;

                int location = binary_Search(sdata, tmp,0, i);

                while ( location <= i ) {
                    sdata[i+1] = sdata[i];
                    i--;
                }
                sdata[i+1] = tmp;

            }
        }

        public int binary_Search(int[] sdata, int tmp, int start, int end) {
            if( end <= start )  return (sdata[end] < tmp) ? end + 1 : end ;

            int mid = ( start + end ) / 2;

            if( sdata[mid] == tmp ) return mid + 1;

            if( sdata[mid] < tmp ) return binary_Search(sdata, tmp, mid+1, end);
            else return binary_Search(sdata, tmp, start, mid);
        }
    }



