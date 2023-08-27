#include <bits/stdc++.h>

using namespace std;

class Solution {
 public:
  string longestPalindrome(string s) {
    string palyndrome;
    int palyndrome_length = s.size();
    int start_search;
    int nth_trial = 1;
    int comparissons;
    bool found_palyndrome = 0;
    bool is_odd;
    int start_comparisson;
    while (palyndrome_length > 0 && !found_palyndrome) {
      is_odd = palyndrome_length & 1;
      comparissons = (palyndrome_length >> 1);
      start_search = (palyndrome_length >> 1) + is_odd;  //
      for (int k = 0; k < nth_trial; k++) {
        // int i = is_odd;
        int i = 0;
        start_comparisson = start_search + k;
        for (; i < comparissons; i++) {
          if (s[start_comparisson + i] != s[start_comparisson - (i + 1 + is_odd)]) break;
        }
        if (i >= (comparissons)) {
          found_palyndrome = 1;
          palyndrome = s.substr((start_comparisson - (comparissons + is_odd)),
                                palyndrome_length);
        }
      }
      nth_trial++;
      palyndrome_length--;
    }
    return palyndrome;
  }
};

int main() {
  vector<int> a = {3, 4};
  vector<int> b = {};
  string s = "babad";

  cout << Solution().longestPalindrome(s) << '\n';
}