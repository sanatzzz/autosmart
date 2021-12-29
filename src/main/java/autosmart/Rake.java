package autosmart;
import java.util.ArrayList;
import io.github.crew102.rapidrake.RakeAlgorithm;
import io.github.crew102.rapidrake.data.SmartWords;
import io.github.crew102.rapidrake.model.RakeParams;
import io.github.crew102.rapidrake.model.Result;

public class Rake {

	public static void main(String[] args) throws java.io.IOException {

		// Create an object to hold algorithm parameters
		String[] stopWords = new SmartWords().getSmartWords();
		// String[] stopPOS = {"VB", "VBD", "VBG", "VBN", "VBP", "VBZ"};
		String[] stopPOS = { null };
		int minWordChar = 1;
		boolean shouldStem = true;
		String phraseDelims = "[-,.?():;\"!/]";
		RakeParams params = new RakeParams(stopWords, stopPOS, minWordChar, shouldStem, phraseDelims);

		// Create a RakeAlgorithm object
		String POStaggerURL = "C:\\Users\\Sanat\\OneDrive\\Desktop\\models\\en-pos-maxent.bin"; // The path to your POS
																								// tagging model
		String SentDetecURL = "C:\\Users\\Sanat\\OneDrive\\Desktop\\models\\en-sent.bin"; // The path to your sentence
																							// detection model
		RakeAlgorithm rakeAlg = new RakeAlgorithm(params, POStaggerURL, SentDetecURL);

		// Call the rake method
		PatternMatcherAlgo pm = new PatternMatcherAlgo();
		ArrayList<String> extractedScenarios = pm.extractScenarios();
		Result result = null;
		for (int i = 0; i < extractedScenarios.size(); i++) {
			result = rakeAlg.rake(extractedScenarios.get(i));
			System.out.println(result.distinct());
		}

	}
}
