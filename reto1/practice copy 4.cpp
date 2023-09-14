#include <bits/stdc++.h>

using namespace std;

class Solution {
 public:
  bool isMatch(string s, string p) {
    int pointer_pattern = 0;
    int i = 0;
    for (; i < s.length() && pointer_pattern < p.length(); i++) {
      if (s[i] == p[pointer_pattern] || p[pointer_pattern] == '.') {
        pointer_pattern++;
      } else if (p[pointer_pattern] == '*') {
        while (s[i] == p[pointer_pattern - 1] || p[pointer_pattern - 1] == '.') {
          i++;
          }
        pointer_pattern++;
        i--;
      } else if (pointer_pattern < p.length() - 1 &&
                 p[pointer_pattern + 1] == '*')
        pointer_pattern++;
      else return false;
    }
    if (i >= s.length()) return true;
    return false;
  }
};

int main() {
  vector<int> a = {3, 4};
  vector<int> b = {};
  string s = "mississippi";
  string p = "mis*is*ip*.";
  int n = 1534236469;

  cout << Solution().isMatch(s,p) << '\n';
}