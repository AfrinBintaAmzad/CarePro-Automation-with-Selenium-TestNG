package utils;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.testng.annotations.DataProvider;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class DataSet {
    @DataProvider(name = "data-provider")
    public Object[][] setData() throws IOException {
        return readDataFromCSV("./src/test/resources/dataset.csv");
    }

    private Object[][] readDataFromCSV(String csvFilePath) throws IOException {
        List<Object[]> data = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(csvFilePath))) {
            String[] nextLine;
            reader.readNext();

            while ((nextLine = reader.readNext()) != null) {
                String nrc= nextLine[0];
                String time = nextLine[2];
                double weight = Double.parseDouble(nextLine[3]);
                double height = Double.parseDouble(nextLine[4]);
                int temperature = Integer.parseInt(nextLine[5]);
                int systolic = Integer.parseInt(nextLine[6]);
                int diastolic = Integer.parseInt(nextLine[7]);
                int pulseRate = Integer.parseInt(nextLine[8]);
                int respiratoryRate = Integer.parseInt(nextLine[9]);
                int oxygenSaturation = Integer.parseInt(nextLine[10]);

                Object[] row = {nrc, time, weight, height, temperature, systolic, diastolic, pulseRate, respiratoryRate, oxygenSaturation};
                data.add(row);
            }
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }

        Object[][] dataArray = new Object[data.size()][];
        return data.toArray(dataArray);
    }
}
