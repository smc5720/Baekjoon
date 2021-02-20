#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>
#include <deque>
#include <queue>
#include <map>
#include <stack>

using namespace std;

// https://www.acmicpc.net/problem/1037

int N;
int arr[51];
 
int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> N;

	arr[0] = 0;

	for (int i = 1; i <= N; i++)
	{
		int n;
		cin >> n;
		arr[i] = n;
	}

	sort(arr, arr + N + 1);

	if (N % 2 == 0)
	{
		cout << arr[1] * arr[N] << "\n";
	}

	else
	{
		int idx;
		idx = (1 + N) / 2;
		cout << arr[idx] * arr[idx] << "\n";
	}

	return 0;
}