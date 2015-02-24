package image;

import ij.ImagePlus;
import image.helpers.ProcessHelper;
import image.models.ParticleResult;
import image.models.Measurement;
import image.models.Result;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;


public class ApplicationMain {

	private String[] selectedMeasurements;
	private String selectedThreshold;
	private Measurement measurement;
	private String filePath;
	private ImagePlus imagePlus;

	public ApplicationMain(String[] selectedMeasurements, String selectedThreshold, ImagePlus imagePlus, String theUploadedFilePath){
		this.selectedMeasurements = selectedMeasurements;
		this.selectedThreshold = selectedThreshold;
		this.measurement = new Measurement();

		this.imagePlus = imagePlus;
		this.filePath = theUploadedFilePath;
	}

	public ApplicationMain(ImagePlus imagePlus, String uploadedFilePath){
		this.imagePlus = imagePlus;
		this.filePath = uploadedFilePath;
	}

	public Result analyseImage(){
		Result theResult;
		ProcessHelper processHelper = new ProcessHelper(this.imagePlus, this.filePath);
		int measurements = this.measurement.convertMeasurementListToInt(this.selectedMeasurements);
		theResult = processHelper.analyseImage(measurements, this.selectedThreshold);
		theResult.setSelectedMeasurements(this.measurement.selectedMeasurementsMap);
		return theResult;
	}

	public Result  countParticles() {
		Result theResult;
		ProcessHelper processHelper = new ProcessHelper(this.imagePlus, this.filePath);
		int measurements = this.measurement.convertMeasurementListToInt(this.selectedMeasurements);
		theResult =  processHelper.countParticles(this.selectedThreshold, measurements);
		theResult.setSelectedMeasurements(this.measurement.selectedMeasurementsMap);
		return theResult;
	}

	public BufferedImage applyThreshold(String selectedThreshold){
		if (selectedThreshold != null){
			ProcessHelper processHelper = new ProcessHelper(this.imagePlus, this.filePath);
			return processHelper.applyThreshold(selectedThreshold);
		} else {
			ProcessHelper processHelper = new ProcessHelper(this.imagePlus, this.filePath);
			return processHelper.applyThreshold("Default");
		}

	}
}
