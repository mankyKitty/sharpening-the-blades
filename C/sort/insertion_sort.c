#include <stdio.h>

void insertion_sort(int[], int);
void show(int[], int);


int main(void) {
  int len = 7;
  int xs[len];

  // 87, 77, 55, 99, 22, 33, 1
  xs[0] = 87; xs[1] = 77;
  xs[2] = 55; xs[3] = 99;
  xs[4] = 22; xs[5] = 33;
  xs[6] = 1;

  show(xs, len);

  insertion_sort(xs, len);

  show(xs, len);

  return 0;
}

void insertion_sort(int s[], int n) {
  int i,j; /* counters */

  int tmp;

  for (i = 0; i < n; i++) {
    j = i;
    while (j > 0 && (s[j] < s[j - 1])) {
      
      tmp = s[j];
      s[j] = s[j - 1];
      s[j - 1] = tmp;

      j = j - 1;
    }
  }
}

void show(int s[], int l) {

  for (int i = 0; i < l; i++) {
    printf("%d ", s[i]);
  }
  printf("\n");
}
