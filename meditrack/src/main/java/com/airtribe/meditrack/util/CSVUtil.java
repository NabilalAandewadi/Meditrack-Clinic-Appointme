package com.airtribe.meditrack.util;



import com.airtribe.meditrack.constant.ConstantsDetails;

import com.airtribe.meditrack.entity.Appointment;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVUtil {
    public static void exportAppointmentsToCSV(List<Appointment> appointments) {
        try (FileWriter writer = new FileWriter(ConstantsDetails.CSV_FILE_PATH)) {
            writer.write("ID,Doctor,Patient,Date,Status\n");
            for (Appointment app : appointments) {
                writer.write(app.getId() + "," + app.getDoctor().getName() + "," + app.getPatient().getName() + "," + app.getDate() + "," + app.getStatus() + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException("Error exporting to CSV", e);
        }
    }
}