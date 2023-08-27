#include <bits/stdc++.h>

using namespace std;

class Solution {
 public:
  double findMedianSortedArrays(vector<int>& nums1, vector<int>& nums2) {
    double previous_median;
    int median_pos = (nums1.size() + nums2.size()) / 2;
    bool avg_median = !((nums1.size() ^ nums2.size()) & 1);
    int a, b;  // indexes for nums1 and nums2
    a = 0;
    b = 0;
    // as the comparisson is strict, at the end of this 'while' we must have
    // on median, the previous item corresponding to the median,
    // and a or b corresponds to the previous position of the actual median

    while (a + b < median_pos) {
      if (nums2.size() == b || (nums1.size() != a && nums1[a] < nums2[b])) {
        previous_median = nums1[a];
        a++;
      } else{
        previous_median = nums2[b];
        b++;
      }
    }

    double median;
    if (nums1.size() == a) {
      median = nums2[b];
    } else if (nums2.size() == b) {
      median = nums1[a];
    } else {
      median = (nums1[a] < nums2[b]) ? nums1[a] : nums2[b];
    }
    if (avg_median) median = (previous_median + median) / 2.0;

    return median;
  }
};

int main() {
  vector<int> a = {3, 4};
  vector<int> b = {};

  cout << Solution().findMedianSortedArrays(a, b) << '\n';
}