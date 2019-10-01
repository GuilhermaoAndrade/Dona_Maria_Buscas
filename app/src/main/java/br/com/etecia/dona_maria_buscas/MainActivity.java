package br.com.etecia.dona_maria_buscas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.what3words.androidwrapper.What3WordsV3;
import com.what3words.javawrapper.response.APIResponse.What3WordsError;
import com.what3words.javawrapper.response.ConvertToCoordinates;
import com.what3words.javawrapper.response.Autosuggest;
import com.what3words.javawrapper.request.Coordinates;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Thread(new Runnable() {
            public void run() {
                // For all requests a what3words API key is needed
                What3WordsV3 api = new What3WordsV3("what3words-api-key", MainActivity.this);

                Autosuggest autosuggest = api.autosuggest("freshen.overlook.clo")
                        .clipToCountry("FR")
                        .focus(new Coordinates(48.856618, 2.3522411))
                        .nResults(1)
                        .execute();

                if (autosuggest.isSuccessful()) {
                    String words = autosuggest.getSuggestions().get(0).getWords();
                    System.out.printf("Top 3 word address match: %s%n", words);

                    ConvertToCoordinates convertToCoordinates = api.convertToCoordinates(words).execute();
                    if (convertToCoordinates.isSuccessful()) {
                        System.out.printf("WGS84 Coordinates: %f, %f%n",
                                convertToCoordinates.getCoordinates().getLat(),
                                convertToCoordinates.getCoordinates().getLng());
                        System.out.printf("Nearest Place: %s%n", convertToCoordinates.getNearestPlace());
                    } else {
                        What3WordsError error = autosuggest.getError();
                        if (error == What3WordsError.INTERNAL_SERVER_ERROR) { // Server Error
                            System.out.println("InternalServerError: " + error.getMessage());

                        } else if (error == What3WordsError.NETWORK_ERROR) { // Network Error
                            System.out.println("NetworkError: " + error.getMessage());

                        }
                    }
                } else {
                    What3WordsError error = autosuggest.getError();

                    if (error == What3WordsError.BAD_CLIP_TO_COUNTRY) { // Invalid country clip is provided
                        System.out.println("BadClipToCountry: " + error.getMessage());

                    } else if (error == What3WordsError.BAD_FOCUS) { // Invalid focus
                        System.out.println("BadFocus: " + error.getMessage());

                    } else if (error == What3WordsError.BAD_N_RESULTS) { // Invalid number of results
                        System.out.println("BadNResults: " + error.getMessage());

                    } else if (error == What3WordsError.INTERNAL_SERVER_ERROR) { // Server Error
                        System.out.println("InternalServerError: " + error.getMessage());

                    } else if (error == What3WordsError.NETWORK_ERROR) { // Network Error
                        System.out.println("NetworkError: " + error.getMessage());

                    } else {
                        System.out.println(error + ": " + error.getMessage());

                    }
                }
            }
        }).start();
    }
}



