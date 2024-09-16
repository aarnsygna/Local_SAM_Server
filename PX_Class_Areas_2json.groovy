px_classifier_model = "MALDI-mesm-175RT-V2" // Name of pixel classifier to use
fl_name = getCurrentImageName()[0..-5] + "_withMeasurements.json"//Create file name.. Remove '.czi'
resetSelection()
selectObjectsByClassification("Glom Tuft")
createDetectionsFromPixelClassifier(px_classifier_model, 8.0, 0.5, "SPLIT", "DELETE_EXISTING")
println("Created Detections from pixel classifer ${px_classifier_model}")

clearAnnotationMeasurements() // Clears measurements to prevent overlap, does nothing if not present
clearMeasurements(qupath.lib.objects.PathDetectionObject)
selectDetections()// Selecting recently created detections
// Measurements calculated on Detections, with default Key name is [{Class}_area_µm^2]
addPixelClassifierMeasurements(px_classifier_model, "")
resetSelection()// Clear selection
// Select the annotations/objects in the two Classes
selectObjectsByClassification("Glom Tuft", "Glom Unit")
addShapeMeasurements("AREA")// Area measurements added to the 2 classes above.
println("Calculated Areas. Moving to next step to JSON output...")

// Same as in QuPath GUI for setting hierarchy location
resolveHierarchy()
// Selecting hiearchy and assigning it to variable
def hierarchy = getCurrentHierarchy()
// Collects current annos and prepares them for json and preserves hiearchy ??AR unsure
def json = GsonTools.getInstance(true).toJson(hierarchy)
// File path to save the JSON is at working directory for project
def outputFile = new File(buildFilePath(PROJECT_BASE_DIR, fl_name))
// Write the JSON to specified file path and name
outputFile.withWriter {writer -> writer.write(json)}
println("Done!")