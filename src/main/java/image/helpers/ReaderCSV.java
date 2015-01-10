package image.helpers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import image.models.ImageResult;

public class ReaderCSV {
	private BufferedReader br;
	private HashMap<String, String> map;
	private String filePath;
	public List<ImageResult> imageResults;

	public ReaderCSV(String theFile) {
		filePath = theFile;
	}

	public List<ImageResult> read() {
		String line;
		String csvSplitBy = ",";

		try {
			map = new HashMap<>();
			imageResults = new ArrayList<>();
			br = new BufferedReader(new FileReader(filePath));
			String[] rowHeaders = br.readLine().split(csvSplitBy);
			rowHeaders[0] = "id";
			while ((line = br.readLine()) != null) {
				String[] row = line.split(csvSplitBy);
				map = matchHeadersWithData(rowHeaders, row);
				ImageResult imr = new ImageResult(map);
				imageResults.add(imr);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return imageResults;
	}

	private HashMap<String, String> matchHeadersWithData(String[] headers, String[] data) {
		if (headers.length != data.length) {
			return map;
		} else {
			for (int i=0; i<headers.length; i++) {
				map.put(headers[i], data[i]);
			}
		}
		return map;
	}
}
