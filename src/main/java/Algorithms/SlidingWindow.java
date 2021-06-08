package Algorithms;

import java.util.*;

class WordConcatenation {
    public static List<Integer> findWordConcatenation(String str, String[] words) {
        List<Integer> resultIndices = new ArrayList<Integer>();
        char[] strChars = str.toCharArray();

        Map<String, Integer> wordsMap = new HashMap<>();
        int lengthOfAllWords = 0;
        int longestWordLength = 0;
        for (int i = 0; i < words.length; i++) {
            wordsMap.put(words[i], wordsMap.getOrDefault(words[i], 0) + 1);
            lengthOfAllWords = lengthOfAllWords + words[i].length();
            if (words[i].length() > longestWordLength) {
                longestWordLength = words[i].length();
            }
        }

        int windowStart = 0;
        if (lengthOfAllWords > 0 && lengthOfAllWords <= strChars.length) {
            for (int windowEnd = 0; windowEnd < strChars.length; windowEnd++) {
                String currentString = str.substring(windowStart, windowEnd + 1);
                if (wordsMap.getOrDefault(currentString, 0) > 0) {
                    // this is one of our words
                    wordsMap.put(currentString, wordsMap.get(currentString) - 1);
                    if (wordsMap.getOrDefault(currentString, 0) == 0) {
                        wordsMap.remove(currentString);
                    }

                    if (wordsMap.size() == 0) {
                        // we have found a match for a permutation
                        resultIndices.add(windowStart);

                        // if there's not enough letters left, return here
                        if ((strChars.length - windowEnd) < lengthOfAllWords) {
                            return resultIndices;
                        }

                        // otherwise reset words map and continue
                        wordsMap = new HashMap<>();
                        for (int i = 0; i < words.length; i++) {
                            wordsMap.put(words[i], wordsMap.getOrDefault(words[i], 0) + 1);
                        }
                    }

                    windowStart = windowEnd + 1;
                }
            }
        }

        return resultIndices;
    }
}

class MinimumWindowSubstring {
    public static String findSubstring(String str, String pattern) {
        String smallestSubstring = "";
        List<Integer> matchingStarts = new ArrayList<>();
        int windowStart = 0;
        Map<Character, Integer> characterCountOrig = new HashMap<Character, Integer>();
        Map<Character, Integer> characterCountWorking = new HashMap<Character, Integer>();
        char[] strChars = str.toCharArray();
        char[] patternChars = pattern.toCharArray();

        for (int i = 0; i < patternChars.length; i++) {
            characterCountOrig.put(patternChars[i], characterCountOrig.getOrDefault(patternChars[i], 0) + 1);
        }

        characterCountWorking.putAll(characterCountOrig); // initialize working character count
        for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
            char endChar = strChars[windowEnd];
            char startChar = strChars[windowStart];
            int windowSize = windowEnd - windowStart + 1;
            if (pattern.contains(String.valueOf(endChar))) {
                // check to see if we have all the chars yet
                if (characterCountWorking.getOrDefault(endChar, 0) > 0) {
                    characterCountWorking.put(endChar, characterCountWorking.get(endChar) - 1);
                    if (characterCountWorking.get(endChar) == 0) {
                        characterCountWorking.remove(endChar);
                        if (characterCountWorking.size() == 0) {
                            String currentSubstring = str.substring(windowStart, windowEnd + 1);
                            if (currentSubstring.length() < smallestSubstring.length() || smallestSubstring == "") {
                                smallestSubstring = currentSubstring;
                            }
                            matchingStarts.add(windowStart); // add this matching start index

                            // move start forward
                            characterCountWorking = new HashMap<Character, Integer>();
                            characterCountWorking.putAll(characterCountOrig);
                            windowEnd = windowStart;
                            windowStart = windowStart + 1;
                        }
                    }
                }
            }
        }

        return smallestSubstring;
    }
}

class StringPermutation {

    public static List<Integer> findPermutationIndexes(String str, String pattern) {
        List<Integer> matchingStarts = new ArrayList<>();
        int windowStart = 0;
        Map<Character, Integer> characterCountOrig = new HashMap<Character, Integer>();
        Map<Character, Integer> characterCountWorking = new HashMap<Character, Integer>();
        char[] strChars = str.toCharArray();
        char[] patternChars = pattern.toCharArray();

        for (int i = 0; i < patternChars.length; i++) {
            characterCountOrig.put(patternChars[i], characterCountOrig.getOrDefault(patternChars[i], 0) + 1);
        }

        characterCountWorking.putAll(characterCountOrig); // initialize working character count
        for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
            char endChar = strChars[windowEnd];
            char startChar = strChars[windowStart];
            int windowSize = windowEnd - windowStart + 1;
            if (pattern.contains(String.valueOf(endChar))) {
                // check to see if we have all the chars yet
                if (characterCountWorking.getOrDefault(endChar, 0) > 0) {
                    characterCountWorking.put(endChar, characterCountWorking.get(endChar) - 1);
                    if (characterCountWorking.get(endChar) == 0) {
                        characterCountWorking.remove(endChar);
                        if (characterCountWorking.size() == 0) {
                            matchingStarts.add(windowStart); // add this matching start index

                            // move start forward
                            characterCountWorking = new HashMap<Character, Integer>();
                            characterCountWorking.putAll(characterCountOrig);
                            windowEnd = windowStart;
                            windowStart = windowStart + 1;
                        }
                    }
                } else {
                    // if this matched the previous char, then start over
                    if (endChar == strChars[windowEnd - 1]) {
                        characterCountWorking = new HashMap<Character, Integer>();
                        characterCountWorking.putAll(characterCountOrig);
                        windowEnd = windowStart;
                        windowStart = windowStart + 1;
                    } else {
                        // otherwise just move start forward
                        characterCountWorking.put(startChar, characterCountWorking.getOrDefault(startChar, 0) + 1);
                        windowStart++;
                    }
                }
            } else {
                // we broke the sequence, start over
                characterCountWorking = new HashMap<Character, Integer>();
                characterCountWorking.putAll(characterCountOrig);
                windowStart = windowEnd + 1;
            }
        }
        return matchingStarts;
    }

    public static boolean findPermutation(String str, String pattern) {
        int windowStart = 0;
        Map<Character, Integer> characterCountOrig = new HashMap<Character, Integer>();
        Map<Character, Integer> characterCountWorking = new HashMap<Character, Integer>();
        char[] strChars = str.toCharArray();
        char[] patternChars = pattern.toCharArray();

        for (int i = 0; i < patternChars.length; i++) {
            characterCountOrig.put(patternChars[i], characterCountOrig.getOrDefault(patternChars[i], 0) + 1);
        }

        characterCountWorking.putAll(characterCountOrig); // initialize working character count
        for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
            char endChar = strChars[windowEnd];
            char startChar = strChars[windowStart];
            int windowSize = windowEnd - windowStart + 1;
            if (pattern.contains(String.valueOf(endChar))) {
                // check to see if we have all the chars yet
                if (characterCountWorking.getOrDefault(endChar, 0) > 0) {
                    characterCountWorking.put(endChar, characterCountWorking.get(endChar) - 1);
                    if (characterCountWorking.get(endChar) == 0) {
                        characterCountWorking.remove(endChar);
                        if (characterCountWorking.size() == 0) {
                            return true;
                        }
                    }
                } else {
                    // we broke the sequence, start over
                    characterCountWorking = new HashMap<Character, Integer>();
                    characterCountWorking.putAll(characterCountOrig);
                }
            } else {
                // we broke the sequence, start over
                characterCountWorking = new HashMap<Character, Integer>();
                characterCountWorking.putAll(characterCountOrig);
            }
        }
        return false;
    }
}

class ReplacingOnes {
    public static int findLength(int[] inputArray, int k) {
        int windowStart = 0, maxLength = 0, maxContiguousOnesCount = 0;
        Map<Integer, Integer> integerCountMap = new HashMap<>();

        for (int windowEnd = 0; windowEnd < inputArray.length; windowEnd++) {
            int leftInt = inputArray[windowStart];
            int rightInt = inputArray[windowEnd];
            int currentWindowSize = windowEnd - windowStart + 1;
            int currentCountForRightInt = integerCountMap.getOrDefault(rightInt, 0) + 1;
            int currentCountForOnes = integerCountMap.getOrDefault(1, 0) + 1;
            integerCountMap.put(rightInt, currentCountForRightInt);
            maxContiguousOnesCount = Math.max(maxContiguousOnesCount, currentCountForOnes);

            if (currentWindowSize - maxContiguousOnesCount > k) {
                // we need more than k replacements, so shrink the window
                integerCountMap.put(leftInt, integerCountMap.get(leftInt) - 1);
                windowStart++;
            }

            maxLength = Math.max(maxLength, windowEnd - windowStart + 1);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        // System.out.println(ReplacingOnes.findLength(new int[] { 0, 1, 1, 0, 0, 0, 1, 1, 0, 1, 1 }, 2));
        // System.out.println(ReplacingOnes.findLength(new int[] { 0, 1, 0, 0, 1, 1, 0, 1, 1, 0, 0, 1, 1 }, 3));
        // System.out.println(StringPermutation.findPermutationIndexes("oidbcaf", "abc"));
        // String="", Pattern="pq"
        // System.out.println(MinimumWindowSubstring.findSubstring("aabdec", "abc"));

        System.out.println(WordConcatenation.findWordConcatenation("catfoxcat", new String[]{"cat", "fox"}));
        System.out.println(WordConcatenation.findWordConcatenation("catcatfoxfox", new String[]{"cat", "fox"}));

        // System.out.println(StringPermutation.findPermutationIndexes("odicf", "dc"));
        // System.out.println(StringPermutation.findPermutationIndexes("bcdxabcdy", "bcdyabcdx"));
        // System.out.println(StringPermutation.findPermutationIndexes("aaacb", "abc"));

    }
}
