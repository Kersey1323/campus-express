//package com.kersey.utils;
//
//import com.google.cloud.speech.v1.RecognitionAudio;
//import com.google.cloud.speech.v1.RecognitionConfig;
//import com.google.cloud.speech.v1.RecognizeResponse;
//import com.google.cloud.speech.v1.SpeechClient;
//import com.google.cloud.speech.v1.SpeechRecognitionAlternative;
//import com.google.cloud.speech.v1.SpeechRecognitionResult;
//import com.google.protobuf.ByteString;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//
//public class SpeechRecognitionUtil {
//    public static void main(String... args) throws Exception {
//        // Instantiates a client
//        try (SpeechClient speechClient = SpeechClient.create()) {
//
//            // The path to the audio file to transcribe
//            String fileName = "record/RC.wav";
//
//            // Reads the audio file into memory
//            Path path = Paths.get(fileName);
//            byte[] data = Files.readAllBytes(path);
//            ByteString audioBytes = ByteString.copyFrom(data);
//
//            // Builds the recognition config
//            RecognitionConfig config =
//                    RecognitionConfig.newBuilder()
//                            .setEncoding(RecognitionConfig.AudioEncoding.LINEAR16)
//                            .setLanguageCode("en-US")
//                            .build();
//
//            // Builds the recognition audio
//            RecognitionAudio audio = RecognitionAudio.newBuilder().setContent(audioBytes).build();
//
//            // Performs speech recognition on the audio file
//            RecognizeResponse response = speechClient.recognize(config, audio);
//            for (SpeechRecognitionResult result : response.getResultsList()) {
//                // First alternative is the most probable result
//                SpeechRecognitionAlternative alternative = result.getAlternativesList().get(0);
//                System.out.printf("Transcription: %s%n", alternative.getTranscript());
//            }
//        }
//    }
//}
