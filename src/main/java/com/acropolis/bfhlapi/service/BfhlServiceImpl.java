package com.acropolis.bfhlapi.service;

import com.acropolis.bfhlapi.dto.BfhlRequest;
import com.acropolis.bfhlapi.dto.BfhlResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class BfhlServiceImpl implements BfhlService {

    @Value("${app.user.id}")
    private String userId;

    @Value("${app.user.email}")
    private String email;

    @Value("${app.user.roll-number}")
    private String rollNumber;

    // Pattern for checking if a string is a valid integer (positive or negative)
    private static final Pattern INT_PATTERN = Pattern.compile("^-?\\d+$");

    // Pattern for checking if a string consists entirely of alphabetical characters
    private static final Pattern ALPHA_PATTERN = Pattern.compile("^[a-zA-Z]+$");

    @Override
    public BfhlResponse processRequest(BfhlRequest request) {
        if (request == null || request.getData() == null) {
            return new BfhlResponse(false, userId, email, rollNumber,
                    Collections.emptyList(), Collections.emptyList(),
                    Collections.emptyList(), Collections.emptyList(), "0", "");
        }

        List<String> evenNumbers = new ArrayList<>();
        List<String> oddNumbers = new ArrayList<>();
        List<String> alphabets = new ArrayList<>();
        List<String> specialCharacters = new ArrayList<>();

        BigInteger sum = BigInteger.ZERO;
        List<Character> allAlphabeticalChars = new ArrayList<>();

        for (String element : request.getData()) {
            if (element == null) {
                continue;
            }

            // Trim leading and trailing spaces for classification but keep original
            String trimmed = element.trim();

            if (trimmed.isEmpty()) {
                // If it is empty, classify it as special character or skip it? Let's check
                specialCharacters.add(element);
                continue;
            }

            // Check if it's a number
            if (INT_PATTERN.matcher(trimmed).matches()) {
                BigInteger val = new BigInteger(trimmed);
                sum = sum.add(val);

                // Check even/odd
                if (val.abs().mod(BigInteger.TWO).equals(BigInteger.ZERO)) {
                    evenNumbers.add(element);
                } else {
                    oddNumbers.add(element);
                }
            }
            // Check if it's strictly alphabetical letters
            else if (ALPHA_PATTERN.matcher(trimmed).matches()) {
                alphabets.add(element.toUpperCase());
            }
            // Otherwise it's a special character
            else {
                specialCharacters.add(element);
            }

            // Collect all alphabetical characters from the element in order of appearance
            for (char ch : element.toCharArray()) {
                if (Character.isLetter(ch)) {
                    allAlphabeticalChars.add(ch);
                }
            }
        }

        // Generate the alternating caps string from reversed alphabetical characters
        String concatString = generateAlternatingCapsString(allAlphabeticalChars);

        return new BfhlResponse(
                true,
                userId,
                email,
                rollNumber,
                evenNumbers,
                oddNumbers,
                alphabets,
                specialCharacters,
                sum.toString(),
                concatString
        );
    }

    private String generateAlternatingCapsString(List<Character> chars) {
        if (chars == null || chars.isEmpty()) {
            return "";
        }

        // Reverse the gathered alphabetical characters list
        List<Character> reversedChars = new ArrayList<>(chars);
        Collections.reverse(reversedChars);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < reversedChars.size(); i++) {
            char ch = reversedChars.get(i);
            if (i % 2 == 0) {
                sb.append(Character.toUpperCase(ch));
            } else {
                sb.append(Character.toLowerCase(ch));
            }
        }
        return sb.toString();
    }
}
