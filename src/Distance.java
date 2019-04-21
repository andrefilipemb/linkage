import java.io.FileReader;
import java.util.HashMap;
import uk.ac.shef.wit.simmetrics.similaritymetrics.*;

public class Distance {
	public static void main(String[] args) {

		if (args.length < 2) {
			System.err.println("Passe duas strings para que possam ser comparadas");
		}

		String arg1 = args[0];
		String arg2 = args[1];
		boolean normalized = false;

		if (args.length > 2 && args[2].equals("normalize=false")) {
			normalized = true;
		}

		HashMap<String, Object> algoritmos = new HashMap<String, Object>();

		algoritmos.put("Distância Euclidiana", new EuclideanDistance());
		algoritmos.put("Levenshtein", new Levenshtein());
		algoritmos.put("MongeElkan", new MongeElkan());
		algoritmos.put("NeedlemanWunch", new NeedlemanWunch());
		algoritmos.put("QGramsDistance", new QGramsDistance());
		algoritmos.put("SmithWaterman", new SmithWaterman());
		algoritmos.put("BlockDistance", new BlockDistance());
		algoritmos.put("CosineSimilarity", new CosineSimilarity());
		algoritmos.put("DiceSimilarity", new DiceSimilarity());
		algoritmos.put("JaccardSimilarity", new JaccardSimilarity());
		algoritmos.put("Jaro", new Jaro());
		algoritmos.put("JaroWinkler", new JaroWinkler());
		algoritmos.put("MatchingCoefficient", new MatchingCoefficient());
		algoritmos.put("OverlapCoefficient", new OverlapCoefficient());

		float maior = Float.MIN_VALUE;
		String key_maior = "";
		for (String key : algoritmos.keySet()) {

			AbstractStringMetric metric = (AbstractStringMetric) algoritmos.get(key);
			float result = 0;
			if (!normalized) {
				result = metric.getSimilarity(arg1, arg2);
			} else {
				result = metric.getUnNormalisedSimilarity(arg1, arg2);
			}

			if (result > maior) {
				maior = result;
				key_maior = key;
			}
			System.out.println(key + " - score: " + result);

		}

		System.out.println("\n\n [Melhor score]: " + key_maior + " " + maior);

	}

}
