import java.io.PrintWriter;
import java.io.FileOutputStream;

public class FileUtil {
	/*
	 * Write something to a file
	 */
	public static void writeFile(String content, String filename) {
		try {
			PrintWriter out = new PrintWriter(new FileOutputStream(filename));

			out.print(content);
			out.flush();
			out.close();
			out = null;
		} catch (Exception e) {
		}
	}
}
