#include <bits/stdc++.h>

using namespace std;

class Solution {
 public:
  int reverse(int x) {
    int ans = 0;
    bool is_negative = x < 0;
    while(x > 0){
      if (ans > INT_MAX / 10 || ans < INT_MIN/10){
        return 0;
      }
      ans = (ans * 10) + x % 10;
      x /= 10;
    }
    return ans * (is_negative ? -1 : 1);
  }
};

int main() {
  vector<int> a = {3, 4};
  vector<int> b = {};
  string s = "PAYPALISHIRING";
  int n = 1534236469;

  cout << Solution().reverse(n) << '\n';
}