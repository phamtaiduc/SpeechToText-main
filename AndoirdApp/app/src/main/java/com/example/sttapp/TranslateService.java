package com.example.sttapp;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;
import java.util.List;

public interface TranslateService {
    @POST("language/translate/v2")
        // Đường dẫn API cho Google Translate
    Call<TranslationResponse> translate(
            @Query("q") String text,  // Văn bản cần dịch
            @Query("target") String targetLanguage,  // Ngôn ngữ đích (ví dụ: "en", "vi")
            @Query("key") String apiKey  // API key của bạn
    );

    public class TranslationResponse {
        private Data data;

        public Data getData() {
            return data;
        }

        public class Data {
            private List<Translation> translations;

            public List<Translation> getTranslations() {
                return translations;
            }
        }

        public class Translation {
            private String translatedText;

            public String getTranslatedText() {
                return translatedText;
            }
        }
    }
}