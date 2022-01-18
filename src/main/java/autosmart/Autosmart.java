package autosmart;

import java.io.FileInputStream;
import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;
import org.apache.lucene.wordnet.SynonymMap;

import edu.emory.mathcs.backport.java.util.Arrays;
import io.github.crew102.rapidrake.RakeAlgorithm;
import io.github.crew102.rapidrake.data.SmartWords;
import io.github.crew102.rapidrake.model.RakeParams;
import io.github.crew102.rapidrake.model.Result;
import me.xdrop.fuzzywuzzy.FuzzySearch;
import net.didion.jwnl.JWNLException;
import net.didion.jwnl.data.Word;
import net.didion.jwnl.data.IndexWord;
import net.didion.jwnl.data.POS;
import net.didion.jwnl.data.PointerUtils;
import net.didion.jwnl.data.list.PointerTargetTree;
import net.didion.jwnl.dictionary.Dictionary;

public class Autosmart {
	
	
	 private static void demonstrateTreeOperation(IndexWord word) throws JWNLException {
		 PointerUtils p = null;
		 PointerTargetTree hyponyms = p.getHyponymTree(word.getSenses()[0]);
	        System.out.println("Hyponyms of \"" + word.getLemma() + "\":");
	        hyponyms.print();
		 }
	
	public static Object findClosestMatch(ArrayList<String> keywords, ArrayList<String> tests) {
	    int distance = Integer.MAX_VALUE;
	    Object closest = null;
	    for (Object compareObject : keywords) {
	        int currentDistance = StringUtils.getLevenshteinDistance(compareObject.toString(), tests.toString());
	        if(currentDistance < distance) {
	            distance = currentDistance;
	            closest = compareObject;
	        }
	    }
	    return closest;
	}
	

	public static void getTestsFromKeywords(ArrayList<String> extractedScenarios, ArrayList<String> extractedTests) {
		ArrayList<String> testNames  = new ArrayList<String>();

		for (Integer i = 0; i < extractedScenarios.size(); i++) {

			String[] scenarioSteps = removeDigits(extractedScenarios.get(i)).split(",");
			ArrayList<String> scenarioKeywords = new ArrayList<String>();
			int similarity = 0;
			Integer temp = 0;
			int position = 0;
			for(int m = 0;m<scenarioSteps.length;m++)
			{
			if (!(scenarioSteps[m].length() <= 2)) {
				scenarioKeywords.add(scenarioSteps[m]);
			}
			}
			
			System.out.println("keywords:"+scenarioKeywords);
			testNames.clear();

			for (Integer j = 0; j < scenarioKeywords.size(); j++) {
				

				for (Integer k = 0; k < extractedTests.size(); k++) {
					//System.out.println(scenarioKeywords.get(j) + extractedTests.get(k));
					if (k == 0) {
						temp = FuzzySearch.ratio(scenarioKeywords.get(j),extractedTests.get(k));
						//System.out.println("Similarity for first check is: "+temp);
						position = k;
					} else {
						similarity = FuzzySearch.ratio(scenarioKeywords.get(j),extractedTests.get(k));
						//System.out.println("Rest: "+similarity);
						if (similarity > temp) {
							temp = similarity;
							position = k;
							//System.out.println("Similarity updated: "+similarity);
						}

					}

				}
				System.out.println("Most Similar TestName is " + extractedTests.get(position));

				testNames.add(extractedTests.get(position));
				//System.out.println("Testnames: " + testNames);
			}
			System.out.println("Final Testnames: " + testNames);
		}
	}

	public static String removeDigits(String text) {
		char[] charArray = text.toCharArray();

		String result = "";

		for (int i = 0; i < charArray.length; i++) {
			if (!Character.isDigit(charArray[i])) {
				result = result + charArray[i];
			}
		}
		result = result.replaceAll("\\(.\\)", "").replaceAll("\\(\\)", "").replaceAll(" ", "").replaceAll("\\[", "").replaceAll("\\]", "");
		return result;
	}

	public static void main(String[] args) throws java.io.IOException, JWNLException {

		String basePath = System.getProperty("user.dir");
		String featurePath = "/BDDs/Petstore.feature";
		String testPath = "/src/main/java/autosmart/RestAssuredTests.java";

		// Create an object to hold algorithm parameters
		String[] stopWords = new SmartWords().getSmartWords();
		// String[] stopPOS = {"VB", "VBD", "VBG", "VBN", "VBP", "VBZ"};
		String[] stopPOS = { null };
		int minWordChar = 1;
		boolean shouldStem = true;
		String phraseDelims = "[-,.?():;\"!/]";
		RakeParams params = new RakeParams(stopWords, stopPOS, minWordChar, shouldStem, phraseDelims);

		// Create a RakeAlgorithm object
		String POStaggerURL = basePath + "/models/en-pos-maxent.bin"; // The path to your POS tagging model
		String SentDetecURL = basePath + "/models/en-sent.bin"; // The path to your sentence detection model

		RakeAlgorithm rakeAlg = new RakeAlgorithm(params, POStaggerURL, SentDetecURL);

		// Get scenario keywords
		PatternMatcherAlgo pm = new PatternMatcherAlgo();
		ArrayList<String> extractedScenarios = pm.extractKeywords(basePath + featurePath, "#Start", "#End");
		Result scenariosResult = null;
		ArrayList<String> extractedScenariosKeywords = new ArrayList<String>();
		for (int i = 0; i < extractedScenarios.size(); i++) {
			scenariosResult = rakeAlg.rake(extractedScenarios.get(i));
			extractedScenariosKeywords.add(scenariosResult.distinct().toString());
			System.out.println(scenariosResult.distinct().toString());
		}

		// Get test keywords
		ArrayList<String> extractedTests = pm.extractKeywords(basePath + testPath, "public void", "\\(\\)");
		System.out.println("Extracted Tests: " + extractedTests);


		getTestsFromKeywords(extractedScenariosKeywords, extractedTests);
		
//		 String[] words = new String[] { "search", "add", "modify", "delete", "place"};
//		 SynonymMap map = new SynonymMap(new FileInputStream("C:\\Users\\Sanat\\OneDrive\\Documents\\wn_s.pi.txt"));
//		 for (int i = 0; i < words.length; i++) {
//		     String[] synonyms = map.getSynonyms(words[i]);
//		     System.out.println(words[i] + ":" + java.util.Arrays.asList(synonyms).toString());
//		 }
		 
		// Wordnet w  = 
		
	}
}
