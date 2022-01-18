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
		while (line != null) {
			sb.append(line);
			line = br.readLine();
		}
		br.close();
		return sb.toString();
	}

	public ArrayList<String> extractKeywords(String filepath, String start, String end) throws IOException {
		ArrayList<String> keywords = new ArrayList<String>();
		String str = readUsingFileReader(filepath);
		Pattern pattern = Pattern.compile(start + "(.*?)" + end, Pattern.DOTALL);
		Matcher matcher = pattern.matcher(str);
		while (matcher.find()) {
			keywords.add(matcher.group(1));
		}
		return keywords;
	}

}