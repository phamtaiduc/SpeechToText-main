package com.example.sttapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardFragment extends Fragment {

    private static final String API_KEY = "fdfc9bc3b4mshb42b39aff7e97f7p17803cjsn3b3a98357e6c"; // Thay YOUR_API_KEY bằng API key của bạn

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        // Gọi hàm để dịch văn bản
        translateText("Hello", "vi"); // Dịch từ "Hello" sang tiếng Việt

        return view;
    }

    private void translateText(String text, String targetLanguage) {
        TranslateService translateService = ApiClient.getClient().create(TranslateService.class);
        Call<TranslateService.TranslationResponse> call = translateService.translate(text, targetLanguage, API_KEY);

        call.enqueue(new Callback<TranslateService.TranslationResponse>() {
            @Override
            public void onResponse(Call<TranslateService.TranslationResponse> call, Response<TranslateService.TranslationResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    String translatedText = response.body().getData().getTranslations().get(0).getTranslatedText();
                    Log.d("Translated Text", translatedText);
                } else {
                    Log.e("API Error", "Error Code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<TranslateService.TranslationResponse> call, Throwable t) {
                Log.e("API Failure", t.getMessage());
            }
        });
    }
}
