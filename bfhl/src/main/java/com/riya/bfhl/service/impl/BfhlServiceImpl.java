package com.riya.bfhl.service.impl;

import com.riya.bfhl.dto.BfhlRequest;
import com.riya.bfhl.dto.BfhlResponse;
import com.riya.bfhl.service.BfhlService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BfhlServiceImpl implements BfhlService {

    private static final String USER_ID = "riya_patial_02042005";
    private static final String EMAIL = "riya1432.be23@chitkarauniversity.edu.in";
    private static final String ROLL_NUMBER = "2311981432";

    @Override
    public BfhlResponse processData(BfhlRequest request) {
        List<String> data = request.getData();

        List<String> oddNumbers = new ArrayList<>();
        List<String> evenNumbers = new ArrayList<>();
        List<String> alphabets = new ArrayList<>();
        List<String> specialChars = new ArrayList<>();
        long numericSum = 0;
        StringBuilder allAlphaChars = new StringBuilder();

        for (String token : data) {
            if (token == null) {
                continue;
            }

            String trimmed = token.trim();
            if (trimmed.isEmpty()) {
                continue;
            }

            if (isNumeric(trimmed)) {
                long value = Long.parseLong(trimmed);
                numericSum += value;

                if (value % 2 == 0) {
                    evenNumbers.add(trimmed);
                } else {
                    oddNumbers.add(trimmed);
                }
            } else if (isAllAlpha(trimmed)) {
                alphabets.add(trimmed.toUpperCase());
                allAlphaChars.append(trimmed);
            } else if (isSingleSpecialChar(trimmed)) {
                specialChars.add(trimmed);
            } else {
                for (char c : trimmed.toCharArray()) {
                    String current = String.valueOf(c);

                    if (Character.isLetter(c)) {
                        alphabets.add(current.toUpperCase());
                        allAlphaChars.append(c);
                    } else if (!Character.isDigit(c)) {
                        specialChars.add(current);
                    }
                }
            }
        }

        return BfhlResponse.builder()
                .isSuccess(true)
                .userId(USER_ID)
                .email(EMAIL)
                .rollNumber(ROLL_NUMBER)
                .oddNumbers(oddNumbers)
                .evenNumbers(evenNumbers)
                .alphabets(alphabets)
                .specialCharacters(specialChars)
                .sum(String.valueOf(numericSum))
                .concatString(buildConcatString(allAlphaChars.toString()))
                .build();
    }

    private boolean isNumeric(String value) {
        if (value == null || value.isEmpty()) {
            return false;
        }

        int start = value.charAt(0) == '-' ? 1 : 0;
        if (start == value.length()) {
            return false;
        }

        for (int i = start; i < value.length(); i++) {
            if (!Character.isDigit(value.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    private boolean isAllAlpha(String value) {
        if (value == null || value.isEmpty()) {
            return false;
        }

        for (char c : value.toCharArray()) {
            if (!Character.isLetter(c)) {
                return false;
            }
        }

        return true;
    }

    private boolean isSingleSpecialChar(String value) {
        return value != null && value.length() == 1 && !Character.isLetterOrDigit(value.charAt(0));
    }

    private String buildConcatString(String allAlphaChars) {
        if (allAlphaChars == null || allAlphaChars.isEmpty()) {
            return "";
        }

        String reversed = new StringBuilder(allAlphaChars).reverse().toString();
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < reversed.length(); i++) {
            char c = reversed.charAt(i);
            result.append(i % 2 == 0 ? Character.toUpperCase(c) : Character.toLowerCase(c));
        }

        return result.toString();
    }
}
