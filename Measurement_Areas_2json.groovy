// Clear existing measurements from annotations and detections to avoid overlap or duplication.
// If no measurements are present, these functions will have no effect
clearAnnotationMeasurements() // Clears measurements from annotations
clearMeasurements(qupath.lib.objects.PathDetectionObject)// Clears measurements from detection objects

// Clears any current user selections from GUI
resetSelection()
// Select objects classified under specific classes for measurement.
selectObjectsByClassification("Glom Tuft", "Glom Unit", "Mesangial Matrix")
// Computes the area for each object from classes above and appends it as a measurement attribute.
// More exact area calculation as per Pete comment. See link:
// https://forum.image.sc/t/exporting-annotations-and-detections-in-qupath-as-geojson-or-json/101683/5
addShapeMeasurements("AREA")
println("Calculated Areas. Moving to next step to JSON output...")

// Same as in QuPath GUI for setting hierarchy 
// Resolving object hierarchy for annotations (Unit and Tuft), by default are not in a hieararchy
resolveHierarchy()
// Selecting current object hiearchy and assigning it to variable
def hierarchy = getCurrentHierarchy()
// Send hierarchy to convert into JSON format with pretty printing enabled, as a result of 'True'
def json = GsonTools.getInstance(true).toJson(hierarchy)
// Filename for the output JSON by removing the file extension and adding descriptor 
fl_name = getCurrentImageName()[0..-5] + "_withMeasurements.json"
// Save the file in a 'Exports' subdirectory within the project directory
def outputFile = new File(buildFilePath(PROJECT_BASE_DIR, 'json_Exports', fl_name))
// Write the JSON data to the specified file
outputFile.withWriter {writer -> writer.write(json)}
println("Done! Exported to project directory")