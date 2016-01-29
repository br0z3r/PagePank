import java.io.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.StringTokenizer;


public class Main {

    public static void main(String[] args) throws IOException {
        InputStream stdin = null;
        stdin = System.in;
        FileInputStream stream = new FileInputStream("/<>/test");
        System.setIn(stream);

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        mapPageRank(bufferedReader);


    }

    static void mapPageRank(BufferedReader bufferedReader) throws IOException  {
        if(!bufferedReader.ready() ) {
            return;
        }

        String line;
        float distance;
        float countOutNode;
        String outNodes;
        String tmpDist;
        String outAppend;

        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
        decimalFormatSymbols.setDecimalSeparator('.');
        DecimalFormat decimalFormat = new DecimalFormat("##0.000");
        decimalFormat.setDecimalFormatSymbols( decimalFormatSymbols );

        StringBuilder outLine = new StringBuilder();

        StringTokenizer st;

        while ((line = bufferedReader.readLine()) != null && line.length() != 0) {
            st = new StringTokenizer(line);
            if(st.countTokens() != 3) {
                continue;
            }

            System.out.println(line);

            st.nextToken();
            tmpDist = st.nextToken();
            outNodes = st.nextToken();

            st = new StringTokenizer(outNodes,"{,}");

            countOutNode = st.countTokens();

            if(countOutNode == 0 ) {
                continue;
            }

            if(tmpDist.compareTo("INF") == 0) {
                outAppend = "\tINF\t{}";
            } else {

                distance = Float.parseFloat(tmpDist);
                outAppend = outLine.append("\t").
                        append(decimalFormat.format(distance / countOutNode)).
                        append("\t{}").toString();
                outLine.setLength(0);
            }


//            st = new StringTokenizer(outNodes,"{,}");
            while (st.hasMoreTokens()) {
                outLine.append(st.nextToken()).append(outAppend);
                System.out.println(outLine);
                outLine.setLength(0);
            }


        }

    }
}

