#include <bits/stdc++.h>

using namespace std;

class Solution {
 public:
  string convert(string s, int numRows) {
    vector<vector<char>> zigzag = vector(numRows, vector<char>());
    string zigzag_result = "";
    bool down = 1;

    for (int i = 0, k = 0; i < s.size(); i++) {
      zigzag[k % numRows].push_back(s[i]);
      if (down)
        k++;
      else
        k--;
      if (!(k % numRows)) {
        down = !down;
        if (numRows > 2) k += (down ? 0 : -2);
      }
    }
    for (int i = 0; i < numRows; i++) {
      for (char c : zigzag[i]) {
        zigzag_result += c;
      }
    }

    return zigzag_result;
  }
};

int main() {
  vector<int> a = {3, 4};
  vector<int> b = {};
  string s = "ABCD";
  int n = 2;

  cout << Solution().convert(s,n) << '\n';
}