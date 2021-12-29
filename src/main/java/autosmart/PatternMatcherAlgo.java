package autosmart;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternMatcherAlgo {

	private static String readUsingFileReader(String fileName) throws IOException {
		File file = new File(fileName);
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		StringBuilder sb = new StringBuilder();
		String line = br.readLine();
		System.out.println("Reading text file using FileReader");
		while (line != null) {
			sb.append(line);
			line = br.readLine();
		}
		return sb.toString();
	}

	public ArrayList<String> extractScenarios() throws IOException {
		String fileName = "C:\\Users\\Sanat\\eclipse-workspace\\autosmart\\BDDs\\Petstore.feature";

		ArrayList<String> scenarios = new ArrayList<String>();
		String str = readUsingFileReader(fileName);
		Pattern pattern = Pattern.compile("#Start(.*?)#End", Pattern.DOTALL);
		Matcher matcher = pattern.matcher(str);
		while (matcher.find()) {
			scenarios.add(matcher.group(1));
		//	System.out.println(scenarios);
		}
		return scenarios;
	}

}