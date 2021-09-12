#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>
#include <deque>
#include <queue>
#include <map>

using namespace std;

// https://www.acmicpc.net/problem/17175

#define DIV 1000000007

int n;
int arr[51];

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	arr[0] = 1;
	arr[1] = 1;

	for (int i = 2; i <= 50; i++)
	{
		arr[i] = (1 + arr[i - 1] + arr[i - 2]) % DIV;
	}

	cin >> n;

	cout << arr[n] << "\n";

	return 0;
}