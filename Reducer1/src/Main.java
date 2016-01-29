import java.io.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.StringTokenizer;


public class Main {

    public static void main(String[] args) throws IOException {

        InputStream stdin = null;
        stdin = System.in;
        FileInputStream stream = new FileInputStream("/home/air/IdeaProjects/BigData/5.Algorithms_on_graph_MapReduce/PagePank/Reducer1/test");
        System.setIn(stream);

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        reducePageRank(bufferedReader);

    }

    static void reducePageRank(BufferedReader bufferedReader) throws IOException  {

        if(!bufferedReader.ready() ) {
            return;
        }

        String line;

        String curNode = "", tmpNode;
        String curPath = "INF", tmpPath;
        String curDest = "{}", tmpDest;
        float rank = 0, tmpRank = 0;

        int cPath, tPath;

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

            tmpNode = st.nextToken();
            tmpPath = st.nextToken();
            tmpDest = st.nextToken();

            if ( curNode.compareTo(tmpNode) != 0) {

                if (!curNode.isEmpty()) {

                    outLine.append(curNode).append("\t").
                            append(decimalFormat.format(rank)).append("\t").
                            append(curDest);

                    System.out.println(outLine);
                }

                curNode = tmpNode;
                curPath = tmpPath;
                curDest = tmpDest;

                if (curDest.compareTo("{}") == 0 ) {
                    rank = Float.parseFloat(curPath);
                } else {
                    rank = 0;
                }

                outLine.setLength(0);

                continue;
            }

            if (tmpDest.compareTo("{}") == 0) {
                rank += Float.parseFloat(tmpPath);
            } else {
                curDest = tmpDest;
            }
        }
        outLine.append(curNode).append("\t").
                append(decimalFormat.format(rank)).append("\t").
                append(curDest);

        System.out.println(outLine);

    }
}
