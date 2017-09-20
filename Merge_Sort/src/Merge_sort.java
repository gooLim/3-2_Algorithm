import java.io.*;
import java.util.StringTokenizer;

public class Merge_sort {
    public void sort() {
        String str = null;
        String _str= "";
        File infile = new File("./input.txt");
        File outfile = new File("./201302469_output.txt");
        BufferedReader br = null;
        BufferedWriter bw = null;

        try {
            br = new BufferedReader(new FileReader(infile));
            bw = new BufferedWriter(new FileWriter(outfile));
            int index = 0;
            while( (str = br.readLine() ) != null) {
                _str += str;
            }
            StringTokenizer tokens = new StringTokenizer(_str);
            int[] sdata = new int[tokens.countTokens()];
            int[] tmp = new int[tokens.countTokens()];
            while( tokens.hasMoreTokens() ) {
                String token = tokens.nextToken();
                sdata[index++] = Integer.parseInt( token );
            }


            long startT= System.nanoTime();
            mergeSort(sdata, tmp, 0, sdata.length -1);
            long endT = System.nanoTime();

            for(int count = 0; count < sdata.length; count++) {
                bw.write( (count == sdata.length-1) ? sdata[count]+"" : sdata[count] + " " );
            }
            bw.flush();
            System.out.println(endT - startT);

            br.close();
            bw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mergeSort( int[] sdata, int[] tmp, int p, int q) {
        int mid;
        if( p < q ) {
            mid = (p+q) / 2;
            mergeSort(sdata,tmp, p, mid);
            mergeSort(sdata, tmp, mid + 1, q);
            merge(sdata, tmp, p, mid, q);
        }
    }

    public void merge(int[] sdata, int[] sorted, int p, int mid, int q) {
        int i, j, k, m;
        i = p;
        j = mid+1;
        k = p;
        while( i <= mid && j <= q ) {
            if( sdata[i] <= sdata[j] )   sorted[k] = sdata[i++];
            else  sorted[k] = sdata[j++];

            k++;
        }

        if(i > mid) {
            for (m = j; m <= q; m++, k++)
                sorted[k] = sdata[m];
        }

        else {
            for(m = i; m <= mid; m++, k++)
                sorted[k] = sdata[m];
        }

        for(m = p; m <= q; m++) {
            sdata[m] = sorted[m];
        }

    }

}
