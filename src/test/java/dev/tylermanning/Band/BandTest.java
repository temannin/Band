package dev.tylermanning.Band;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Random;

import org.json.JSONObject;
import org.junit.Test;

public class BandTest {

    @Test
    public void testSearchSpeed() {

        Band band = new Band();
        JSONObject actual = new JSONObject();
        actual.put("firstname", "kyrie");
        actual.put("lastname", "irving");
        actual.put("address", "Something something lane");
        band.addToBand(actual, new String[] { "firstname", "lastname", "address" });

        actual = new JSONObject();
        actual.put("firstname", "kyrie");
        actual.put("lastname", "james");
        actual.put("address", "Something something lane2");
        band.addToBand(actual, new String[] { "firstname", "lastname", "address" });

        ArrayList<String> names = new ArrayList<String>();

        for (int i = 0; i < 28828; i++) {

            JSONObject test = new JSONObject();
            String firstname = getRandomString();
            test.put("firstname", firstname);
            test.put("lastname", getRandomString());
            test.put("address", getRandomString());
            test.put("city", getRandomString());
            test.put("state", getRandomString());
            test.put("country", getRandomString());
            test.put("elevation", getRandomString());
            test.put("lat", getRandomString());
            test.put("lon", getRandomString());
            test.put("tz", getRandomString());
            band.addToBand(test, new String[] { "firstname", "lastname", "address", "city", "state", "country",
                    "elevation", "lat", "lon", "tz" });

            if (i % 10 == 0) {
                names.add(firstname);
            }
        }
        int counter = 0;
        double time = 0;
        for (int i = 0; i < names.size(); i++) {
            String string = names.get(i);
            if (i > 5) {
                counter++;
                double start = System.nanoTime();
                band.search("firstname", string).getResults();
                double end = System.nanoTime();
                time += (end - start) / 1000000;
                String result = String.format("%.5f", (end - start) / 1000000);
                assertTrue(Double.parseDouble(result) < 20);
            } else {
                band.search("firstname", string).getResults();
            }

        }
        double average = time / counter;
        System.out.println("Band searched " + String.valueOf(counter) + " times averaging "
                + String.format("%.5fms", average) + " per query.");
        assertTrue(average < .06);

    }

    private static String getRandomString() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }

    private JSONObject generateJson(String pro, String value) {
        JSONObject test = new JSONObject();
        test.put(pro, value);
        return test;
    }

}
