#include <bits/stdc++.h>

using namespace std;

class Solution {
 public:
  string convert(string s, int numRows) {
    vector<string> zigzag(numRows, "");
    string zigzag_result = "";
    bool down = 1;

    for (int i = 0, k = 0; i < s.size(); i++) {
      zigzag[k % numRows] += s[i];
      if (down)
        k++;
      else
        k--;
      if (k == 0 || k == numRows -1) {
        down = !down;
      }
    }
    for (int i = 0; i < numRows; i++) {
      zigzag_result += zigzag[i];
    }

    return zigzag_result;
  }
};

int main() {
  vector<int> a = {3, 4};
  vector<int> b = {};
  string s = "PAYPALISHIRING";
  int n = 4;

  cout << Solution().convert(s,n) << '\n';
}