#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>

using namespace std;

// https://www.acmicpc.net/problem/8370

int n1, n2;
int k1, k2;

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> n1 >> k1;
	cin >> n2 >> k2;

	int ans;
	ans = n1 * k1 + n2 * k2;

	cout << ans << "\n";

	return 0;
}