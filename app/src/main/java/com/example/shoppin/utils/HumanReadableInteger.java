package com.example.shoppin.utils;

import android.text.TextUtils;

import java.util.HashMap;
import java.util.Map;

enum Language {
     
     //   English Language
     English,
     
     //   Persian Language
     Persian
}

// Digit's groups
enum DigitGroup {
     //  Ones group
     Ones,
     
     // Teens group
     Teens,
     
     // Tens group
     Tens,
     
     // Hundreds group
     Hundreds,
     
     // Thousands group
     Thousands
}

// Equivalent names of a group
class NumberWord {
     
     // Digit's group
     public DigitGroup Group;
     
     // Number to word language
     public Language Language;
     
     // Equivalent names
     public String[] Names;
}

public class HumanReadableInteger {
     
     private static Map<Language, String> And = new HashMap<Language, String>() {
          {
               put(Language.English, " ");
               put(Language.Persian, " و ");
          }
     };
     private static NumberWord[] NumberWords = {new NumberWord() {
          {
               Group = DigitGroup.Ones;
               Language = Language.English;
               Names = new String[]{"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
          }
     }, new NumberWord() {
          {
               Group = DigitGroup.Ones;
               Language = Language.Persian;
               Names = new String[]{"", "یک", "دو", "سه", "چهار", "پنج", "شش", "هفت", "هشت", "نه"};
          }
     }, new NumberWord() {
          {
               Group = DigitGroup.Teens;
               Language = Language.English;
               Names = new String[]{"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen",
                    "Eighteen", "Nineteen"};
          }
     }, new NumberWord() {
          {
               Group = DigitGroup.Teens;
               Language = Language.Persian;
               Names = new String[]{"ده", "یازده", "دوازده", "سیزده", "چهارده", "پانزده", "شانزده", "هفده", "هجده", "نوزده"};
          }
     },
          
          new NumberWord() {
               {
                    Group = DigitGroup.Tens;
                    Language = Language.English;
                    Names = new String[]{"Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
               }
          }, new NumberWord() {
          {
               Group = DigitGroup.Tens;
               Language = Language.Persian;
               Names = new String[]{"بیست", "سی", "چهل", "پنجاه", "شصت", "هفتاد", "هشتاد", "نود"};
          }
     },
          
          new NumberWord() {
               {
                    Group = DigitGroup.Hundreds;
                    Language = Language.English;
                    Names = new String[]{"", "One Hundred", "Two Hundred", "Three Hundred", "Four Hundred", "Five Hundred",
                         "Six Hundred", "Seven Hundred", "Eight Hundred", "Nine Hundred"};
               }
          }, new NumberWord() {
          {
               Group = DigitGroup.Hundreds;
               Language = Language.Persian;
               Names = new String[]{"", "یکصد", "دویست", "سیصد", "چهارصد", "پانصد", "ششصد", "هفتصد", "هشتصد", "نهصد"};
          }
     },
          
          new NumberWord() {
               {
                    Group = DigitGroup.Thousands;
                    Language = Language.English;
                    Names = new String[]{"", " Thousand", " Million", " Billion", " Trillion", " Quadrillion", " Quintillion",
                         " Sextillian", " Septillion", " Octillion", " Nonillion", " Decillion", " Undecillion", " Duodecillion",
                         " Tredecillion", " Quattuordecillion", " Quindecillion", " Sexdecillion", " Septendecillion",
                         " Octodecillion", " Novemdecillion", " Vigintillion", " Unvigintillion", " Duovigintillion", " 10^72",
                         " 10^75", " 10^78", " 10^81", " 10^84", " 10^87", " Vigintinonillion", " 10^93", " 10^96",
                         " Duotrigintillion", " Trestrigintillion"};
               }
          }, new NumberWord() {
          {
               Group = DigitGroup.Thousands;
               Language = Language.Persian;
               Names = new String[]{"", " هزار", " میلیون", " میلیارد", " تریلیون", " Quadrillion", " Quintillion",
                    " Sextillian", " Septillion", " Octillion", " Nonillion", " Decillion", " Undecillion", " Duodecillion",
                    " Tredecillion", " Quattuordecillion", " Quindecillion", " Sexdecillion", " Septendecillion",
                    " Octodecillion", " Novemdecillion", " Vigintillion", " Unvigintillion", " Duovigintillion", " 10^72",
                    " 10^75", " 10^78", " 10^81", " 10^84", " 10^87", " Vigintinonillion", " 10^93", " 10^96",
                    " Duotrigintillion", " Trestrigintillion"};
          }
     },};
     private static Map<Language, String> Negative = new HashMap<Language, String>() {
          {
               put(Language.English, "Negative ");
               put(Language.Persian, "منهای ");
          }
     };
     private static Map<Language, String> Zero = new HashMap<Language, String>() {
          {
               put(Language.English, "Zero");
               put(Language.Persian, "صفر");
          }
     };
     
     public static String convertRialsNumberToLetters(String input) {
          try {
               long initNumber = Long.parseLong(input);
               
               if (null != input && !TextUtils.isEmpty(input)) {
                    long remain = initNumber % 10;
                    long numberInRial = initNumber - remain;
                    long numberInToman = numberInRial / 10;
                    String out = "";
                    
                    if (remain == 0 && numberInToman > 0) {
                         out = numberToText(numberInToman) + " تومان";
                    } else if (remain != 0 && numberInToman > 0) {
                         out = numberToText(numberInToman) + " تومان";
                         //                    out = numberToText(numberInToman) + " تومان و " + numberToText(remain) + " ریال";
                    } else if (remain != 0 && numberInToman == 0) {
                         out = numberToText(remain) + " ریال";
                    }
                    return out;
               } else {
                    return "";
               }
          } catch (NumberFormatException e) {
               e.printStackTrace();
          }
          return "";
     }
     // Public Methods (5)
     
     /* display a numeric value using the equivalent text
      <param name="number">input number</param>
      <param name="language">local language</param>
      <returns>the equivalent text</returns>*/
     public static String NumberToText(int number, Language language) {
          return NumberToText((long) number, language);
     }
     
     public static String numberToText(long number) {
          return NumberToText((long) number, Language.Persian);
     }
     
     /* display a numeric value using the equivalent text
      <param name="number">input number</param>
      <param name="language">local language</param>
      <returns>the equivalent text</returns>*/
     public static String NumberToText(double number, Language language) {
          return NumberToText((long) number, language);
     }
     
     /* display a numeric value using the equivalent text
      <param name="number">input number</param>
      <param name="language">local language</param>
     <returns>the equivalent text</returns>*/
     public static String NumberToText(long number, Language language) {
          if (number == 0) {
               return Zero.get(language);
          }
          
          if (number < 0) {
               return Negative.get(language) + NumberToText(-number, language);
          }
          
          return wordify(number, language, "", 0);
     }
     // Private Methods (2)
     
     private static String getName(int idx, Language language, DigitGroup group) {
          for (NumberWord x : NumberWords) {
               if (x.Group == group && x.Language == language) {
                    return x.Names[idx];
               }
          }
          return null;
     }
     
     private static String wordify(long number, Language language, String leftDigitsText, int thousands) {
          if (number == 0) {
               return leftDigitsText;
          }
          
          String wordValue = leftDigitsText;
          if (wordValue.length() > 0) {
               wordValue += And.get(language);
          }
          
          if (number < 10) {
               wordValue += getName((int) number, language, DigitGroup.Ones);
          } else if (number < 20) {
               wordValue += getName((int) (number - 10), language, DigitGroup.Teens);
          } else if (number < 100) {
               wordValue += wordify(number % 10, language, getName((int) (number / 10 - 2), language, DigitGroup.Tens), 0);
          } else if (number < 1000) {
               wordValue += wordify(number % 100, language, getName((int) (number / 100), language, DigitGroup.Hundreds), 0);
          } else {
               wordValue += wordify(number % 1000, language, wordify(number / 1000, language, "", thousands + 1), 0);
          }
          
          if (number % 1000 == 0) {
               return wordValue;
          }
          return wordValue + getName(thousands, language, DigitGroup.Thousands);
     }
     
}