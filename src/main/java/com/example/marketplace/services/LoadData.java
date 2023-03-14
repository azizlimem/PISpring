package com.example.marketplace.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LoadData {
        static Map<String, String[]> words = new HashMap<>();

        static int largestWordLength = 0;
        public String[] loadFromURL(String url) {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(url).openConnection().getInputStream()));
                String line = "";
                int counter = 0;
                String[] content = null;
                while ((line = reader.readLine()) != null) {
                    counter++;
                    try {
                        content = line.split(",");
                        if (content.length == 0) {
                            continue;
                        }
                        String word = content[0];
                        String[] ignore_in_combination_with_words = new String[]{};
                        if (content.length > 1) {
                            ignore_in_combination_with_words = content[1].split("_");
                        }

                        if (word.length() > largestWordLength) {
                            largestWordLength = word.length();
                        }
                        words.put(word.replaceAll(" ", ""), ignore_in_combination_with_words);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println(content);
                }
                return content;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }


